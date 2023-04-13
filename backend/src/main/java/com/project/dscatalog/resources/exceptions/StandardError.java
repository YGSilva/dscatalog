package com.project.dscatalog.resources.exceptions;

import java.time.Instant;

public class StandardError {
	
	private Instant timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
	
	public StandardError() {
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant instant) {
		this.timestamp = instant;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int i) {
		this.status = i;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
