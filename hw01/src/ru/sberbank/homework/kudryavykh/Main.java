package ru.sberbank.homework.kudryavykh;

import java.lang.reflect.Method;

public class Main {

    /*
    @author Kornext
     */

    public static void main(String[] args) throws Exception{

        SimpleCalculatorTest.testIntAdd(10,5,15);
        SimpleCalculatorTest.testIntSubstract(10,5,5);
        SimpleCalculatorTest.testIntDivide(10,5,2);
        SimpleCalculatorTest.testIntMultiply(10,5,50);
        SimpleCalculatorTest.testIntDivisionZero(5,1);
        SimpleCalculatorTest.testDoubleAdd(10.0,5.0,15.0);
        SimpleCalculatorTest.testDoubleSubstract(10.0,5.0,5.0);
        SimpleCalculatorTest.testDoubleDivide(10.0,5.0,2.0);
        SimpleCalculatorTest.testDoubleMuliply(10.0,5.0,50.0);
        SimpleCalculatorTest.testDoubleDivisionZero(5,1);
    }
}
