package com.bridgelabz.addressbookapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressBookDTO {

    @NotBlank(message = "Name cannot be empty")
    @Pattern(
            regexp = "^[A-Za-z ]{2,}$",
            message = "Name must contain only alphabets and minimum 2 characters"
    )
    private String name;

    private String city;
    private String state;
}