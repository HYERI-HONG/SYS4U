package kr.sys4u.filedir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileTreeToFileConverter implements Convertable<FileTree, File> {

	private static final String CRNL = "\r\n";
	private static final String SPACE = "    ";
	private static final String CHILD_SYMBOL = "└";
	private static final String SAVE_PATH = "C:/Users/notebiz7750/Downloads/FileConverter/ConvertResult.txt";
	

	private String converted;

	public FileTreeToFileConverter() {
		this.converted = "";
	}

	@Override
	public File convert(FileTree source) {

		File file = new File(SAVE_PATH);
		converted += "FileConverter : " + source.getRootNode().getFile().getAbsolutePath() + CRNL;
		convertFileNodeToStringRecursively(source.getRootNode());

		try (FileWriter writer = new FileWriter(file, false)) {
			writer.write(converted);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	private void convertFileNodeToStringRecursively(final FileNode parentNode) {

		for (FileNode childNode : parentNode.getChildren()) {
			converted += getDepthSpace(childNode.getDepth()) + childNode.getFile().getName() + CRNL;
			convertFileNodeToStringRecursively(childNode);
		}
	}

	private String getDepthSpace(final int depth) {
		StringBuilder spaceBuilder = new StringBuilder();

		for (int i = 0; i < depth; i++) {
			spaceBuilder.append(SPACE);
		}
		return spaceBuilder.append(CHILD_SYMBOL).toString();
	}


}
