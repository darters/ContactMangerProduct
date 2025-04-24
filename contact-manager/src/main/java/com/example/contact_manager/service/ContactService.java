package com.example.contact_manager.service;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.contact_manager.dto.ContactDTO;
import com.example.contact_manager.entity.Contact;
import com.example.contact_manager.repository.ContactRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Service
public class ContactService {
    private final ContactRepository contactRepository;
    public ContactService (ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    
    private ContactDTO mapToDTO(Contact contact) {
        return new ContactDTO(contact.getId(), contact.getFullname(), contact.getEmail(), contact.getPhoneNumber());
    }
    private Contact mapToEntity(ContactDTO contactDTO) {
        return new Contact(contactDTO.getFullname(), contactDTO.getEmail(), contactDTO.getPhoneNumber());
    }
    public List<ContactDTO> getAllContacts() {
        return contactRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    public ContactDTO getContactById(Integer id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        return mapToDTO(contact);
    }
    public ContactDTO createContact(@RequestBody ContactDTO contactDTO) {
        Contact contact = mapToEntity(contactDTO);
        Contact savedContact = contactRepository.save(contact);
        return mapToDTO(savedContact);
    }
    public ContactDTO updateContact(Integer id, @RequestBody ContactDTO contactDTO) {
        Contact existingContact = contactRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Contact not found"));
        
        Method[] methods = ContactDTO.class.getDeclaredMethods();
        for(Method method : methods) {
            if(method.getName().startsWith("get")) {
                try {
                    String getterName = method.getName();
                    Method getter = ContactDTO.class.getMethod(getterName);
                    Object valueFromRequest = getter.invoke(contactDTO);
                    if(valueFromRequest != null) {
                        String setterName = "set" + getterName.substring(3);
                        Method setterMethod = Contact.class.getMethod(setterName, getter.getReturnType());
                        setterMethod.invoke(existingContact, valueFromRequest);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return mapToDTO(contactRepository.save(existingContact));
    }

    public void deleteContactById(Integer id) {
        contactRepository.deleteById(id);
    }
}
