/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.test.patterns.singleton;

/**
 * Singleton demo.
 *
 * @author Zhang Dezhou @Description
 * @date 2020/7/12 14:57
 */
public class SingletonDemo {
  /**
   * Entrance of program.
   *
   * @param args execution parameters
   */
  public static void main(String[] args) {
    Thread t1 =
        new Thread(
            () -> {
              LazyModeSingleton obj1 = LazyModeSingleton.getInstance(0);
              obj1.setValue(10);
              obj1.showMessage(0);
              obj1.showMessage(2000);
            },
            "thread1");
    t1.start();

    Thread t2 =
        new Thread(
            () -> {
              LazyModeSingleton obj2 = LazyModeSingleton.getInstance(0);
              obj2.setValue(20);
              obj2.showMessage(0);
            },
            "thread2");
    t2.start();
  }
}
