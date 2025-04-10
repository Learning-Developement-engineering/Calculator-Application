package com.example.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public double add(double a, double b) {
        return a + b;
    }

    @Override
    public double subtract(double a, double b) {
        return a - b;
    }

    @Override
    public double multiply(double a, double b) {
        return a * b;
    }

    @Override
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        // Handle zero numerator specifically to ensure +0.0 rather than -0.0
        if (a == 0) {
            return 0.0;
        }
        return a / b;
    }

    @Override
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }
}