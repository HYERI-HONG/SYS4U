package kr.sys4u.network;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class DirectoryTransferServer {

	@SuppressWarnings("unchecked")
	public static void main(String args[]) {

		int portNumber = 9000;
		String dirPath = "D:/apartment2";

		try (ServerSocket serverSocket = new ServerSocket(portNumber);
				Socket clientSocket = serverSocket.accept();
				ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());) {

			for (FileObject fileObject : (ArrayList<FileObject>) in.readObject()) {

				if (fileObject.getType().equals("D")) {
					new File(dirPath + fileObject.getPath()).mkdirs();
				} else {
					FileOutputStream OutputStream = new FileOutputStream(new File(dirPath + fileObject.getPath()));
					OutputStream.write(fileObject.getData());
					OutputStream.flush();
					OutputStream.close();
				}
			}
		} catch (IOException | ClassNotFoundException e) {

		}
	}
}
