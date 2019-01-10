package kr.sys4u.filedir;

import java.io.File;

public class FileTree {

	private final FileNode rootFileNode;
	private boolean initialized = false;

	public FileTree(final String rootDirPath) {
		if (rootDirPath == null) {
			throw new IllegalArgumentException("경로를 잘못 입력하였습니다.");
		}
		if (!new File(rootDirPath).exists()) {
			throw new NullPointerException("존재하지 않는 경로입니다.");
		}	
		this.rootFileNode = new FileNode(new File(rootDirPath));
	}

	public FileTree(final File rootDirFile) {
		if (rootDirFile == null) {
			throw new IllegalArgumentException("파일을 잘못 입력하였습니다.");
		}
		if (!rootDirFile.exists()) {
			throw new NullPointerException("존재하지 않는 파일입니다.");
		}
		this.rootFileNode = new FileNode(rootDirFile);
	}

	public FileTree(final FileNode rootFileNode) {
		if (rootFileNode == null) {
			throw new IllegalArgumentException("파일 노드를 잘못 입력하였습니다.");
		}
		this.rootFileNode = rootFileNode;
	}

	public synchronized void initialize() {
		if (initialized) {
			return;
		}
		this.rootFileNode.removeChilderen();
		addChildrenRecursively(this.rootFileNode);
		this.initialized = true;
	}

	private void addChildrenRecursively(FileNode parentNode) {

		File[] childrenFiles = parentNode.getFile().listFiles();
		int depth = parentNode.getDepth();

		for (File child : childrenFiles) {
			if(child.isFile()) {
				continue;
			}
			addChildrenRecursively(parentNode.addChild(child, depth+1));
		}
	}
	
	public FileNode getRootNode() {
		if (!initialized) {
			initialize();
		}
		return this.rootFileNode;
	}
	
}
