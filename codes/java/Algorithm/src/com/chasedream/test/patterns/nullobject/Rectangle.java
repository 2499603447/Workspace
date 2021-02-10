/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.test.patterns.nullobject;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/5/23 15:44
 */
public class Rectangle implements Shape {
    /**
     * Rectangle's width
     */
    private final double width;
    /**
     * Rectangle's height
     */
    private final double height;

    public Rectangle() {
        this(1.0d, 1.0d);
    }

    public Rectangle(double width, double length) {
        this.width = width;
        this.height = length;
    }

    @Override
    public double area() {
        // A = w * l
        return width * height;
    }

    @Override
    public double perimeter() {
        // P = 2(w + l)
        return 2 * (width + height);
    }

    @Override
    public void draw() {
        System.out.println("Drawing Rectangle with area: " + area() + " and perimeter: " + perimeter());
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
