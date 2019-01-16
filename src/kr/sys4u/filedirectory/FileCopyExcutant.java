package kr.sys4u.filedirectory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyExcutant {

	private final String inputPath;
	private final String outputPath;

	public FileCopyExcutant(String inPutPath, String outPutPath) {

		if (inPutPath == null || outPutPath == null) {
			throw new IllegalArgumentException();
		}
		this.inputPath = inPutPath;
		this.outputPath = outPutPath;
	}

	public void Copy() {

		int data = 0;
		try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(outputPath)));
				BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(new File(inputPath)))) {
			while ((data = inputStream.read()) != -1) {
				outputStream.write(data);
			}
		} catch (FileNotFoundException e) {
			// 처리
		} catch (IOException e1) {
			// 처리
		}
	}

	public void move() {
		Copy();
		new File(inputPath).delete();
	}

	public void copyDirectoryStructure() {
		
	}

//	public void copyDirectoryStructure() {
//		FileNode inputRootNode = new FileTree(inputPath).getRootNode();
//		FileNode outputRootNode = new FileNode(new File(outputPath));
//
//		accessToAllNode(inputRootNode,outputRootNode);
//		
//		FileTree ft = new FileTree(outputRootNode);
//
//		//FileTreeToDirectoryConverter ft = new FileTreeToDirectoryConverter();
//		//ft.convert(new FileTree(copiedRootNode));
//
//	}
//
//	private void accessToAllNode(FileNode inputNode, FileNode outputNode) {
//		
//		for (FileNode childNode : inputNode.getChildren()) {
//			
//			FileNode tempNode  = new FileNode(new File((childNode.getFile().getAbsolutePath()).replace(inputPath, outputPath)));
//			
//			outputNode.addChild(tempNode,childNode.getDepth());
//			
//			accessToAllNode(childNode,tempNode);
//		}
//
//	}

}
