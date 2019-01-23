package kr.sys4u.network2;

import java.io.File;
import java.util.ArrayList;

public class LeafDirAndFileCollector {

	private ArrayList<Leaf> leafList;
	private String rootDirectoryPath;
	private Leaf leaf;

	public LeafDirAndFileCollector() {
		this.leafList = new ArrayList<Leaf>();
	}

	public ArrayList<Leaf> getDirectoryList(String rootDirectoryPath) {

		if (rootDirectoryPath == null) {
			throw new IllegalArgumentException();
		}

		File rootFile = new File(rootDirectoryPath);
		this.rootDirectoryPath = rootFile.getAbsolutePath();
		
		checkAllFileRecursively(rootFile);

		return leafList;
	}

	public void checkAllFileRecursively(File parentFile) {
	
		String parentPath = parentFile.getAbsolutePath().replace(rootDirectoryPath, "");

		for (File childFile : parentFile.listFiles()) {

			if (childFile.isDirectory()) {
				
				addDirectory(childFile, parentPath);
				checkAllFileRecursively(childFile);
			} else {
				addFile(childFile, parentPath);
			}
		}
	}

	private void addDirectory(File childFile, String parentPath) {

		
		if (childFile.listFiles().length == 0) {
			leaf = new Leaf();
			leaf.setDirPath(parentPath + File.separator + childFile.getName());
			leaf.setFileType("D");
			leafList.add(leaf);
		}
	}

	private void addFile(File childFile, String parentPath) {

		leaf = new Leaf();
		leaf.setDirPath(parentPath);
		leaf.setFileName(childFile.getName());
		leaf.setFileType("F");
		leafList.add(leaf);


	}

}
