package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        JavaToKotlinMapper mapper = new JavaToKotlinMapper();
        System.out.println(JavaToKotlinMapper.mapStandardLibrary("Math.max"));
    }
}