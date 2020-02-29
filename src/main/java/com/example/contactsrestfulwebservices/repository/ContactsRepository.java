package com.example.contactsrestfulwebservices.repository;

import com.example.contactsrestfulwebservices.persistence.ContactsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepository extends JpaRepository<ContactsEntity, Integer> {
}
