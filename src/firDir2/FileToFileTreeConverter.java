package firDir2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileToFileTreeConverter implements Convertable<File, FileTree> {
	private static final String SPACE = "  ";
	private FileNode rootFileNode;
	
	//private String path;

	@Override
	public FileTree convert(File source) {

		try (BufferedReader reader = new BufferedReader(new FileReader(source))) {

			this.rootFileNode = new FileNode(new File(reader.readLine()));
			addChildrenNodeFromFileRecursively(reader, rootFileNode,1);

		} catch (FileNotFoundException e) {
			// 처리
		} catch (IOException e1) {
			// 처리
		}
		return new FileTree(rootFileNode);
	}

	private void addChildrenNodeFromFileRecursively(BufferedReader reader, FileNode parentNode, int beforeDepth) {
		String readLine = "";
		String path = "";
		int currentDepth = 0;

		try {
			while ((readLine = reader.readLine()) != null) {

				currentDepth = readLine.substring(0, readLine.indexOf("└")).length() / SPACE.length();
				path = parentNode.getFile().getAbsolutePath()+"/" + readLine.substring(readLine.lastIndexOf("└") + 1);

				if (beforeDepth != currentDepth) {
					addChildrenNodeFromFileRecursively(reader,
							parentNode.getChildren().get((parentNode.getChildren().size())-1),currentDepth);
				}
				parentNode.addChild(new File(path), currentDepth);
				beforeDepth = currentDepth;
			}
		} catch (IOException e) {
			// 처리
		}

	}

}
