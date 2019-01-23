package kr.sys4u.network2;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerProcessor {
	Socket clientSocket;
	private final String dirPath = "C:/test2";

	public ServerProcessor(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public void process() {

		try (DataInputStream in = new DataInputStream(clientSocket.getInputStream());
				DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

		) {
			while (true) {

				String receivedData = in.readUTF();
				String[] controlInfo = receivedData.split("\n");

				new File(dirPath + controlInfo[1]).mkdirs();

				if (controlInfo[0].equals("F")) {
					out.writeUTF(controlInfo[1] + File.separator + controlInfo[2]);
					out.flush();

					System.out.println();
					BufferedOutputStream outStream = new BufferedOutputStream(
							new FileOutputStream(new File(dirPath + controlInfo[1] + File.separator + controlInfo[2])));

					int readData = 0;
					long size = in.readLong();
					byte[] buffer = new byte[1024];

					while (size > 0 && (readData = in.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
						outStream.write(buffer, 0, readData);
						outStream.flush();
						size -= readData;
					}
					outStream.close();
				}
			}

		} catch (IOException e) {

		}

	}
}
