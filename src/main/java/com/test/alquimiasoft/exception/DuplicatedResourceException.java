package com.test.alquimiasoft.exception;

public class DuplicatedResourceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private static final String DESCRIPTION = "Duplicated Resource Exception";

	public DuplicatedResourceException(String detail) {

		//super(DESCRIPTION + ". " + detail);
		super(detail);

	}

}
