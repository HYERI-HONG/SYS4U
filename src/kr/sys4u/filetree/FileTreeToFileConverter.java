package kr.sys4u.filetree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import kr.sys4u.exception.ConversionException;

public class FileTreeToFileConverter implements Convertable<FileTree, File> {

	private static final String SAVE_PATH = "C:/test/FileTreeToFileConvertResult/test1.txt";

	@Override
	public File convert(FileTree source) {

		File file = new File(SAVE_PATH);

		try (FileWriter writer = new FileWriter(file, false)) {
			writer.write(source.toString());
			writer.flush();
		} catch (IOException e) {
			throw new ConversionException(e);
		}

		return file;
	}

}
