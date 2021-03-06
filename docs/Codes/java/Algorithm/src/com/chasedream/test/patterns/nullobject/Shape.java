/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.test.patterns.nullobject;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/5/23 15:16
 */
public interface Shape {
    /**
     * Calculate this shape area.
     *
     * @return shape's area
     */
    double area();

    /**
     * Calculate shape perimeter
     *
     * @return shape's perimeter
     */
    double perimeter();

    /**
     * Draw the shape.
     */
    void draw();

    /**
     * nice to have method to indicate null object
     *
     * @return true if the object is null
     */
    boolean isNull();
}
