package com.example.SpringBootAngular.exception;

import java.util.Date;

public class ErrorDetails {
	
	private Date timestamp;
	private String details;
	private String message;
	
	public ErrorDetails() {
		
	}

	public ErrorDetails(Date timestamp, String details, String message) {
		super();
		this.timestamp = timestamp;
		this.details = details;
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorDetails [timestamp=" + timestamp + ", details=" + details + ", message=" + message + "]";
	}
	
	
}
