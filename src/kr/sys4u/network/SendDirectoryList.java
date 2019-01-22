package kr.sys4u.network;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class SendDirectoryList {

	private ArrayList<FileObject> DirectoryList;
	private String rootDirectoryPath;
	private FileObject fileObject;

	public SendDirectoryList() {
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
		//첫번째로 보낼 값 : File인지 Directory인지 구분자
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

		fileObject = new FileObject();
		fileObject.setPath(parentPath + File.separator + childFile.getName());
		fileObject.setType("D");

		if (IsLeaf(childFile)) {
			DirectoryList.add(fileObject);
		}
	}

	private void addFile(File childFile, String parentPath) {

		byte[] data = new byte[(int) childFile.length()];
		fileObject = new FileObject();

		try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(childFile));) {

			inputStream.read(data);
			fileObject.setData(data);
			fileObject.setPath(parentPath + File.separator + childFile.getName());
			fileObject.setType("F");
			DirectoryList.add(fileObject);

		} catch (FileNotFoundException e) {

		} catch (IOException e1) {

		}

	}

	private boolean IsLeaf(File file) {

		boolean check = true;

		for (File childfile : file.listFiles()) {
			if (childfile.isDirectory()) {
				check = false;
			}
		}
		return check;
	}

}
