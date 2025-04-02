package com.example.calculator.controller;

import com.example.calculator.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
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
    void testDivideByZero() throws Exception {
        // Given
        when(calculatorService.divide(6.0, 0.0)).thenThrow(new ArithmeticException());

        // When & Then
        mockMvc.perform(get("/api/calculator/divide")
                        .param("a", "6.0")
                        .param("b", "0.0"))
                .andExpect(status().isBadRequest());
    }
}