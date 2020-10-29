package com.adp.coinvendingmachine.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adp.coinvendingmachine.Exception.CoinVendingMachineException;
import com.adp.coinvendingmachine.entity.CashDenominationRequest;
import com.adp.coinvendingmachine.entity.ChangeAmount;
import com.adp.coinvendingmachine.service.CoinVendingMachineService;

@RestController
public class CoinVendingMachineController {
	private static final Logger logger = LoggerFactory.getLogger(CoinVendingMachineController.class);

	@Autowired
	CoinVendingMachineService coinVendingMachineService;

	@PostMapping(value = "/getChangeForGivenDollarAmount")
	public ResponseEntity<ChangeAmount> getChangeForGivenDollarAmount(
			@RequestParam(value = "inputDollarAmount", required = true) Integer inputDollarAmount)
			throws CoinVendingMachineException {
		logger.debug("Inside get change for given dollar amount");
		ChangeAmount changeAmount = new ChangeAmount();
		logger.info("Given value in the input request {}", inputDollarAmount);
		var isInputValid = coinVendingMachineService.isGivenDollarAmountVaild(inputDollarAmount);
		if (isInputValid) {
			changeAmount.setChangeDetails(coinVendingMachineService.getChange(inputDollarAmount));
		}
		logger.debug("Inside get change for given dollar amount method end.");
		return new ResponseEntity<ChangeAmount>(changeAmount, HttpStatus.OK);

	}

	@PutMapping(value = "/inputInitialCoinValues")
	public void setInitialCoinValues(@RequestParam(value = "initialCoinInventory", required = true) Integer inputValue)
			throws CoinVendingMachineException {
		logger.debug("Inside set initial coin values amount method start.");
		logger.info("Given initial values for the Inventory{}", inputValue);
		coinVendingMachineService.initialize(inputValue);
		logger.debug("Inside set initial coin values amount method end.");
	}

	@PostMapping(value = "/inputBillsForChange")
	public ResponseEntity<ChangeAmount> getChangeForPossibleDollarAmount(
			@RequestBody CashDenominationRequest cashDenominationRequest) throws CoinVendingMachineException {
		logger.debug("Inside input bills for change amount method start.");
		ChangeAmount changeAmount = new ChangeAmount();
		var totalDollarAmount = coinVendingMachineService.getTotalDollarAmount(cashDenominationRequest);
		changeAmount.setChangeDetails(coinVendingMachineService.getChange(totalDollarAmount));
		return new ResponseEntity<ChangeAmount>(changeAmount, HttpStatus.OK);
	}

}
