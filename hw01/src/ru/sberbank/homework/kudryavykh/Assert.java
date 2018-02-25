package ru.sberbank.homework.kudryavykh;

public class Assert {

    public static void assertInt(String message, int expect, int real) {
        if (real != expect) {
            throw new AssertionError();
        }


        else { System.out.println("Test assertInt successful " + message);
        }
    }

    public static void assertDouble(String message, double expect, double real) {
        if (Math.abs(real - expect) > 0.00000000000000099) {
            throw new AssertionError("Test assertDouble failed");
        }
        else {
            System.out.println("Test assertDouble successful" + message);
        }
    }
}
