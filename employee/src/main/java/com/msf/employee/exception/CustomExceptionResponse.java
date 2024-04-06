package com.msf.employee.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomExceptionResponse {

	private String infoMessage;
	private int infoId;
	private String callerURL;
	private LocalDateTime timestamp;
	public CustomExceptionResponse(String infoMessage, int infoId,String callerURL) {
		super();
		this.infoMessage = infoMessage;
		this.infoId = infoId;
		this.callerURL = callerURL;
		this.timestamp= LocalDateTime.now();
	}
}