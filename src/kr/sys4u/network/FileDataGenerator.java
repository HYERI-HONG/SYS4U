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
		
		byte[] fileData = new byte[4000];
		
		try (BufferedInputStream inputStream = new BufferedInputStream(
				new FileInputStream(new File(fileDirPath)));
		) {
			
			inputStream.read(fileData);
			inputStream.close();

		} catch (FileNotFoundException e) {

		} catch (IOException e1) {

		}
		return fileData;
	}
	
}
