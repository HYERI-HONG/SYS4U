package kr.sys4u.network2;

import java.io.File;
import java.util.ArrayList;

public class LeafDirAndFileCollector {

	private ArrayList<Leaf> leafList;
	private String rootDirectoryPath;
	private Leaf leaf;
	private int fileLeafCnt;

	public LeafDirAndFileCollector() {
		this.leafList = new ArrayList<Leaf>();
		this.fileLeafCnt = 0;
	}

	public void setDirectoryList(String rootDirectoryPath) {

		if (rootDirectoryPath == null) {
			throw new IllegalArgumentException();
		}

		File rootFile = new File(rootDirectoryPath);
		this.rootDirectoryPath = rootFile.getAbsolutePath();
		
		checkAllFileRecursively(rootFile);

	}

	public ArrayList<Leaf> getDirectoryList() {
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

		fileLeafCnt++;
	}

	public int getFileLeafCnt() {
		return fileLeafCnt;
	}

	public String DirectoryListToString() {

		String result = "";


		for (int i = 0; i < leafList.size(); i++) {
			String temp = leafList.get(i).getFileType() + "\t" + leafList.get(i).getDirPath() + "\t"
					+ leafList.get(i).getFileName();
			result += temp + ((i == leafList.size() - 1) ? "" : "\n");
		}
		return result;
	}



}
