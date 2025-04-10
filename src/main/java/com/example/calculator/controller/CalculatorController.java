package com.example.calculator.controller;

import com.example.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/add")
    public ResponseEntity<Double> add(@RequestParam double a, @RequestParam double b) {
        return ResponseEntity.ok(calculatorService.add(a, b));
    }

    @GetMapping("/subtract")
    public ResponseEntity<Double> subtract(@RequestParam double a, @RequestParam double b) {
        return ResponseEntity.ok(calculatorService.subtract(a, b));
    }

    @GetMapping("/multiply")
    public ResponseEntity<Double> multiply(@RequestParam double a, @RequestParam double b) {
        return ResponseEntity.ok(calculatorService.multiply(a, b));
    }

    @GetMapping("/divide")
    public ResponseEntity<Double> divide(@RequestParam double a, @RequestParam double b) {
        try {
            return ResponseEntity.ok(calculatorService.divide(a, b));
        } catch (ArithmeticException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/power")
    public ResponseEntity<Double> power(@RequestParam double base, @RequestParam double exponent) {
        return ResponseEntity.ok(calculatorService.power(base, exponent));
    }

    @GetMapping("/percentage")
    public ResponseEntity<Double> percentage(@RequestParam double value, @RequestParam double percent) {
        try {
            return ResponseEntity.ok(calculatorService.percentage(value, percent));
        } catch (ArithmeticException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/percentageOf")
    public ResponseEntity<Double> percentageOf(@RequestParam double part, @RequestParam double total) {
        try {
            return ResponseEntity.ok(calculatorService.percentageOf(part, total));
        } catch (ArithmeticException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/percentageChange")
    public ResponseEntity<Double> percentageChange(@RequestParam double oldValue, @RequestParam double newValue) {
        try {
            return ResponseEntity.ok(calculatorService.percentageChange(oldValue, newValue));
        } catch (ArithmeticException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}