package kr.sys4u.network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class DirectoryTransferClient {

	public static void main(String args[]) {

		int serverPortNum = 9000;
		String serverAddress = "127.0.0.1";

		try (Socket socket = new Socket(serverAddress, serverPortNum);
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());

		) {

			SendDirectoryList sendDirectoryList = new SendDirectoryList();

			// out.writeObject(new SendDirectoryList().getDirectoryList("D:/apartment"));
			// out.flush();

		} catch (IOException e) {

		}
	}
}
