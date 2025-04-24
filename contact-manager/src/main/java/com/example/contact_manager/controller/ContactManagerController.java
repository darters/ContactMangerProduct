package com.example.contact_manager.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.contact_manager.dto.ContactDTO;
import com.example.contact_manager.service.ContactService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/contacts")
public class ContactManagerController {
    private final ContactService contactService;
    public ContactManagerController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ContactDTO>> getAllContacts() {
        List<ContactDTO> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<ContactDTO> getContactById(@PathVariable Integer id) {
        ContactDTO contactDTO = contactService.getContactById(id);
        return ResponseEntity.ok(contactDTO);
    }
    
    @PostMapping("/add")
    public ResponseEntity<ContactDTO> createContact(@RequestBody @Valid ContactDTO contactDTO) {
        ContactDTO createdContact = contactService.createContact(contactDTO);
        return ResponseEntity.ok(createdContact);
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<ContactDTO> updateContact(@PathVariable Integer id, @RequestBody @Valid ContactDTO contactDTO) {
        ContactDTO updatedContact = contactService.updateContact(id, contactDTO);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContactById(@PathVariable Integer id) {
        contactService.deleteContactById(id);
        return ResponseEntity.noContent().build();
    }
}

