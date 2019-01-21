package kr.sys4u.network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DirectoryTransferClient {

	public static void main(String args[]) {

		int serverPortNum = 9000;
		String serverAddress = "127.0.0.1";

		try (Socket socket = new Socket(serverAddress, serverPortNum);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());) {

			out.writeObject(new MakeDirectoryList().getDirectoryList("D:/apartment"));
			out.flush();

		} catch (IOException e) {

		}
	}
}
