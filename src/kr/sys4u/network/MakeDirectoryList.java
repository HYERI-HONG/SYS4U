package kr.sys4u.network;

import java.io.File;
import java.util.ArrayList;

public class MakeDirectoryList {

	private ArrayList<FileObject> DirectoryList;
	private String rootDirectoryPath;
	private FileObject fileObject;

	public MakeDirectoryList() {
		this.DirectoryList = new ArrayList<FileObject>();
	}

	public ArrayList<FileObject> getDirectoryList(String rootDirectoryPath) {

		if (rootDirectoryPath == null) {
			throw new IllegalArgumentException();
		}

		File rootFile = new File(rootDirectoryPath);
		this.rootDirectoryPath = rootFile.getAbsolutePath();
		
		checkAllFileRecursively(rootFile);

		return DirectoryList;
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
			fileObject = new FileObject();
			fileObject.setDirPath(parentPath + File.separator + childFile.getName());
			fileObject.setFileType("D");
			DirectoryList.add(fileObject);
		}
	}

	private void addFile(File childFile, String parentPath) {


		fileObject = new FileObject();
		fileObject.setDirPath(parentPath);
		fileObject.setFileName(childFile.getName());
		fileObject.setFileType("F");
		DirectoryList.add(fileObject);


	}

}
