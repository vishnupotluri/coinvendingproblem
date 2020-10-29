package com.adp.coinvendingmachine.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.adp.coinvendingmachine.Exception.CoinVendingMachineException;
import com.adp.coinvendingmachine.common.CoinVendingMachineConstants;
import com.adp.coinvendingmachine.controller.CoinVendingMachineController;
import com.adp.coinvendingmachine.entity.CashDenominationRequest;
import com.adp.coinvendingmachine.entity.Coin;
import com.adp.coinvendingmachine.util.Inventory;

@Service("coinVendingMachineService")
public class CoinVendingMachineServiceImpl implements CoinVendingMachineService {

	private static final Logger logger = LoggerFactory.getLogger(CoinVendingMachineController.class);

	private static Inventory<Coin> cashInventory = new Inventory<>();

	public boolean isGivenDollarAmountVaild(int inputDollarAmount) {
		if (CoinVendingMachineConstants.LIST.contains(inputDollarAmount)) {
			logger.info("Given input amount is valid: {} ", inputDollarAmount);
			return true;
		} else {
			logger.error("Given input amount is invalid: {} ", inputDollarAmount);
			throw new CoinVendingMachineException("Given input value " + inputDollarAmount
					+ " is invalid. Valid values are: 1, 2, 5, 10, 20, 50, 100");
		}
	}

	public void initialize(Integer givenAmount) {
		if (givenAmount == 0) {
			givenAmount = 100; 
		}
		cashInventory.clear();
		for (Coin c : Coin.values()) {
			cashInventory.put(c, givenAmount);
		}

	}

	public Map<String, Integer> getChange(long amount) throws CoinVendingMachineException {
		logger.debug("Inside get change method");
		Map<String, Integer> coinDenominations = new HashMap<>();

		if (amount > 0) {
			long balance = amount * 100;
			while (balance > 0) {
				if (balance >= Coin.QUARTER.getDenomination() && cashInventory.hasItem(Coin.QUARTER)) {
					coinDenominations.merge(Coin.QUARTER.name(), 1, Integer::sum);
					balance = balance - Coin.QUARTER.getDenomination();
					cashInventory.deduct(Coin.QUARTER);
				} else if (balance >= Coin.DIME.getDenomination() && cashInventory.hasItem(Coin.DIME)) {
					coinDenominations.merge(Coin.DIME.name(), 1, Integer::sum);
					balance = balance - Coin.DIME.getDenomination();
					cashInventory.deduct(Coin.DIME);
				} else if (balance >= Coin.NICKLE.getDenomination() && cashInventory.hasItem(Coin.NICKLE)) {
					coinDenominations.merge(Coin.NICKLE.name(), 1, Integer::sum);
					balance = balance - Coin.NICKLE.getDenomination();
					cashInventory.deduct(Coin.NICKLE);
				} else if (balance >= Coin.PENNY.getDenomination() && cashInventory.hasItem(Coin.PENNY)) {
					coinDenominations.merge(Coin.PENNY.name(), 1, Integer::sum);
					balance = balance - Coin.PENNY.getDenomination();
					cashInventory.deduct(Coin.PENNY);
				} else {
					logger.error("No sufficient change avialable");
					throw new CoinVendingMachineException("Not sufficient change available, Please come back later!");
				}
			}
		}
		logger.debug("Inside get change method end");
		return coinDenominations;
	}

	public long getTotalDollarAmount(CashDenominationRequest cashDenominationRequest) {

		return (cashDenominationRequest.getDollar() * 1) + (cashDenominationRequest.getTwoDollar() * 2)
				+ (cashDenominationRequest.getFiveDollar() * 5) + (cashDenominationRequest.getTenDollar() * 10)
				+ (cashDenominationRequest.getTwentyDollar() * 20) + (cashDenominationRequest.getFiftyDollar() * 50)
				+ (cashDenominationRequest.getHundredDollar() * 100);
	}

}
