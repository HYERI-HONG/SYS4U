package kr.sys4u.filedir2;

import java.io.File;

public class FileTree {

	private final FileNode rootFileNode;

	public FileTree(final String rootDir) {
		if (rootDir == null) {
			throw new IllegalArgumentException();
		}
		this.rootFileNode = new FileNode(new File(rootDir));
	}

	public FileTree(final File rootDir) {
		if (rootDir == null) {
			throw new IllegalArgumentException();
		}
		this.rootFileNode = new FileNode(rootDir);
	}

	public FileTree(final FileNode rootFileNode) {
		if (rootFileNode == null) {
			throw new IllegalArgumentException();
		}
		this.rootFileNode = rootFileNode;
	}
	
	public void initialize() {
		addChildrenRecursively(rootFileNode);
	}

	private void addChildrenRecursively(FileNode parentNode) {

		File[] childrenFile = parentNode.getFile().listFiles();

		for (File child : childrenFile) {
			if(child.isFile()) {
				continue;
			}

			addChildrenRecursively(parentNode.addChild(child));
		}
	}
	
	public String toString() {
		return StringFileNode.fileNodeToString(rootFileNode);
	}
}
