/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.test.patterns.nullobject;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/5/23 15:52
 */
public class ShapeFactory {
    public static Shape createShape(String shapeType) {
        Shape shape = null;
        if ("Circle".equalsIgnoreCase(shapeType)) {
            shape = new Circle();
        } else if ("Rectangle".equalsIgnoreCase(shapeType)) {
            shape = new Rectangle();
        } else if ("Triangle".equalsIgnoreCase(shapeType)) {
            shape = new Triangle();
        } else {
            shape = new NullShape();
        }
        return shape;
    }
}
