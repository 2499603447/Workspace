/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.test.patterns.nullobject;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/5/23 15:41
 */
public class Circle implements Shape {
    /**
     * Circle radius.
     */
    private final double radius;

    public Circle() {
        this(1.0d);
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        // Area = π r^2
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double perimeter() {
        // Perimeter = 2πr
        return 2 * Math.PI * radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Circle with area: " + area() + " and perimeter: " + perimeter());
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
