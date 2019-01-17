package kr.sys4u.filecopy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import kr.sys4u.exception.*;

public class FileCopyExcutant {
	private File inputFile;
	private File outputFile;
	
	private Boolean moveState;

	public FileCopyExcutant(String inputPath, String outputPath) {

		if (inputPath == null || outputPath == null) {
			throw new IllegalArgumentException();
		}
		
		this.inputFile = new File(inputPath);
		this.outputFile = new File(outputPath);

		if (!inputFile.exists()) {
			throw new NoSuchFileException();
		}
		if (!outputFile.exists()) {
			outputFile.mkdir();
		}
		this.moveState = false;
	}

	public void FileCopier() {
		if (checkOption()) {
			copyFile(inputFile, outputFile);
		} else {
			copyDirectoryRecursively(inputFile, outputFile);
		}
	}

	public void FileMover() {
		this.moveState = true;
		FileCopier();
	}

	private void copyDirectoryRecursively(File SuperFile, File preCopiedFile) {

		for (File subFile : SuperFile.listFiles()) {

			File copiedFile = new File(preCopiedFile + File.separator + subFile.getName());

			if (subFile.isDirectory()) {
				copiedFile.mkdir();
				copyDirectoryRecursively(subFile, copiedFile);
			} else {
				copyFile(subFile, copiedFile);
			}
			if (moveState)
				subFile.delete();
		}
	}

	private void copyFile(File inputFile, File outputFile) {

		int data = 0;
		try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
				BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile))) {
			while ((data = inputStream.read()) != -1) {
				outputStream.write(data);
			}
		} catch (FileNotFoundException e) {
			throw new NoSuchFileException(e);
		} catch (IOException e) {
			throw new FailToReadStreamException();
		}
	}

	private boolean checkOption() {
		boolean result = false;

		if (inputFile.isDirectory()) {
			if (outputFile.isFile()) {
				throw new IllegalArgumentException("폴더를 파일로 복사할 수 없습니다.");
			}
		} else {
			if (outputFile.isDirectory()) {

				outputFile = new File(outputFile.getAbsoluteFile() + File.separator + inputFile.getName());

			} else {

				if (!getFileExtension(inputFile).equals(getFileExtension(outputFile))) {

					throw new IllegalArgumentException("복사하려는 두 파일의 확장명이 다릅니다.");
				}
			}
			result = true;
		}
		return result;
	}

	private String getFileExtension(File file) {
		return file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1);
	}

}
