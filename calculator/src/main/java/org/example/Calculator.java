package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Calculator {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a value: ");

        String value = scanner.nextLine().toLowerCase();
        double result = calculator(value);
        System.out.println("Calculated result: " + result);
    }

    private static final HashMap<String, Integer> numberMap = new HashMap<>();

    static {
        numberMap.put("zero", 0);
        numberMap.put("one", 1);
        numberMap.put("two", 2);
        numberMap.put("three", 3);
        numberMap.put("four", 4);
        numberMap.put("five", 5);
        numberMap.put("six", 6);
        numberMap.put("seven", 7);
        numberMap.put("eight", 8);
        numberMap.put("nine", 9);
        numberMap.put("ten", 10);
    }

    public static int parseNumber(String enteredNumber) {
        Integer number = numberMap.get(enteredNumber.toLowerCase());
        if (number == null) {
            System.out.println("Invalid number");
        }
        return number;
    }

    public static double calculator(String value) {
        String[] input = value.split("\\s+");
        List<String> operators = new ArrayList<>();
        List<Double> numbers = new ArrayList<>();

        for (int i = 0; i < input.length; i++) {
            if (i % 2 == 0) {
                numbers.add((double) parseNumber(input[i]));
            } else {
                operators.add(input[i]);
            }
        }

        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i).equals("multiplied-by") || operators.get(i).equals("times")) {
                numbers.set(i, numbers.get(i) * numbers.get(i + 1));
                numbers.remove(i + 1);
                operators.remove(i);
                i--;
            } else if (operators.get(i).equals("divided-by") || operators.get(i).equals("over")) {
                if (numbers.get(i + 1) == 0) {
                    System.out.println("Cannot be divided by zero");
                }
                numbers.set(i, numbers.get(i) / numbers.get(i + 1));
                numbers.remove(i + 1);
                operators.remove(i);
                i--;
            }
        }

        double result = numbers.get(0);

        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i).equals("add") || operators.get(i).equals("plus")) {
                result += numbers.get(i + 1);
            } else if (operators.get(i).equals("substract") || operators.get(i).equals("minus") || operators.get(i).equals("less")) {
                result -= numbers.get(i + 1);
            }
        }

        return result;
    }

}
