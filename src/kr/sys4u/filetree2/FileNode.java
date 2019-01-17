package kr.sys4u.filetree2;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileNode {
	private final File file;
	private final List<FileNode> children;
	private int depth;
	private FileNode parentNode;

	public FileNode(final File file) {
		if (file == null) {
			throw new IllegalArgumentException();

		}
		this.file = file;
		this.children = new ArrayList<>();
		this.parentNode = null;
	}

	public FileNode(final File file, int depth) {
		if (file == null) {
			throw new IllegalArgumentException();
		}
		this.file = file;
		this.depth = depth;
		this.children = new ArrayList<>();
		this.parentNode = null;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public FileNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(FileNode parentNode) {
		this.parentNode = parentNode;
	}

	public File getFile() {
		return file;
	}

	public List<FileNode> getChildren() {
		List<FileNode> cloned = new ArrayList<>();
		cloned.addAll(this.children);
		return cloned;
	}

	public FileNode addChild(final File child, final int depth) {
		FileNode childNode = new FileNode(child);
		addChild(childNode);
		return childNode;
	}

	public boolean addChild(final FileNode childNode) {
		if (childNode == null) {
			throw new IllegalArgumentException();
		}

		childNode.parentNode = this;
		return this.children.add(childNode);

	}

	public boolean addChildren(Collection<FileNode> children) {
		if (children == null) {
			throw new IllegalArgumentException();
		}
		return this.children.addAll(children);
	}

	public void removeChildren() {
		this.children.removeAll(children);
	}


}
