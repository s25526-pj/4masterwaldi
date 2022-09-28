package Zadanie2;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Calculator calculator1 = (a, b) -> a + b;
        Calculator calculator2 = (a, b) -> a - b;
        Calculator calculator3 = (a, b) -> a * b;
        Calculator calculator4 = (a, b) -> a / b;


        Map<String, Calculator> operationMap = new HashMap<>();
        operationMap.put("+", calculator1);
        operationMap.put("-", calculator2);
        operationMap.put("*", calculator3);
        operationMap.put("/", calculator4);
        System.out.println(operationMap.get("+").calculate(10, 20));

    }


}
