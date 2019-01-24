package kr.sys4u.network2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class ServerProcessor {
	Socket clientSocket;
	private final String dirPath = "D:/after";

	public ServerProcessor(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public void process() {

		try (DataInputStream in = new DataInputStream(clientSocket.getInputStream());
				DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());) {

			String controlData = in.readUTF();
			String[] controlArray = controlData.split("\n");

			for (String control : controlArray) {
				String[] controlInfo = control.split("\t");


				new File(dirPath + controlInfo[1]).mkdirs();

				if (controlInfo[0].equals("F")) {

					new FileDataReceiver(out, in).saveFile(controlInfo[1] + File.separator + controlInfo[2]);
				}
			}
		} catch (IOException e) {

		}

	}
}
