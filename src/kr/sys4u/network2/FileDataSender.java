package kr.sys4u.network2;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileDataSender {

	private DataOutputStream out;

	public FileDataSender(DataOutputStream out) {
		this.out = out;
	}

	public void sendFile(String fileDirPath) {
		
		if(fileDirPath == null) {
			throw new IllegalArgumentException();
		}
		
		File file = new File(fileDirPath);

		try (BufferedInputStream in = new BufferedInputStream(
				new FileInputStream(file));
		) {
			
			byte[] data = new byte[(int) file.length()];
			long size = data.length;

			out.writeLong(data.length);

			int readData = 0;
			byte[] buffer = new byte[1024];

			while (size > 0 && (readData = in.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
				out.write(buffer, 0, readData);
				out.flush();
				size -= readData;
			}
			in.close();

		} catch (FileNotFoundException e) {

		} catch (IOException e1) {

		}
	}
	
}
