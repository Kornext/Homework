package ru.sberbank.homework.kudryavukh;

public enum OperationEnum {

    ADD("+"), SUBSTRACT("-"), MULTIPLY("*"), DIVIDE("/");

    private String symbol;

    OperationEnum(String translateEnum) {
        this.symbol = translateEnum;
    }

    private String getSymbol() {
        return symbol;
    }

    public static String getResult(OperationEnum objectEnum, double first, double second) {
        String resultOperation;
        switch (objectEnum) {
            case ADD:
                return resultOperation = Double.toString(first + second);
            case DIVIDE:
                if (second == 0) {
                    return null;
                } else {
                    return resultOperation = Double.toString(first / second);
                }
            case MULTIPLY:
                return resultOperation = Double.toString(first * second);
            case SUBSTRACT:
                return resultOperation = Double.toString(first - second);
        }
        return null;
    }

    public static OperationEnum setValue(char operator) {

        switch (operator) {
            case '+':
                return OperationEnum.ADD;
            case '-':
                return OperationEnum.SUBSTRACT;
            case '/':
                return OperationEnum.DIVIDE;
            case '*':
                return OperationEnum.MULTIPLY;
        }
        return null;
    }
}
