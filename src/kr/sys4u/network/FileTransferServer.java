package kr.sys4u.network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileTransferServer {

	public static void main(String args[]) {
		int portNumber = 9000;
		File file = new File("D:/test2/test.txt");

		try (ServerSocket serverSocket = new ServerSocket(portNumber);
				Socket clientSocket = serverSocket.accept();
				BufferedInputStream in = new BufferedInputStream(clientSocket.getInputStream());
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));) {
			int data;
			while ((data = in.read()) != -1) {
				out.write(data);
			}
		} catch (IOException e) {
			System.out.println((new StringBuilder("Failed to listen on port ")).append(portNumber)
					.append(" or listening for a connection").toString());
		}
	}
}
