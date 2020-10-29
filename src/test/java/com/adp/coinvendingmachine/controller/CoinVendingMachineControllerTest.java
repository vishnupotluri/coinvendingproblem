package com.adp.coinvendingmachine.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.adp.coinvendingmachine.entity.CashDenominationRequest;
import com.adp.coinvendingmachine.service.CoinVendingMachineService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CoinVendingMachineControllerTest {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	 @Autowired
	 private MockMvc mockMvc;
	 
	 @Autowired
	 private TestRestTemplate restTemplate;
	 
	 @Mock
	 CoinVendingMachineService coinVendingMachineService;

	 @InjectMocks
	 CoinVendingMachineController coinVendingMachineController;
	 
	@Before
    public void init() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(coinVendingMachineController).build();
    }
	
	@Test
	public void testGetChangeForValidDollarAmount() throws Exception {
		mockMvc.perform(post("/getChangeForGivenDollarAmount?inputDollarAmount=20"))
				.andExpect(status().isOk())
				.andReturn();
	}

	@Test
	public void testSetInitialCoinValues() throws Exception {
		mockMvc.perform(put("/inputInitialCoinValues?initialCoinInventory=100")).andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void testGetChangeForPossibleDollarAmount() throws Exception {
		CashDenominationRequest cashDenominationRequest = new CashDenominationRequest();
		cashDenominationRequest.setDollar(1);
		cashDenominationRequest.setTwoDollar(1);
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(cashDenominationRequest), headers);

        ResponseEntity<String> response = restTemplate.exchange("/inputBillsForChange", HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());		
	}

}
