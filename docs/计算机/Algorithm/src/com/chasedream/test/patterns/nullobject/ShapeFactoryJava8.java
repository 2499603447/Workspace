/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.test.patterns.nullobject;

import java.util.Optional;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/5/23 16:32
 */
public class ShapeFactoryJava8 {
    public static Optional<Shape> createShape(String shapeType) {
        Shape shape = null;
        if ("Circle".equalsIgnoreCase(shapeType)) {
            shape = new Circle();
        } else if ("Rectangle".equalsIgnoreCase(shapeType)) {
            shape = new Rectangle();
        } else if ("Triangle".equalsIgnoreCase(shapeType)) {
            shape = new Triangle();
        } else {
            // no need to have NullShape anymore
            shape = null;
        }
        // using ofNullable because shape may be not null.
        return Optional.ofNullable(shape);
    }
}
