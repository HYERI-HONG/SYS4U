package kr.sys4u.filedir;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileNode {
	private final File file;
	private final List<FileNode> children;
	private int depth;

	public FileNode(final File file) {
		if (file == null) {
			throw new IllegalArgumentException();
		}
		this.file = file;
		this.children = new ArrayList<>();
		this.depth = 0;
	}
	
	public FileNode(final String file) {
		if (file == null) {
			throw new IllegalArgumentException();
		}
		this.file = new File(file);
		this.children = new ArrayList<>();
		this.depth = 0;
	}

	public File getFile() {
		return file;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getDepth() {
		return depth;
	}

	public FileNode addChild(File child) {
		FileNode childNode = new FileNode(child);
		addChild(childNode);
		return childNode;
	}
	
	public FileNode addChild(File child, int depth) {
		FileNode childNode = new FileNode(child);
		childNode.setDepth(depth);
		addChild(childNode);
		return childNode;
	}
	
	public boolean addChild(FileNode child) {
		if (child == null) {
			throw new IllegalArgumentException();
		}
		return this.children.add(child);
	}

	public boolean addChildren(Collection<FileNode> children) {
		if (children == null) {
			throw new IllegalArgumentException();
		}
		return this.children.addAll(children);
	}

	public List<FileNode> getChildren(){
		List<FileNode> cloned = new ArrayList<>();
		cloned.addAll(this.children);
		return cloned;
	}
	
	public void removeChildren() {
		this.children.removeAll(this.children);
	}
}