package com.example.exception;

public class SplitBillException extends Exception {

	private static final long serialVersionUID = 1L;

	public SplitBillException() {
	}

	public SplitBillException(String errors) {
		super(errors);
	}

}
