package kr.sys4u.network2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class LeafDirAndFileSender {

	public static void main(String args[]) {

		int serverPortNum = 9000;
		String serverAddress = "127.0.0.1";
		String dirPath = "C:/test";

		try (Socket socket = new Socket(serverAddress, serverPortNum);
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());

		) {
			ArrayList<Leaf> leafList = new LeafDirAndFileCollector().getDirectoryList(dirPath);

			for (Leaf leaf : leafList) {

				String controlData = leaf.getFileType()+"\n"+leaf.getDirPath()+"\n"+leaf.getFileName();
				out.writeUTF(controlData); 
				out.flush();

				if(leaf.getFileType().equals("F")) {
					String requestFilePath = in.readUTF();
					
					FileDataSender fileDataSender = new FileDataSender(out);
					fileDataSender.sendFile(dirPath + requestFilePath);
				}	
			}

		} catch (IOException e) {

		}
	}
}
