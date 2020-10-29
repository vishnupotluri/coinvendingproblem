package com.adp.coinvendingmachine.service;

import java.util.Map;

import com.adp.coinvendingmachine.Exception.CoinVendingMachineException;
import com.adp.coinvendingmachine.entity.CashDenominationRequest;

public interface CoinVendingMachineService {
	
	public boolean isGivenDollarAmountVaild(int inputDollarAmount) throws CoinVendingMachineException;
	
	public void initialize(Integer initialAmount);
	
	public Map<String,Integer> getChange(long amount) throws CoinVendingMachineException;

	public long  getTotalDollarAmount(CashDenominationRequest cashDenominationRequest);
}
