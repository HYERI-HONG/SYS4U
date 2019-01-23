package kr.sys4u.network;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LeafDirAndFileReceiver {

	public static void main(String args[]) {

		int portNumber = 9000;
		String dirPath = "E:/test/server/apartment";

		try (ServerSocket serverSocket = new ServerSocket(portNumber);
				Socket clientSocket = serverSocket.accept();
				DataInputStream in = new DataInputStream(clientSocket.getInputStream());
				DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

			String receivedData = "";
			String[] controlInfo = new String[3];

			while (true) {

				receivedData = in.readUTF();
				System.out.println("제어정보 : " + receivedData);
				controlInfo = receivedData.split("\n");

				new File(dirPath + controlInfo[1]).mkdirs();

				if (controlInfo[0].equals("F")) {
					out.writeUTF(controlInfo[1] + File.separator + controlInfo[2]);
					out.flush();

					byte[] fileData = new byte[4000];
					BufferedOutputStream outStream = new BufferedOutputStream(
							new FileOutputStream(new File(dirPath + controlInfo[1] + File.separator + controlInfo[2])));

					in.read(fileData);
					outStream.write(fileData);
					outStream.flush();
					outStream.close();

				}
			}
		} catch (IOException e) {

		}
	}
}
