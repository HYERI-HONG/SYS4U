package kr.sys4u.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String args[]) throws IOException {
		int portNumber = 9000;

		try (ServerSocket serverSocket = new ServerSocket(portNumber);
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				out.println(inputLine);
				System.out.println(new StringBuilder("server : ").append(inputLine).toString());
			}
		} catch (IOException e) {
			System.out.println((new StringBuilder("Failed to listen on port ")).append(portNumber)
					.append(" or listening for a connection").toString());
		}
	}
}
