package kr.sys4u.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
	public static void main(String args[]) throws IOException {
		String serverAddress = "127.0.0.1";
		int serverPort = 9000;

		try (Socket echoSocket = new Socket(serverAddress, serverPort);
				PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
				BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));) {
			String userInput;
			while ((userInput = userIn.readLine()) != null) {
				out.println(userInput);
				System.out.println((new StringBuilder("Client : ")).append(in.readLine()).toString());
			}
		} catch (IOException e) {
			System.err.println((new StringBuilder("Failed to connect to ")).append(serverAddress).toString());
		}

	}
}
