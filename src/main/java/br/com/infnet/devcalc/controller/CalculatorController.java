package br.com.infnet.devcalc.controller;

import br.com.infnet.devcalc.dto.CalculationResponse;
import br.com.infnet.devcalc.service.CalculatorService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calc")
@RequiredArgsConstructor
public class CalculatorController {
  private final CalculatorService calculatorService;

  @GetMapping("/add")
  public CalculationResponse add(@RequestParam double a, @RequestParam double b) {
    return new CalculationResponse("add", calculatorService.add(a, b));
  }

  @GetMapping("/subtract")
  public CalculationResponse subtract(@RequestParam double a, @RequestParam double b) {
    return new CalculationResponse("subtract", calculatorService.subtract(a, b));
  }

  @GetMapping("/multiply")
  public CalculationResponse multiply(@RequestParam double a, @RequestParam double b) {
    return new CalculationResponse("multiply", calculatorService.multiply(a, b));
  }

  @GetMapping("/divide")
  public CalculationResponse divide(@RequestParam double a, @RequestParam double b) {
    return new CalculationResponse("divide", calculatorService.divide(a, b));
  }

  @GetMapping("/sqrt")
  public CalculationResponse sqrt(@RequestParam double x) {
    return new CalculationResponse("sqrt", calculatorService.sqrt(x));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> handleIllegalArgument(IllegalArgumentException exception) {
    return Map.of("error", exception.getMessage());
  }
}
