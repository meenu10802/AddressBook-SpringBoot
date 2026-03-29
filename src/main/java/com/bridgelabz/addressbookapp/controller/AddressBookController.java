package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.dto.ResponseDTO;
import com.bridgelabz.addressbookapp.model.AddressBook;
import com.bridgelabz.addressbookapp.service.AddressBookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllContacts() {
        log.info("Fetching all contacts");
        List<AddressBook> contacts = addressBookService.getAllContacts();
        return new ResponseEntity<>(
                new ResponseDTO("Fetched all contacts successfully", contacts),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getContactById(@PathVariable int id) {
        log.info("Fetching contact with id: {}", id);
        AddressBook contact = addressBookService.getContactById(id);
        return new ResponseEntity<>(
                new ResponseDTO("Fetched contact successfully", contact),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addContact(@Valid @RequestBody AddressBookDTO addressBookDTO) {
        log.info("Adding new contact");
        AddressBook newContact = addressBookService.addContact(addressBookDTO);
        return new ResponseEntity<>(
                new ResponseDTO("Contact added successfully", newContact),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateContact(@PathVariable int id,
                                                     @Valid @RequestBody AddressBookDTO addressBookDTO) {
        log.info("Updating contact with id: {}", id);
        AddressBook updatedContact = addressBookService.updateContact(id, addressBookDTO);
        return new ResponseEntity<>(
                new ResponseDTO("Contact updated successfully", updatedContact),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteContact(@PathVariable int id) {
        log.info("Deleting contact with id: {}", id);
        addressBookService.deleteContact(id);
        return new ResponseEntity<>(
                new ResponseDTO("Contact deleted successfully", null),
                HttpStatus.OK
        );
    }
}