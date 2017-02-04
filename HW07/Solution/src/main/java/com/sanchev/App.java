package com.sanchev;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class App {
	private static final long TIME_TO_WORK = TimeUnit.SECONDS.toMillis(10);

	public static void main(String[] args) throws Exception {
		try (ServerSocket serverSocket = new ServerSocket(5050)) {
			System.out.println("Server started");
			long startTime = new Date().getTime();
			while (new Date().getTime() < startTime + TIME_TO_WORK) {
				new MirrorSocket(serverSocket.accept()).start();
			}
		}
	}

	private static class MirrorSocket extends Thread {
		private final Socket socket;

		MirrorSocket(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try (PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				 BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
				String inputLine;
				while ((inputLine = input.readLine()) != null) {
					out.println(inputLine);
					out.flush();
					if (inputLine.equals("Bue."))
						break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}