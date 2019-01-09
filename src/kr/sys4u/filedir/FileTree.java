package kr.sys4u.filedir;

import java.io.File;

public class FileTree {

	private final FileNode rootFileNode;
	private boolean initialized = false;

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
	
	public synchronized void initialize() {
		if(initialized) {
			return;
		}
		rootFileNode.removeChildren();
		addChildrenRecursively(rootFileNode);
		initialized = true;
	}

	//getRootNode 留뚮뱾湲�
	public FileNode getRootFileNode() {
		if(!initialized) {
			initialize();
		}
		
		return rootFileNode;
	}
	
	private void addChildrenRecursively(FileNode parentNode) {

		File[] childrenFile = parentNode.getFile().listFiles();

		for (File child : childrenFile) {
			if(child.isFile()) {
				continue;
			}

			addChildrenRecursively(parentNode.addChild(child, parentNode.getDepth()+1));
		}
	}

//	public String toString() {
//		return StringFileNode.fileNodeToString(FileTree);
//	}
}
