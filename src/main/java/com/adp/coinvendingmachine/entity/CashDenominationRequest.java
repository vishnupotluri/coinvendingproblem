package com.adp.coinvendingmachine.entity;

public class CashDenominationRequest {
	
	private long dollar;
	
	private long twoDollar;
	
	private long fiveDollar;
	
	private long tenDollar;
	
	private long twentyDollar;
	
	private long fiftyDollar;
	
	private long hundredDollar;
	
	
	public long getDollar() {
		return dollar;
	}
	public void setDollar(long dollar) {
		this.dollar = dollar;
	}
	public long getTwoDollar() {
		return twoDollar;
	}
	public void setTwoDollar(long twoDollar) {
		this.twoDollar = twoDollar;
	}
	public long getFiveDollar() {
		return fiveDollar;
	}
	public void setFiveDollar(long fiveDollar) {
		this.fiveDollar = fiveDollar;
	}
	public long getTenDollar() {
		return tenDollar;
	}
	public void setTenDollar(long tenDollar) {
		this.tenDollar = tenDollar;
	}
	public long getTwentyDollar() {
		return twentyDollar;
	}
	public void setTwentyDollar(long twentyDollar) {
		this.twentyDollar = twentyDollar;
	}
	public long getFiftyDollar() {
		return fiftyDollar;
	}
	public void setFiftyDollar(long fiftyDollar) {
		this.fiftyDollar = fiftyDollar;
	}
	public long getHundredDollar() {
		return hundredDollar;
	}
	public void setHundredDollar(long hundredDollar) {
		this.hundredDollar = hundredDollar;
	}
	@Override
	public String toString() {
		return "CashDenominationRequest [dollar=" + dollar + ", twoDollar=" + twoDollar + ", fiveDollar=" + fiveDollar
				+ ", tenDollar=" + tenDollar + ", twentyDollar=" + twentyDollar + ", fiftyDollar=" + fiftyDollar
				+ ", hundredDollar=" + hundredDollar + "]";
	}
	
	

}
