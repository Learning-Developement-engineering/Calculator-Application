package com.example.calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    void testAddWithZero() {
        assertEquals(5.0, calculatorService.add(5.0, 0.0));
        assertEquals(5.0, calculatorService.add(0.0, 5.0));
        assertEquals(0.0, calculatorService.add(0.0, 0.0));
    }

    @Test
    void testAddWithLargeNumbers() {
        assertEquals(2000000.0, calculatorService.add(1000000.0, 1000000.0));
        assertEquals(0.0, calculatorService.add(1000000.0, -1000000.0));
    }

    @Test
    void testAddWithDecimalPrecision() {
        assertEquals(3.3, calculatorService.add(1.1, 2.2), 0.0001);
        assertEquals(0.03, calculatorService.add(0.01, 0.02), 0.0001);
    }

    @ParameterizedTest
    @CsvSource({
            "5.0, 3.0, 8.0",
            "-5.0, -3.0, -8.0",
            "5.0, -3.0, 2.0",
            "0.0, 0.0, 0.0"
    })
    void testAddParameterized(double a, double b, double expected) {
        assertEquals(expected, calculatorService.add(a, b), 0.0001);
    }

    @Test
    void testSubtract() {
        assertEquals(-1.0, calculatorService.subtract(2.0, 3.0));
        assertEquals(4.0, calculatorService.subtract(2.0, -2.0));
        assertEquals(1.0, calculatorService.subtract(-2.0, -3.0));
    }

    @Test
    void testSubtractWithZero() {
        assertEquals(5.0, calculatorService.subtract(5.0, 0.0));
        assertEquals(-5.0, calculatorService.subtract(0.0, 5.0));
        assertEquals(0.0, calculatorService.subtract(0.0, 0.0));
    }

    @Test
    void testSubtractWithDecimalPrecision() {
        assertEquals(0.9, calculatorService.subtract(3.0, 2.1), 0.0001);
        assertEquals(-0.09, calculatorService.subtract(0.01, 0.1), 0.0001);
    }

    @ParameterizedTest
    @CsvSource({
            "5.0, 3.0, 2.0",
            "-5.0, -3.0, -2.0",
            "5.0, -3.0, 8.0",
            "0.0, 0.0, 0.0"
    })
    void testSubtractParameterized(double a, double b, double expected) {
        assertEquals(expected, calculatorService.subtract(a, b), 0.0001);
    }

    @Test
    void testMultiply() {
        assertEquals(6.0, calculatorService.multiply(2.0, 3.0));
        assertEquals(-4.0, calculatorService.multiply(2.0, -2.0));
        assertEquals(6.0, calculatorService.multiply(-2.0, -3.0));
    }

    @Test
    void testMultiplyWithZero() {
        assertEquals(0.0, calculatorService.multiply(5.0, 0.0));
        assertEquals(0.0, calculatorService.multiply(0.0, 5.0));
        assertEquals(0.0, calculatorService.multiply(0.0, 0.0));
    }

    @Test
    void testMultiplyWithDecimalNumbers() {
        assertEquals(6.25, calculatorService.multiply(2.5, 2.5), 0.0001);
        assertEquals(0.25, calculatorService.multiply(0.5, 0.5), 0.0001);
    }

    @Test
    void testMultiplyWithLargeNumbers() {
        assertEquals(1000000.0, calculatorService.multiply(1000.0, 1000.0));
        assertEquals(-1000000.0, calculatorService.multiply(-1000.0, 1000.0));
    }

    @ParameterizedTest
    @CsvSource({
            "5.0, 3.0, 15.0",
            "-5.0, -3.0, 15.0",
            "5.0, -3.0, -15.0",
            "0.0, 5.0, 0.0"
    })
    void testMultiplyParameterized(double a, double b, double expected) {
        assertEquals(expected, calculatorService.multiply(a, b), 0.0001);
    }

    @Test
    void testDivide() {
        assertEquals(2.0, calculatorService.divide(6.0, 3.0));
        assertEquals(-1.0, calculatorService.divide(2.0, -2.0));
        assertEquals(0.5, calculatorService.divide(-1.0, -2.0));
    }

    @Test
    void testDivideWithRemainder() {
        assertEquals(3.3333333333333335, calculatorService.divide(10.0, 3.0));
        assertEquals(0.3333333333333333, calculatorService.divide(1.0, 3.0));
    }

    @Test
    void testDivideWithZeroNumerator() {
        assertEquals(0.0, calculatorService.divide(0.0, 5.0));
        assertEquals(0.0, calculatorService.divide(0.0, -5.0));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculatorService.divide(2.0, 0.0));
        assertThrows(ArithmeticException.class, () -> calculatorService.divide(-2.0, 0.0));
        assertThrows(ArithmeticException.class, () -> calculatorService.divide(0.0, 0.0));
    }

    @ParameterizedTest
    @CsvSource({
            "6.0, 3.0, 2.0",
            "-6.0, -3.0, 2.0",
            "6.0, -3.0, -2.0",
            "0.0, 3.0, 0.0"
    })
    void testDivideParameterized(double a, double b, double expected) {
        assertEquals(expected, calculatorService.divide(a, b), 0.0001);
    }

    @Test
    void testEdgeCases() {
        // Test with very small numbers
        assertEquals(3E-10, calculatorService.add(1E-10, 2E-10), 1E-20);

        // Test with very large numbers
        assertEquals(3E10, calculatorService.add(1E10, 2E10), 1E-5);

        // Test precision with recurring decimals
        assertEquals(0.3333333333333333, calculatorService.divide(1.0, 3.0), 1E-15);
    }

    @Test
    void testPercentage() {
        assertEquals(20.0, calculatorService.percentage(200.0, 10.0));
        assertEquals(0.0, calculatorService.percentage(0.0, 10.0));
        assertEquals(25.0, calculatorService.percentage(100.0, 25.0));
        assertEquals(-15.0, calculatorService.percentage(-150.0, 10.0));
    }

    @Test
    void testPercentageOf() {
        assertEquals(10.0, calculatorService.percentageOf(20.0, 200.0));
        assertEquals(25.0, calculatorService.percentageOf(25.0, 100.0));
        assertEquals(200.0, calculatorService.percentageOf(100.0, 50.0));
        assertEquals(100.0, calculatorService.percentageOf(-50.0, -50.0));
    }

    @Test
    void testPercentageOfZeroTotal() {
        assertThrows(ArithmeticException.class, () -> calculatorService.percentageOf(20.0, 0.0));
    }

    @Test
    void testPercentageChange() {
        assertEquals(20.0, calculatorService.percentageChange(100.0, 120.0));
        assertEquals(-20.0, calculatorService.percentageChange(100.0, 80.0));
        assertEquals(100.0, calculatorService.percentageChange(100.0, 200.0));
        assertEquals(-50.0, calculatorService.percentageChange(100.0, 50.0));
        assertEquals(100.0, calculatorService.percentageChange(-100.0, -200.0));
    }

    @Test
    void testPercentageChangeZeroOldValue() {
        assertThrows(ArithmeticException.class, () -> calculatorService.percentageChange(0.0, 20.0));
    }
}