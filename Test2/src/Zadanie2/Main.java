package Zadanie2;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        
        Map<String, Calculator> operationMap = new HashMap<>();
        operationMap.put("+", (a, b) -> a + b);
        operationMap.put("-", (a, b) -> a - b);
        operationMap.put("*", (a, b) -> a * b);
        operationMap.put("/", (a, b) -> a / b);
        System.out.println(operationMap.get("+").calculate(10, 20));

    }


}
