package ru.sberbank.homework.kashin.main.util;

import ru.sberbank.homework.kashin.main.model.Expression;
import ru.sberbank.homework.kashin.main.model.expressions.Addition;
import ru.sberbank.homework.kashin.main.model.expressions.Division;
import ru.sberbank.homework.kashin.main.model.expressions.Multiplication;
import ru.sberbank.homework.kashin.main.model.expressions.Subtraction;

public class FactoryExpression {
    public static Expression getExpression(Character operator) {
        switch (operator) {
            case '+':
                return new Addition();
            case '-':
                return new Subtraction();
            case '*':
                return new Multiplication();
            case '/':
                return new Division();
            default:
                throw new RuntimeException(String.format("error > %s", operator));
        }
    }
}
