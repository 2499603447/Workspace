package com.chasedream.test.interfacetest;

import com.chasedream.utils.Out;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dezhou
 */
public class InterfaceAImpl implements InterfaceA, Cloneable {
    @Override
    public InterfaceAImpl clone() throws CloneNotSupportedException {
        return (InterfaceAImpl) super.clone();
    }

    @Override
    public void showDefault() {
        Out.print("InterfaceAImpl override superClass default method.");
    }

    @Override
    public void print() {
        Out.print("InterfaceAImpl override superClass common method.");
    }

    public static void main(String[] args) {
        Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > 5;
            }
        };
        for (int i = 0; i < 7; i++) {
            map.put(i, i);
        }
        map.get(4);

        Out.print(map.keySet());
        List<? extends Apple> extendsList = new ArrayList<>();
        List<? super Fruit> superList = new ArrayList<>();
        superList.add(new Apple());
        superList.add(new BigApple());
        for (Object o : superList) {

        }

        for (Apple apple : extendsList) {

        }
    }

    static class Food {
    }

    static class Meat extends Food {

    }

    static class Fruit extends Food {

    }

    static class Apple extends Fruit {

    }

    static class BigApple extends Apple {

    }
}