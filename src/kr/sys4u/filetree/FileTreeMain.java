package kr.sys4u.filetree;

import java.io.File;
import java.io.FileNotFoundException;

public class FileTreeMain {

	public static void main(String[] args) throws FileNotFoundException {
		final String dirPath = "C:/test/FileTreeToFileConvertResult/test.txt";

		FileTree fileTree = new FileTree(dirPath);
		ConvertManager manager = new ConvertManager();
		
		//과제1
		//manager.addConverter(FileTree.class, Boolean.class, new FileTreeToDirectoryConverter());
		//System.out.println(manager.requestConvert(fileTree, Boolean.class));
		
		//과제2
		manager.addConverter(FileTree.class, File.class, new FileTreeToFileConverter());
		manager.addConverter(File.class, FileTree.class, new FileToFileTreeConverter(""));
		
		FileTree newFileTree = manager.requestConvert(new File(dirPath), FileTree.class);
		manager.requestConvert(newFileTree, File.class);
		System.out.println(newFileTree.toString());
		
		
		//manager.addConverter(FileTree.class, Boolean.class, new FileTreeToDirectoryConverter());
		//System.out.println(manager.requestConvert(newFileTree, Boolean.class));
		
		
		//new FileToFileTreeConverter().convert(new File("C:/Users/notebiz7750/Downloads/FileConverter/ConvertResult.txt"));
		
		//manager.addConverter(FileTree.class, String.class, new FileTreeToStringConveter());
		//manager.addConverter(FileTree.class, StringBuilder.class, new FileTreeToStringBuilderConveter());
		
		//manager.addConverter(File.class, FileTree.class, new FileToDirectoryConverter());

		//System.out.println(manager.requestConvert(fileTree, String.class).toString());
		//System.out.println(manager.requestConvert(fileTree, StringBuilder.class).toString());
		//System.out.println(manager.requestConvert(fileTree, File.class).toString());
	
		
	}
}
