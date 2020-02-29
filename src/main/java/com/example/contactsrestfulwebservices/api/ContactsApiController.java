package com.example.contactsrestfulwebservices.api;

import com.example.contactsrestfulwebservices.exceptions.ContactNotFoundException;
import com.example.contactsrestfulwebservices.persistence.ContactsEntity;
import com.example.contactsrestfulwebservices.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController

public class ContactsApiController {

	// JPA to access data from database
	@Autowired
	private ContactsRepository contactsRepository;

	@GetMapping("/team/contacts")
	public List<ContactsEntity> retrieveAllContacts() {
		return contactsRepository.findAll();
	}

	@GetMapping("/team/contacts/{id}")
	public Optional<ContactsEntity> findOneContact(@PathVariable int id) {
		Optional<ContactsEntity> contact = contactsRepository.findById(id);
		if (!contact.isPresent()) {
			throw new ContactNotFoundException("id-" + id);
		}
		return contact;
	}

	//@RequestMapping(value = "/team/contacts", method = RequestMethod.POST)
	@PostMapping("/team/contacts")
	public ResponseEntity<ContactsEntity> createContact(@Valid @RequestBody ContactsEntity contact) {
		ContactsEntity savedContact = contactsRepository.save(contact);
		return new ResponseEntity<>(savedContact, HttpStatus.CREATED);
	}

	// DELETE resource
	@DeleteMapping("/team/contacts/{id}")
	public void deleteContact(@PathVariable int id) {
		contactsRepository.deleteById(id);
	}

}
