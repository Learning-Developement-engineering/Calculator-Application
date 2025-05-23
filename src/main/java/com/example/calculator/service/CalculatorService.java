package com.example.calculator.service;

public interface CalculatorService {
    double add(double a, double b);
    double subtract(double a, double b);
    double multiply(double a, double b);
    double divide(double a, double b);
    double power(double base, double exponent);
<<<<<<< HEAD
=======
    double percentage(double value, double percent);
    double percentageOf(double part, double total);
    double percentageChange(double oldValue, double newValue);
>>>>>>> 1b724b3d0410608514cf2fce78b34614dcf3fcb6
}