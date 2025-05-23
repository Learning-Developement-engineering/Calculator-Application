package com.example.calculator.controller;

import com.example.calculator.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculatorService calculatorService;

    @Test
    void testAdd() throws Exception {
        // Given
        when(calculatorService.add(2.0, 3.0)).thenReturn(5.0);

        // When & Then
        mockMvc.perform(get("/api/calculator/add")
                        .param("a", "2.0")
                        .param("b", "3.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("5.0"));
    }

    @Test
    void testAddWithNegativeNumbers() throws Exception {
        // Given
        when(calculatorService.add(-5.0, -3.0)).thenReturn(-8.0);

        // When & Then
        mockMvc.perform(get("/api/calculator/add")
                        .param("a", "-5.0")
                        .param("b", "-3.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("-8.0"));
    }

    @Test
    void testAddWithDecimalNumbers() throws Exception {
        // Given
        when(calculatorService.add(2.5, 3.5)).thenReturn(6.0);

        // When & Then
        mockMvc.perform(get("/api/calculator/add")
                        .param("a", "2.5")
                        .param("b", "3.5"))
                .andExpect(status().isOk())
                .andExpect(content().string("6.0"));
    }

    @Test
    void testAddWithLargeNumbers() throws Exception {
        // Given
        when(calculatorService.add(1000000.0, 2000000.0)).thenReturn(3000000.0);

        // When & Then
        mockMvc.perform(get("/api/calculator/add")
                        .param("a", "1000000.0")
                        .param("b", "2000000.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("3000000.0"));
    }

    @Test
    void testAddWithMissingParameters() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/calculator/add")
                        .param("a", "2.0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSubtract() throws Exception {
        // Given
        when(calculatorService.subtract(5.0, 3.0)).thenReturn(2.0);

        // When & Then
        mockMvc.perform(get("/api/calculator/subtract")
                        .param("a", "5.0")
                        .param("b", "3.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("2.0"));
    }

    @Test
    void testSubtractResultingInNegative() throws Exception {
        // Given
        when(calculatorService.subtract(3.0, 5.0)).thenReturn(-2.0);

        // When & Then
        mockMvc.perform(get("/api/calculator/subtract")
                        .param("a", "3.0")
                        .param("b", "5.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("-2.0"));
    }

    @Test
    void testSubtractWithPrecisionNumbers() throws Exception {
        // Given
        when(calculatorService.subtract(5.25, 3.15)).thenReturn(2.1);

        // When & Then
        mockMvc.perform(get("/api/calculator/subtract")
                        .param("a", "5.25")
                        .param("b", "3.15"))
                .andExpect(status().isOk())
                .andExpect(content().string("2.1"));
    }

    @Test
    void testMultiply() throws Exception {
        // Given
        when(calculatorService.multiply(2.0, 3.0)).thenReturn(6.0);

        // When & Then
        mockMvc.perform(get("/api/calculator/multiply")
                        .param("a", "2.0")
                        .param("b", "3.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("6.0"));
    }

    @Test
    void testMultiplyByZero() throws Exception {
        // Given
        when(calculatorService.multiply(5.0, 0.0)).thenReturn(0.0);

        // When & Then
        mockMvc.perform(get("/api/calculator/multiply")
                        .param("a", "5.0")
                        .param("b", "0.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("0.0"));
    }

    @Test
    void testMultiplyWithNegativeAndPositive() throws Exception {
        // Given
        when(calculatorService.multiply(-2.0, 3.0)).thenReturn(-6.0);

        // When & Then
        mockMvc.perform(get("/api/calculator/multiply")
                        .param("a", "-2.0")
                        .param("b", "3.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("-6.0"));
    }

    @Test
    void testDivide() throws Exception {
        // Given
        when(calculatorService.divide(6.0, 3.0)).thenReturn(2.0);

        // When & Then
        mockMvc.perform(get("/api/calculator/divide")
                        .param("a", "6.0")
                        .param("b", "3.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("2.0"));
    }

    @Test
    void testDivideWithRemainder() throws Exception {
        // Given
        when(calculatorService.divide(10.0, 3.0)).thenReturn(3.3333333333333335);

        // When & Then
        mockMvc.perform(get("/api/calculator/divide")
                        .param("a", "10.0")
                        .param("b", "3.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("3.3333333333333335"));
    }

    @Test
    void testDivideByZero() throws Exception {
        // Given
        when(calculatorService.divide(6.0, 0.0)).thenThrow(new ArithmeticException());

        // When & Then
        mockMvc.perform(get("/api/calculator/divide")
                        .param("a", "6.0")
                        .param("b", "0.0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDivideZeroByNumber() throws Exception {
        // Given
        when(calculatorService.divide(0.0, 5.0)).thenReturn(0.0);

        // When & Then
        mockMvc.perform(get("/api/calculator/divide")
                        .param("a", "0.0")
                        .param("b", "5.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("0.0"));
    }

    @Test
    void testInvalidInputFormat() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/calculator/add")
                        .param("a", "abc")
                        .param("b", "3.0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testEmptyParameters() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/calculator/add")
                        .param("a", "")
                        .param("b", ""))
                .andExpect(status().isBadRequest());
    }
<<<<<<< HEAD
=======

    @Test
    void testPercentageEndpoint() throws Exception {
        // Given
        when(calculatorService.percentage(200.0, 10.0)).thenReturn(20.0);

        // When & Then
        mockMvc.perform(get("/api/calculator/percentage")
                .param("value", "200.0")
                .param("percent", "10.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("20.0"));
    }

    @Test
    void testPercentageOfEndpoint() throws Exception {
        // Given
        when(calculatorService.percentageOf(20.0, 200.0)).thenReturn(10.0);

        // When & Then
        mockMvc.perform(get("/api/calculator/percentageOf")
                .param("part", "20.0")
                .param("total", "200.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("10.0"));
    }

    @Test
    void testPercentageOfZeroTotalEndpoint() throws Exception {
        // Given
        when(calculatorService.percentageOf(20.0, 0.0)).thenThrow(new ArithmeticException());

        // When & Then
        mockMvc.perform(get("/api/calculator/percentageOf")
                .param("part", "20.0")
                .param("total", "0.0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testPercentageChangeEndpoint() throws Exception {
        // Given
        when(calculatorService.percentageChange(100.0, 120.0)).thenReturn(20.0);

        // When & Then
        mockMvc.perform(get("/api/calculator/percentageChange")
                .param("oldValue", "100.0")
                .param("newValue", "120.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("20.0"));
    }

    @Test
    void testPercentageChangeZeroOldValueEndpoint() throws Exception {
        // Given
        when(calculatorService.percentageChange(0.0, 120.0)).thenThrow(new ArithmeticException());

        // When & Then
        mockMvc.perform(get("/api/calculator/percentageChange")
                .param("oldValue", "0.0")
                .param("newValue", "120.0"))
                .andExpect(status().isBadRequest());
    }
>>>>>>> 1b724b3d0410608514cf2fce78b34614dcf3fcb6
}