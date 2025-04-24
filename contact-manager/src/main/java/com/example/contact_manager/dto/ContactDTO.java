package com.example.contact_manager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(description = "Contact")
@AllArgsConstructor
public class ContactDTO {
    private Integer id;
    @Schema(description = "Full name (3 and 34 characters)<br> example: John Doe")
    @Size(min = 3, max = 34, message = "Full name length must be between 3 and 34 characters")
    @NotBlank(message = "Full name can't be blank")
    private String fullname;
    @Schema(description = "ValidEmail <br> exapmle: johndoe@gmail.com")
    @NotBlank(message = "Email can't be blank")
    @Email(message = "Email should be valid")
    private String email;
    @Schema(description = "Phone number (11 to 15 digits)<br> example: 12125554567")
    @Size(min = 11, max = 15, message = "Phone number length should be between 11 and 15 characters long")
    @NotBlank(message = "Phone number can't be blank")
    private String phoneNumber;
}
