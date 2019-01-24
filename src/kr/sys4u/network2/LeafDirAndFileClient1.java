package kr.sys4u.network2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class LeafDirAndFileClient1 {

	public static void main(String args[]) {

		int serverPortNum = 9000;
		String serverAddress = "127.0.0.1";
		String dirPath = "D:/client1";

		try (Socket socket = new Socket(serverAddress, serverPortNum);
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());

		) {
			LeafDirAndFileCollector collector = new LeafDirAndFileCollector();
			collector.setDirectoryList(dirPath);

			out.writeUTF(collector.DirectoryListToString());
			out.flush();

			for (int i = 0; i < collector.getFileLeafCnt(); i++) {
				String requestFilePath = in.readUTF();
				FileDataSender fileDataSender = new FileDataSender(out);
				fileDataSender.sendFile(dirPath + requestFilePath);
			}

		} catch (IOException e) {

		}
	}
}
