package com.adp.coinvendingmachine.entity;

import java.util.Map;

public class ChangeAmount {
	
	Map<String,Integer> changeDetails;

	public Map<String, Integer> getChangeDetails() {
		return changeDetails;
	}

	public void setChangeDetails(Map<String, Integer> changeDetails) {
		this.changeDetails = changeDetails;
	}

	@Override
	public String toString() {
		return "ChangeAmount [changeDetails=" + changeDetails + "]";
	}
	
	
}
