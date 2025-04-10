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

    @Override
    public double percentage(double value, double percent) {
        // Calculate percent of a value (e.g., 10% of 200 = 20)
        return (value * percent) / 100;
    }
    
    @Override
    public double percentageOf(double part, double total) {
        // Calculate what percentage part is of total (e.g., 20 is 10% of 200)
        if (total == 0) {
            throw new ArithmeticException("Total cannot be zero");
        }
        return (part / total) * 100;
    }
    
    @Override
    public double percentageChange(double oldValue, double newValue) {
        // Calculate percentage change (e.g., from 100 to 120 is a 20% increase)
        if (oldValue == 0) {
            throw new ArithmeticException("Old value cannot be zero");
        }
        return ((newValue - oldValue) / Math.abs(oldValue)) * 100;
    }
}