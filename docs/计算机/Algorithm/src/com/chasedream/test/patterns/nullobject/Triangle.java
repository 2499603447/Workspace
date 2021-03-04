/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.test.patterns.nullobject;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/5/23 15:46
 */
public class Triangle implements Shape {
    /**
     * Three side's length.
     */
    private final double a;
    private final double b;
    private final double c;

    public Triangle() {
        this(1.0d, 1.0d, 1.0d);
    }

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double area() {
        // Using Heron's formula:
        // Area = SquareRoot(s * (s - a) * (s - b) * (s - c))
        // where s = (a + b + c) / 2, or 1/2 of the perimeter of the triangle
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public double perimeter() {
        // P = a + b + c
        return a + b + c;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Triangle with area: " + area() + " and perimeter: " + perimeter());
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
