/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.test.patterns.nullobject;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/5/23 16:33
 */
public class ShapeMainJava8 {
    public static void main(String[] args) {
        String[] shapeTypes = new String[]{"Circle", null, "Triangle", "Pentagon", "Rectangle", "Trapezoid"};
        Arrays.asList(shapeTypes).stream().forEach(shapeType -> {
            Optional<Shape> optionalShape = ShapeFactoryJava8.createShape(shapeType);
            optionalShape.ifPresent((shape) -> {
                // null-check is done by ifPresent of Optional
                System.out.println("Shape area: " + shape.area());
                System.out.println("Shape Perimeter: " + shape.perimeter());
                shape.draw();
                System.out.println();
            });
        });
    }
}
