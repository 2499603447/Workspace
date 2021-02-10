/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.test.interfacetest;

/**
 * @author dezhou
 */
public interface InterfaceA {
    //String a = "";

    /**
     * static method
     */
    static void showStatic() {
        System.out.println("InterfaceA++showStatic");
    }

    strictfp static void out() {
        System.out.println("InterfaceA++showStatic");
    }

    /**
     * default method
     */
    public default void showDefault() {
        // do nothing
        char a = '\u0013';
        float f = 45.32f;
        double d = 45.32;
    }

    /**
     * common Method
     */
    public abstract void print();

}