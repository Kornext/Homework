package ru.sberbank.homework.kudryavykh;

public class SimpleCalculatorTest {

    public static void testIntAdd(int one, int two, int result) {
        Assert.assertInt("| Operation add", result, SimpleCalculator.add(one, two));
    }

    public static void testIntSubstract(int one, int two, int result) {
        Assert.assertInt("| Operation Substract", result, SimpleCalculator.substract(one, two));
    }

    public static void testIntDivide(int one, int two, int result) {
        Assert.assertInt("| Operation Divide", result, SimpleCalculator.divide(one, two));
    }

    public static void testIntMultiply(int one, int two, int result) {
        Assert.assertInt("| Operation Multiply", result, SimpleCalculator.multiply(one, two));
    }

    public static void testDoubleAdd(double one, double two, double result) {
        Assert.assertDouble("| Operation add", result, SimpleCalculator.add(one, two));
    }

    public static void testDoubleSubstract(double one, double two, double result) {
        Assert.assertDouble("| Operation Substract", result, SimpleCalculator.subtract(one, two));
    }

    public static void testDoubleDivide(double one, double two, double result) {
        Assert.assertDouble("| Operation Divide", result, SimpleCalculator.divide(one, two));
    }

    public static void testDoubleMuliply(double one, double two, double result) {
        Assert.assertDouble("| Operation Multiply", result, SimpleCalculator.multiply(one, two));
    }

    public static void testIntDivisionZero(int one, int two) {
        try {
            SimpleCalculator.divide(one, two);
            System.out.println("Test IntDivisionByZero successful");
        } catch (ArithmeticException e) {
            System.out.println("Test IntDivisionByZero failed");
        }
    }

    public static void testDoubleDivisionZero(int one, double two) {
        try {
            SimpleCalculator.divide(one, two);
            System.out.println("Test DoubleDivisionByZero successful");
        } catch (ArithmeticException e) {
            System.out.println("Test DoubleDivisionByZero failed");
        }

    }
}
