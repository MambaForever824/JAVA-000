package com.geekuniversity.java.week01.homework01;

/**
 * 自己写一个简单的 HelloCompileFile.java，里面需要涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码。
 */
public class Hello {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiple(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("divisor cannot be 0");
        }
        return a - b;
    }

    public int ifMethod(int a, int b) {
        if (a > b) {
            return a - b;
        }
        return b - a;
    }

    public void forLoop(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i;
        }
    }
}