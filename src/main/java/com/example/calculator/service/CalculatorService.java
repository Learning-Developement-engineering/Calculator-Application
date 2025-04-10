package com.example.calculator.service;

public interface CalculatorService {
    double add(double a, double b);
    double subtract(double a, double b);
    double multiply(double a, double b);
    double divide(double a, double b);
    double power(double base, double exponent);
    double percentage(double value, double percent);
    double percentageOf(double part, double total);
    double percentageChange(double oldValue, double newValue);
}