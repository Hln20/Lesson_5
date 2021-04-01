package ru.geekbrains.homework_5;

import java.util.Arrays;

public class Lesson5 {

    private static final int size = 10000000;
    static final int threads = 4;
    static final int h = size / threads;


    public static void method1() {
        float[] arr = new float[size];
        Arrays.fill(arr, 1);

        long k = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.print("Время работы первого метода = ");
        System.out.println(System.currentTimeMillis() - k);



    }

    public static void method2() {
        float[] arr = new float[size];
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();

        float[][] newarr = new float[threads][h];

        for (int i = 0; i < threads; i++) {
            System.arraycopy(arr, i * h, newarr[i], 0, h);
        }
        System.out.println("Время работы второго метода:");
        System.out.print("Время разбивки массива на " + threads + " = ");
        System.out.println(System.currentTimeMillis() - a);


        for (int j = 0; j < threads; j++) {
            long b = System.currentTimeMillis();
            for (int i = 0; i < h; i++) {
                newarr[j][i] = (float)(newarr[j][i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

            }
            System.out.print("Время расчёта " + (j+1) + "-ого массива = ");
            System.out.println(System.currentTimeMillis() - b);

        }

        long c = System.currentTimeMillis();
        for (int i = 0; i < threads; i++) {

            System.arraycopy(newarr[i], 0, arr, i * h, h);

        }
        System.out.print("Склейка массивов = ");
        System.out.println(System.currentTimeMillis() - c);

    }
}