package com.msf.employee.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeGenericException extends RuntimeException{

    private int infoId;
    private String infoMessage;
    
	public EmployeeGenericException(final int infoId, final String infoMessage) {
        super(infoMessage);
       this.infoId = infoId;
       this.infoMessage = infoMessage;
	}
}
