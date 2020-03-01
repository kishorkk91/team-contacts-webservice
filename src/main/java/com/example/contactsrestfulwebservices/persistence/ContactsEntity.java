package com.example.contactsrestfulwebservices.persistence;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity // contacts entity is managed by JPA
@Table(name = "contacts")
public class ContactsEntity {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 3, message = "First Name should have at least 3 characters")
	@Column(nullable = false)
	private String firstName;

	@Size(min = 3, message = "last Name should have at least 3 characters")
	@Column(nullable = false)
	private String lastName;

	@NotBlank(message = "Email is mandatory")
	private String email;

	public ContactsEntity(){
		}

	public ContactsEntity(String firstName, String lastName,
			 String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "ContactsEntity{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
