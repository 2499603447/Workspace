/*
 * Copyright (c) 2019-2020 ,Chase Dream Ltd. All Rights Reserved.
 */

package com.chasedream.test.patterns.singleton;

import com.chasedream.utils.Out;

/**
 * @author Zhang Dezhou @Description
 * @date 2020/7/12 14:55
 */
public class LazyModeSingleton {
  private static LazyModeSingleton obj;
  private int value;

  private LazyModeSingleton() {}

  public static LazyModeSingleton getInstance(int time) {
    if (obj == null) {
      try {
        Thread.sleep(time);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      obj = new LazyModeSingleton();

      Out.println(
          "thread:" + Thread.currentThread().getName() + ", obj:" + System.identityHashCode(obj));
    }
    return obj;
  }

  public void showMessage(int time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Out.println(
        "thread:"
            + Thread.currentThread().getName()
            + " value:"
            + value
            + " obj:"
            + System.identityHashCode(obj)
            + " this:"
            + System.identityHashCode(this));
  }

  public void setValue(int value) {
    this.value = value;
  }
}
