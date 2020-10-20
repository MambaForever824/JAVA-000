package com.geekuniversity.java.week01.homework02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Base64;

/**
 * 自定义一个 Classloader，加载一个 HelloCompileFile.xlass 文件，执行 hello 方法，此文件内容是一个 HelloCompileFile.class 文件所有字节（x=255-x）处理后的文件。JVM分代.png
 */
public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class<?> clazz = new HelloClassLoader().findClass("Hello");
            Object object = clazz.newInstance();
            Method method = clazz.getMethod("hello");
            method.invoke(object);
            // 输出结果 Hello, classLoader!
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String file = "/JAVA-000/Week_01/com/geekuniversity/java/week01/homework02/Hello.xlass";
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            int avail = inputStream.available();
            byte[] bytes = new byte[avail];
            inputStream.read(bytes);
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }
}
