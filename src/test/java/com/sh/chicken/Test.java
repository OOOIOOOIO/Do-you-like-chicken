package com.sh.chicken;

public class Test {
    private static final String BEARER = "Bearer ";

    public static void main(String[] args) {

        String aa = "Bearer eadfeadfafsdfs";

        String substring = aa.substring(BEARER.length());

        System.out.println(substring);

    }
}
