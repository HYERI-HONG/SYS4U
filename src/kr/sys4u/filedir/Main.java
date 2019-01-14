package kr.sys4u.filedir;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		final String dirPath = "C:/Users/notebiz7750/Documents/Apartment";

		FileTree fileTree = new FileTree(dirPath);
		ConvertManager manager = new ConvertManager();

	
		manager.addConverter(FileTree.class, String.class, new FileTreeToStringConveter());
		manager.addConverter(FileTree.class, StringBuilder.class, new FileTreeToStringBuilderConveter());
		
		manager.addConverter(FileTree.class, File.class, new FileTreeToFileConverter());
		manager.addConverter(File.class, FileTree.class, new FileToDirectoryConverter());

		System.out.println(manager.requestConvert(fileTree, String.class).toString());
		System.out.println(manager.requestConvert(fileTree, StringBuilder.class).toString());
		System.out.println(manager.requestConvert(fileTree, File.class).toString());
		
		File f = manager.requestConvert(fileTree, File.class);
		
		manager.requestConvert(f, FileTree.class);
	

		
	}
}
