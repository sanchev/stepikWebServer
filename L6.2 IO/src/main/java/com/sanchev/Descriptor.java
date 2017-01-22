package com.sanchev;

import java.io.*;

public class Descriptor implements Serializable {
    private final String name;
    private final int age;

    public Descriptor(String name, int age) {
        this.name = name;
        this.age = age;
    }

	public String toString() {
		return "Descriptor{name='" + name + "', age=" + age + "}";
	}
	
}