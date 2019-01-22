package kr.sys4u.network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DirectoryTransferServer {

	public static void main(String args[]) {

		int portNumber = 9000;
		String dirPath = "D:/apartment2";

		int option = 0;
		int readByteCnt = 0;


		try (ServerSocket serverSocket = new ServerSocket(portNumber);
				Socket clientSocket = serverSocket.accept();
				BufferedInputStream in = new BufferedInputStream(clientSocket.getInputStream());
				BufferedOutputStream out = new BufferedOutputStream(clientSocket.getOutputStream())) {

			String readFileType = "";
			String readDirPath = "";
			String readFileName = "";
			byte[] readFileData = null;

			while (true) {
				System.out.println("option : " + option);
				byte[] data = new byte[9000];
				readByteCnt = in.read(data); // 1 //3 //5

				switch (option) {
				case 0:
					readFileType = new String(data);
					System.out.println("타입 : " + readFileType);
					out.write(readByteCnt); // 2
					out.flush();
					option++;

					break;
				case 1:
					readDirPath = new String(data);
					System.out.println("경로 : " + readDirPath);
					out.write(readByteCnt); // 4
					out.flush();

					if (readFileType.equals("D")) {
						System.out.println("받은 파일이 디렉토리임");
						new File(dirPath + readDirPath).mkdirs();
						option = 0;
					} else {
						option++;
					}
					break;
				case 2:
					readFileName = new String(data);
					System.out.println("파일명 : " + readFileName);
					out.write(readByteCnt); // 6
					out.flush();
					option++;

					break;
				case 3:
					System.out.println("파일저장경로 : " + dirPath + readDirPath);
					new File(dirPath + readDirPath).mkdirs();

					BufferedOutputStream outStream = new BufferedOutputStream(
							new FileOutputStream(
									new File(dirPath + readDirPath + File.separator + readFileName)));

					outStream.write(data);
					option = 0;
					break;
				}

			}
		} catch (IOException e) {

		}
	}
}
