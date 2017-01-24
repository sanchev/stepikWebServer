package com.sanchev;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

class App {
	public static void main(String[] args) throws Exception {
		RandomAccessFile file = new RandomAccessFile("data/data.txt", "rw");
		FileChannel channel = file.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(48);
		
		int bytesRead = channel.read(buffer);
		
		while (bytesRead != -1) {
			System.out.println("Read:" + bytesRead);
			buffer.flip();
			
			while (buffer.hasRemaining()) {
				System.out.print((char) buffer.get());
			}
			System.out.println();
			
			buffer.clear();
			bytesRead = channel.read(buffer);
		}
		
		file.close();
	}
}