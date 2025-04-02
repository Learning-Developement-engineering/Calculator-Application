package com.example.calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceImplTest {

    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorServiceImpl();
    }

    @Test
    void testAdd() {
        assertEquals(5.0, calculatorService.add(2.0, 3.0));
        assertEquals(0.0, calculatorService.add(2.0, -2.0));
        assertEquals(-5.0, calculatorService.add(-2.0, -3.0));
    }

    @Test
    void testSubtract() {
        assertEquals(-1.0, calculatorService.subtract(2.0, 3.0));
        assertEquals(4.0, calculatorService.subtract(2.0, -2.0));
        assertEquals(1.0, calculatorService.subtract(-2.0, -3.0));
    }

    @Test
    void testMultiply() {
        assertEquals(6.0, calculatorService.multiply(2.0, 3.0));
        assertEquals(-4.0, calculatorService.multiply(2.0, -2.0));
        assertEquals(6.0, calculatorService.multiply(-2.0, -3.0));
    }

    @Test
    void testDivide() {
        assertEquals(2.0, calculatorService.divide(6.0, 3.0));
        assertEquals(-1.0, calculatorService.divide(2.0, -2.0));
        assertEquals(0.5, calculatorService.divide(-1.0, -2.0));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculatorService.divide(2.0, 0.0));
    }
}