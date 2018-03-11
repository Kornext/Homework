package ru.sberbank.homework.kudryavukh;

import ru.sberbank.homework.common.Calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


//Доп задача - калькулятор работает с длинными выражениями вида a + b * c / d - e. Без скобок

public class Main {

    public static void main(String[] args) {

        String previousResult = "";
        System.out.print("Введите выражение: ");
        Calculator calc = new Calculate();
        for (; ; ) {
            if (previousResult.contains("error")) {
                previousResult = "";
            }
            Scanner in = new Scanner(System.in);
            String inputText = in.nextLine();
            previousResult = calc.calculate(inputText);
            System.out.print(" = " + previousResult + " ");
        }
    }
}