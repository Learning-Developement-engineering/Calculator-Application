package com.example.calculator;

import com.example.calculator.controller.CalculatorController;
import com.example.calculator.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculatorApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private CalculatorController calculatorController;

	@Autowired
	private CalculatorService calculatorService;

	@Test
	void contextLoads() {
		assertThat(calculatorController).isNotNull();
		assertThat(calculatorService).isNotNull();
	}

	@Test
	void testAddEndpoint() {
		String url = "http://localhost:" + port + "/api/calculator/add?a=5&b=3";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("8.0");
	}

	@Test
	void testAddEndpointWithNegativeNumbers() {
		String url = "http://localhost:" + port + "/api/calculator/add?a=-10&b=-15";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("-25.0");
	}

	@Test
	void testAddEndpointWithZero() {
		String url = "http://localhost:" + port + "/api/calculator/add?a=0&b=5";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("5.0");
	}

	@Test
	void testAddEndpointWithLargeNumbers() {
		String url = "http://localhost:" + port + "/api/calculator/add?a=1000000&b=2000000";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("3000000.0");
	}

	@Test
	void testSubtractEndpoint() {
		String url = "http://localhost:" + port + "/api/calculator/subtract?a=10&b=4";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("6.0");
	}

	@Test
	void testSubtractEndpointResultingInNegative() {
		String url = "http://localhost:" + port + "/api/calculator/subtract?a=4&b=10";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("-6.0");
	}

	@Test
	void testSubtractEndpointWithDecimalPrecision() {
		String url = "http://localhost:" + port + "/api/calculator/subtract?a=10.5&b=3.25";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("7.25");
	}

	@Test
	void testMultiplyEndpoint() {
		String url = "http://localhost:" + port + "/api/calculator/multiply?a=7&b=8";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("56.0");
	}

	@Test
	void testMultiplyEndpointWithZero() {
		String url = "http://localhost:" + port + "/api/calculator/multiply?a=7&b=0";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("0.0");
	}

	@Test
	void testMultiplyEndpointWithNegativeNumbers() {
		String url = "http://localhost:" + port + "/api/calculator/multiply?a=-7&b=8";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("-56.0");
	}

	@Test
	void testMultiplyEndpointWithTwoNegativeNumbers() {
		String url = "http://localhost:" + port + "/api/calculator/multiply?a=-7&b=-3";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("21.0");
	}

	@Test
	void testDivideEndpoint() {
		String url = "http://localhost:" + port + "/api/calculator/divide?a=20&b=5";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("4.0");
	}

	@Test
	void testDivideEndpointWithRemainder() {
		String url = "http://localhost:" + port + "/api/calculator/divide?a=10&b=3";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("3.3333333333333335");
	}

	@Test
	void testDivideByZeroEndpoint() {
		String url = "http://localhost:" + port + "/api/calculator/divide?a=10&b=0";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	void testDivideZeroByNumberEndpoint() {
		String url = "http://localhost:" + port + "/api/calculator/divide?a=0&b=5";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("0.0");
	}

	@Test
	void testInvalidParametersEndpoint() {
		String url = "http://localhost:" + port + "/api/calculator/add?a=abc&b=5";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	void testMissingParametersEndpoint() {
		String url = "http://localhost:" + port + "/api/calculator/add?a=10";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	void testEmptyParametersEndpoint() {
		String url = "http://localhost:" + port + "/api/calculator/add?a=&b=";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	void testNegativeNumbersEndpoint() {
		String url = "http://localhost:" + port + "/api/calculator/add?a=-10&b=-5";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("-15.0");
	}

	@Test
	void testDecimalNumbersEndpoint() {
		String url = "http://localhost:" + port + "/api/calculator/multiply?a=2.5&b=4.5";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("11.25");
	}

	@Test
	void testVeryLargeNumbersEndpoint() {
		String url = "http://localhost:" + port + "/api/calculator/multiply?a=1000000&b=1000000";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("1.0E12");
	}

	@Test
	void testVerySmallNumbersEndpoint() {
		String url = "http://localhost:" + port + "/api/calculator/divide?a=0.0000001&b=10";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("1.0E-8");
	}

	@Test
	void testEndpointNotFound() {
		String url = "http://localhost:" + port + "/api/calculator/power?a=2&b=3";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
}