# Arrays

## copyOfRange

### 源码分析

```java
public static char[] copyOfRange(char[] original, int from, int to) {
    int newLength = to - from;
    if (newLength < 0)
        throw new IllegalArgumentException(from + " > " + to);
    // 开辟新的数组空间
    char[] copy = new char[newLength];
    // 调用System.arraycopy给新开辟的数组进行赋值
    System.arraycopy(original, from, copy, 0,
                     Math.min(original.length - from, newLength));
    return copy;
}
```

# 