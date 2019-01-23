package kr.sys4u.network2;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDataReceiver {

	private final String dirPath = "D:/after";
	private DataOutputStream out;
	private DataInputStream in;

	public FileDataReceiver(DataOutputStream out, DataInputStream in) {
		this.out = out;
		this.in = in;

	}

	public void saveFile(String fileDirPath) {

		try {
			out.writeUTF(fileDirPath);
			out.flush();

			BufferedOutputStream outStream = new BufferedOutputStream(
					new FileOutputStream(new File(dirPath + fileDirPath)));

			int readData = 0;
			long size = in.readLong();
			byte[] buffer = new byte[1024];

			while (size > 0 && (readData = in.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
				outStream.write(buffer, 0, readData);
				outStream.flush();
				size -= readData;
			}
			outStream.close();

		} catch (IOException e) {

		}
	}

}
