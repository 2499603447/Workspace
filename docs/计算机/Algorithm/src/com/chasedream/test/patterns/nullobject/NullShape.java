/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.test.patterns.nullobject;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/5/23 15:49
 */
public class NullShape implements Shape {
    @Override
    public double area() {
        return 0.0d;
    }

    @Override
    public double perimeter() {
        return 0.0d;
    }

    @Override
    public void draw() {
        System.out.println("Null object can't be draw");
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
