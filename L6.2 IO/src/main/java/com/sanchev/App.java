package com.sanchev;

import java.io.*;

class App {
	public static void main(String[] args) {
		stringExample();
		descriptorExample();
	}

	private static void stringExample() {
		write("Some text", "./string.bin");
		String fromFile = (String) read("./string.bin");
		System.out.println(fromFile);
	}

	private static void descriptorExample() {
		Descriptor descriptor = new Descriptor("WOW", 15);
		write(descriptor, "./descriptor.bin");
		Descriptor fromFile = (Descriptor) read("./descriptor.bin");
		System.out.println(fromFile);
	}

	public static void write(Object data, String fileName) {
		try (FileOutputStream out = new FileOutputStream(fileName)) {
			BufferedOutputStream bout = new BufferedOutputStream(out);
			ObjectOutputStream obout = new ObjectOutputStream(bout);
			obout.writeObject(data);
			obout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object read(String fileName) {
		try (FileInputStream in = new FileInputStream(fileName)) {
			BufferedInputStream bin = new BufferedInputStream(in);
			ObjectInputStream obin = new ObjectInputStream(bin);
			return obin.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}