package com.adp.coinvendingmachine.Exception;

@SuppressWarnings("serial")
public class CoinVendingMachineException extends RuntimeException {
	
	private String message;

	public CoinVendingMachineException(String string) {
		this.message = string;
	}

	@Override 
	public String getMessage(){ return message; }
}
