package ru.sberbank.homework.solovyov;

import ru.sberbank.homework.common.Calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class SimpleCalculator implements Calculator {

    private String resultString;
    private boolean isNextStep;

    public SimpleCalculator() {
        System.out.println("**********************");
        System.out.println("Welcome to Calculator!");
        System.out.println("**********************");
        System.out.println("Enter your arithmetic problem for calculation in the format a @ b, where @ is an operation +,-,*,/.");
        System.out.println("or enter q(uit) for exit");

        isNextStep = false;
        resultString = null;
    }

    @Override
    public String calculate(String userInput) {

        switch (userInput.charAt(0)) {
            case 'q':
                System.out.println("See you later.");
                return "quit";
            case 'n':
                isNextStep = false;
                resultString = "";
                return "new";
            default:
                if (isNextStep) {
                    resultString += " " + userInput;
                }
        }

        if (isNextStep) {
            parseAndCalc(resultString);
        } else {
            parseAndCalc(userInput);
        }

        if (resultString != null) {
            System.out.println("= " + resultString);
            System.out.println("Type:\n- the next operation and the operand (format: @ c) to continue calculation\n" +
                    "- n(ew) for new calculation example\n- q(uit) for exit.");
            isNextStep = true;
            return resultString;
        } else {
            return "error";
        }
    }

    private void parseAndCalc(String userInput) {
        Scanner scanner = new Scanner(userInput);

        try {
            BigDecimal firstNumber = getNumber(scanner);
            Operation operation = getOperation(scanner);
            BigDecimal secondNumber = getNumber(scanner);


            BigDecimal result = null;
            switch (operation) {
                case PLUS:
                    result = firstNumber.add(secondNumber);
                    break;
                case SUBTRACTION:
                    result = firstNumber.subtract(secondNumber);
                    break;
                case MULTIPLICATION:
                    result = firstNumber.multiply(secondNumber);
                    break;
                case DIVISION:
                    if (secondNumber.compareTo(BigDecimal.ZERO) != 0) {
                        result = firstNumber.divide(secondNumber, 2, RoundingMode.HALF_UP);
                    } else {
                        throw new ArithmeticException("Division by zero!");
                    }
            }

            if (result.remainder(BigDecimal.ONE).signum() == 0) {
                result = result.setScale(0, BigDecimal.ROUND_HALF_UP);
            } else {
                result = result.setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            resultString = result.toString();
        } catch (IllegalArgumentException | NullPointerException | ArithmeticException exception) {
            System.out.println("error > wrong expression: " + exception.getMessage());
            isNextStep = false;
            resultString = null;
            System.out.println("Enter your arithmetic problem for calculation in the format a @ b, where @ is an operation +,-,*,/.");
            System.out.println("or enter q(uit) for exit");
        }
    }

    private Operation getOperation(Scanner scanner) {
        if (!scanner.hasNext()) {
            throw new NullPointerException("No operation to recognize in string.");
        }
        String sign = scanner.next();
        if (sign.length() > 1) {
            throw new IllegalArgumentException("Not valid math sign " + sign);
        }
        Operation operation = Operation.getBySign(sign.charAt(0));
        if (operation == null) {
            throw new IllegalArgumentException("Can't parse operation " + sign);
        }
        return operation;
    }

    private BigDecimal getNumber(Scanner scanner) {
        if (!scanner.hasNext()) {
            throw new NullPointerException("No number to recognize in string.");
        }

        String input = scanner.next().replace("_", "");
        boolean isLongNumber = input.endsWith("L");
        boolean isRealNumber = input.indexOf('.') != -1;

        if (isRealNumber & isLongNumber) {
            throw new NumberFormatException("Number format is not valid: " + input);
        }

        if (isRealNumber) {
            return new BigDecimal(input);
        }

        if (isLongNumber) {
            input = input.substring(0, input.length() - 1);
        }

        int prefixPointer = 0;
        int sign;
        switch (input.charAt(prefixPointer)) {
            case '+':
                sign = 1;
                prefixPointer++;
                break;
            case '-':
                sign = -1;
                prefixPointer++;
                break;
            default:
                sign = 1;
        }
        if (prefixPointer >= input.length()) {
            throw new IllegalArgumentException("Number format is not valid: " + input);
        }

        try {
            if (input.charAt(prefixPointer) == '0' & prefixPointer + 1 < input.length()) {
                String number;
                switch (input.charAt(prefixPointer + 1)) {
                    case 'x':
                        number = input.substring(prefixPointer + 2);
                        return new BigDecimal(sign * (isLongNumber ? Long.parseLong(number, 16) : Integer.parseInt(number, 16)));
                    case 'b':
                        number = input.substring(prefixPointer + 2);
                        return new BigDecimal(sign * (isLongNumber ? Long.parseLong(number, 2) : Integer.parseInt(number, 2)));
                    default:
                        number = input.substring(prefixPointer + 1);
                        return new BigDecimal(sign * (isLongNumber ? Long.parseLong(number, 8) : Integer.parseInt(number, 8)));
                }
            } else {
                return new BigDecimal(input);
            }
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("Number format is not valid: " + input);
        }
    }
}

