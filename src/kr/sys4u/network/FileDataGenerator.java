package kr.sys4u.network;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileDataGenerator {

	public byte[] getFileData(String fileDirPath) {
		
		if(fileDirPath == null) {
			throw new IllegalArgumentException();
		}
		
		
		File file = new File(fileDirPath);
		byte[] fileData = new byte[4096];

		try (BufferedInputStream inputStream = new BufferedInputStream(
				new FileInputStream(file));
		) {
			
			inputStream.read(fileData);
			inputStream.close();

		} catch (FileNotFoundException e) {

		} catch (IOException e1) {

		}
		return fileData;
	}
	
}
