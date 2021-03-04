package com.chasedream.test.memory;

import com.chasedream.utils.Out;

/**
 * @author Zhang Dezhou
 * @Description
 * @date 2020/1/12 16:18
 */
public class ValueCopy {
    private final char[] value;

    private ValueCopy(char[] value) {
        this.value = value;
    }

    public static void main(String[] args) {
        char[] chars = {'a', 'b', '0', '\n'};
        ValueCopy vc = new ValueCopy(chars);
    }

    private void testCommonGet() {
        Out.print(value[0]);
        Out.print(value[1]);
        Out.print(value[2]);
    }

    private void testAvoidGetField() {
        char[] b = value;
        Out.print(b[0]);
        Out.print(b[1]);
        Out.print(b[2]);
    }

}
