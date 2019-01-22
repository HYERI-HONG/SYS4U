package kr.sys4u.network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class DirectoryTransferClient {

	public static void main(String args[]) {

		int serverPortNum = 9000;
		String serverAddress = "127.0.0.1";

		try (Socket socket = new Socket(serverAddress, serverPortNum);
				BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
				BufferedInputStream in = new BufferedInputStream(socket.getInputStream());

		) {
			ArrayList<FileObject> directoryList = new MakeDirectoryList().getDirectoryList("D:/apartment");

			for (FileObject fileObject : directoryList) {

				// 타입 전송
				out.write(fileObject.getFileType().getBytes()); //1
				out.flush();
				System.out.println("1. 타입 전송");

				// 경로 전송
				if (in.read() == fileObject.getFileType().getBytes().length) { //2
					System.out.println("2. 경로 전송");
					out.write(fileObject.getDirPath().getBytes()); //3
					out.flush();
				}

				if(fileObject.getFileType().equals("F")) {
					if (in.read() == fileObject.getDirPath().getBytes().length) {//4
						System.out.println("3.파일명 전송");
						out.write(fileObject.getFileName().getBytes()); //5
						out.flush();
					}
					if (in.read() == fileObject.getFileName().getBytes().length) { //6
						System.out.println("4.파일전송");
						byte[] data = new byte[3000];
						try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(
								fileObject.getDirPath() + File.separator + fileObject.getFileName()));) {

							inputStream.read(data);

						} catch (FileNotFoundException e) {

						} catch (IOException e1) {

						}
						out.write(data); //7
						out.flush();
					}
					
				}
				
			}

		} catch (IOException e) {

		}
	}
}
