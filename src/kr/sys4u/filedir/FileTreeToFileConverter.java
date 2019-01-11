package kr.sys4u.filedir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import kr.sys4u.filedir.exception.ConversionException;

public class FileTreeToFileConverter implements Convertable<FileTree, File> {

	private static final String SAVE_PATH = "C:/Users/notebiz7750/Downloads/FileConverter/ConvertResult.txt";

	@Override
	public File convert(FileTree source) {

		File file = new File(SAVE_PATH);

		/*
		 * try (FileWriter writer = new FileWriter(file, false)) { //try with resources
		 * -> auto closerable writer.write(source.toString()); writer.flush(); } catch
		 * (IOException e) { throw new ConversionException(e); }
		 */

		FileWriter writer = null;
		try {
			writer = new FileWriter(file, false);
			writer.write(source.toString());
			writer.flush();
		} catch (IOException e) {
			throw new ConversionException(e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return file;
	}

}
