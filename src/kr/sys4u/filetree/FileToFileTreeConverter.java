package kr.sys4u.filetree;

import static kr.sys4u.filetree.FileTree.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import kr.sys4u.exception.ConversionException;

public class FileToFileTreeConverter implements Convertable<File, FileTree> {

	/*
	 * private static final int SPACE_LENGTH = SPACE.length();
	 * 
	 * @Override public FileTree convert(File source) { FileNode rootFileNode =
	 * null; try (BufferedReader reader = new BufferedReader(new
	 * FileReader(source))) {
	 * 
	 * String readLine = reader.readLine();
	 * 
	 * rootFileNode = new FileNode(new File(readLine)); rootFileNode.setDepth(0);
	 * 
	 * int beforeDepth = rootFileNode.getDepth(); int currentDepth = 0;
	 * 
	 * FileNode beforeNode = rootFileNode; FileNode currentNode = null;
	 * 
	 * while ((readLine = reader.readLine()) != null) {
	 * 
	 * currentDepth = readLine.substring(0, readLine.indexOf(CHILD_SYMBOL)).length()
	 * / SPACE_LENGTH; String currentStr =
	 * readLine.substring(readLine.lastIndexOf(CHILD_SYMBOL) + 1);
	 * 
	 * beforeNode = findParentNode(beforeNode, beforeDepth - currentDepth + 1);
	 * 
	 * currentNode = new FileNode(new File(beforeNode.getFile().getAbsoluteFile() +
	 * "/" + currentStr)); beforeNode.addChild(currentNode, currentDepth);
	 * 
	 * beforeDepth = currentDepth; beforeNode = currentNode;
	 * 
	 * } } catch (IOException e) { throw new CanNotReadFileException(e); } return
	 * new FileTree(rootFileNode); }
	 * 
	 * private FileNode findParentNode(FileNode beforeNode, int count) { if
	 * (beforeNode.getDepth() <= count) { // throw } for (int i = 0; i < count; i++)
	 * { beforeNode = beforeNode.getParentNode(); } return beforeNode; }
	 */
	private static final int SPACE_LENGTH = SPACE.length();
	private String rootPath;

	public FileToFileTreeConverter(String rootPath) {
		this.rootPath = rootPath;
	}

	@Override
	public FileTree convert(File source) {
		List<String> dataList = new ArrayList<>();
		try {
			Files.lines(source.toPath()).sequential().forEach(dataList::add);
		} catch (IOException e) {
			throw new ConversionException(e);
		}

		return new FileTree(createRootFileNode(dataList));
	}

	private FileNode createRootFileNode(List<String> dataList) {
		FileNode rootFileNode = new FileNode(new File(rootPath));
		readLinesAndAddChildNodes(dataList, rootFileNode);
		return rootFileNode;
	}

	private void readLinesAndAddChildNodes(List<String> dataList, FileNode rootFileNode) {
		FileNode beforeNode = rootFileNode;
		for (int i = 1; i < dataList.size(); i++) {
			FileNode currentNode = addChildNode(beforeNode, dataList.get(i));
			beforeNode = currentNode;
		}
	}

	/**
	 * line을 읽어서 beforeNode로부터 얻어진 parentNode에 childNode를 add한 후 return한다.
	 * 
	 * @param beforeNode 이전에 작업한 노드
	 * @param line       파일에서 읽은 한 줄. 형식은 ..... 다.
	 * @return
	 */
	private FileNode addChildNode(FileNode beforeNode, String line) {
		int currentDepth = line.substring(0, line.indexOf(CHILD_SYMBOL)).length() / SPACE_LENGTH;//
		String currentDirName = line.substring(line.lastIndexOf(CHILD_SYMBOL) + 1);

		FileNode ancestorNode = beforeNode.getAncestor(beforeNode.getDepth() - currentDepth + 1);
		FileNode currentNode = new FileNode(new File(ancestorNode.getFile().getAbsoluteFile() + "/" + currentDirName),
				currentDepth);
		ancestorNode.addChild(currentNode);

		return currentNode;
	}

}
