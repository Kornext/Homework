package ru.sberbank.homework.kudryavukh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculate implements ru.sberbank.homework.common.Calculator {

    private boolean errorFlag = false;
    private String previousResult = "";
    private Parse parse = new Parse();


    public String calculate(String inputText) {

        errorFlag = false;
        String value = "";
        inputText = previousResult + inputText;
        List<String> sArray = new ArrayList<>(Arrays.asList(inputText.split(" ")));
        if (sArray.size() == 1) {
            return "error > wrong expression";
        }
        if (firstAndLastIsOperator(sArray)) {
            return "error > wrong expression";
        }
        if (notContainsOperators(sArray)) {
            return "error > wrong expression";
        }
        for (int i = 0; i < sArray.size(); ) {
            value = Parse.validationAndParser(sArray.get(i));
            if (value == null) {
                errorFlag = true;
                value = sArray.get(i);
                sArray.clear();
                break;
            } else {
                sArray.set(i, value);
                i++;
            }
        }
        if (errorFlag == false) {
            String result = computation(sArray, "*", "/");
            if (result.contains("Ошибка")) {
                return "error > wrong expression";
            }
            result = computation(sArray, "+", "-");
            if (result.contains("Ошибка")) {
                return "error > wrong expression";
            }
        } else {
            return "error > " + value;
        }
        if (sArray.size() == 2) {
            previousResult = parse.output(sArray.get(1)) + " ";
            return parse.output(sArray.get(1));
        }
        previousResult = parse.output(sArray.get(0)) + " ";
        return parse.output(sArray.get(0));
    }

    /**
     * Метод проходит по всему массиву, ищет операторы и вычисляет подвыражения из двух операндов и оператора.
     * В параметры передаются пары операндов: '*' и '/' для первого прохода, '+' и '-' для второго.
     *
     * @param sArray
     * @param firstSymbol  - символ + или *
     * @param secondSymbol - символ - или /
     * @return
     */

    private String computation(List<String> sArray, String firstSymbol, String secondSymbol) {
        String result;
        for (int i = 1; i < sArray.size(); i++) {
            if (sArray.get(i).equals(firstSymbol) || sArray.get(i).equals(secondSymbol)) {
                if (!neighborElementNotContainOperator(sArray, i)) {
                    return "Ошибка ввода! Два знака подрят.";
                }
                OperationEnum operatEnum = OperationEnum.setValue(sArray.get(i).charAt(0));
                if (operatEnum == null) {
                    throw new NullPointerException("Обьект Enum хранит null");
                }
                result = OperationEnum.getResult(operatEnum, Double.parseDouble(sArray.get(i - 1)),
                        Double.parseDouble(sArray.get(i + 1)));
                if (result == null) {
                    return "Ошибка ввода!";
                }
                sArray.set(i, result);
                sArray.remove(i - 1);
                i--;
                sArray.remove(i + 1);
            }
        }
        return "Общая ошибка";
    }

    /**
     * Проверяет соседние элементы оператора, точно ли они являются операндами.
     *
     * @param sArray
     * @param index
     * @return
     */
    private boolean neighborElementNotContainOperator(List<String> sArray, int index) {

        if (sArray.get(index - 1).equals("+")) {
            return false;
        }
        if (sArray.get(index - 1).equals("-")) {
            return false;
        }
        if (sArray.get(index - 1).equals("*")) {
            return false;
        }
        if (sArray.get(index - 1).equals("/")) {
            return false;
        }
        if (sArray.get(index + 1).equals("+")) {
            return false;
        }
        if (sArray.get(index + 1).equals("-")) {
            return false;
        }
        if (sArray.get(index + 1).equals("*")) {
            return false;
        }
        if (sArray.get(index + 1).equals("/")) {
            return false;
        }
        return true;
    }

    /**
     * Проверяет, являются ли первый или последний элемент оператором +-/*
     *
     * @param sArray
     * @return true -> первый или последний элемент - оператор
     */
    private boolean firstAndLastIsOperator(List<String> sArray) {
        if (sArray.get(0).length() == 1) {
            char firstElement = sArray.get(0).charAt(0);
            OperationEnum operationEnum = OperationEnum.setValue(firstElement);
            if (operationEnum != null) {
                return true;
            }
        }
        if (sArray.get(sArray.size() - 1).length() == 1) {
            char lastElement = sArray.get(sArray.size() - 1).charAt(0);
            OperationEnum operationEnum = OperationEnum.setValue(lastElement);
            if (operationEnum != null) {
                return true;
            }
        }
        return false;
    }

    private boolean notContainsOperators(List<String> sArray) {
        for (int i = 0; i < sArray.size(); i++) {
            if (sArray.get(i).length() == 1) {
                char element = sArray.get(i).charAt(0);
                OperationEnum operationEnum = OperationEnum.setValue(element);
                if (operationEnum != null) {
                    return false;
                }
            }
        }
        return true;
    }
}
