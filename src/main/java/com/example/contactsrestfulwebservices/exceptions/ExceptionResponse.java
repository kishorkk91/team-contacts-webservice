package com.example.contactsrestfulwebservices.exceptions;

import java.util.Date;

/* the objective of the class is to create customized exception so it can be used by all teams
 * or company i.e whenever the exception will occur then i want return in below format*/
public class ExceptionResponse {

	private Date timestamp;
	private String message;
	private String details;

	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}
