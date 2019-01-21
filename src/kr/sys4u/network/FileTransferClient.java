package kr.sys4u.network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class FileTransferClient {

	public static void main(String args[]) {
		String serverAddress = "127.0.0.1";
		int serverPort = 9000;

		try (Socket socket = new Socket(serverAddress, serverPort);
				BufferedInputStream in = new BufferedInputStream(
						new FileInputStream(new File("D:/test1/test.txt")));
				BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());

		) {
			int data;
			while ((data = in.read()) != -1) {
				out.write(data);
			}

		} catch (IOException e) {
			System.err.println((new StringBuilder("Failed to connect to ")).append(serverAddress).toString());
		}
	}
}
