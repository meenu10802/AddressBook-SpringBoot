package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.exception.AddressBookException;
import com.bridgelabz.addressbookapp.model.AddressBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AddressBookService {

    private final List<AddressBook> addressBookList = new ArrayList<>();
    private int contactIdCounter = 1;

    public List<AddressBook> getAllContacts() {
        log.info("Getting all contacts");
        return addressBookList;
    }

    public AddressBook getContactById(int id) {
        log.info("Getting contact by id: {}", id);

        return addressBookList.stream()
                .filter(contact -> contact.getId() == id)
                .findFirst()
                .orElseThrow(() -> new AddressBookException("Address Book contact not found with id: " + id));
    }

    public AddressBook addContact(AddressBookDTO addressBookDTO) {
        log.info("Adding new contact");

        AddressBook newContact = new AddressBook(
                contactIdCounter++,
                addressBookDTO.getName(),
                addressBookDTO.getCity(),
                addressBookDTO.getState()
        );

        addressBookList.add(newContact);
        return newContact;
    }

    public AddressBook updateContact(int id, AddressBookDTO addressBookDTO) {
        log.info("Updating contact with id: {}", id);

        AddressBook contact = addressBookList.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new AddressBookException("Cannot update. Address Book contact not found with id: " + id));

        contact.setName(addressBookDTO.getName());
        contact.setCity(addressBookDTO.getCity());
        contact.setState(addressBookDTO.getState());

        return contact;
    }

    public void deleteContact(int id) {
        log.info("Deleting contact with id: {}", id);

        AddressBook contact = addressBookList.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new AddressBookException("Cannot delete. Address Book contact not found with id: " + id));

        addressBookList.remove(contact);
    }
}