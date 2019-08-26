package com.gpro.consulting.tiers.commun.coordination.response.value;

/**
 * @author Wahid Gazzah
 * @since 08/08/2016
 *
 */
public class ErrorValue {
	
	private ErrorType errorType;
	private String message;
	private String code;
	
	public ErrorType getErrorType() {
		return errorType;
	}
	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
