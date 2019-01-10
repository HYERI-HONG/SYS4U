package kr.sys4u.filedir;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		final String dirPath = "C:/Intel";

		FileTree fileTree = new FileTree(dirPath);
		ConvertManager manager = new ConvertManager();

		manager.addConverter(FileTree.class, File.class, new FileTreeToFileConverter());
		manager.addConverter(FileTree.class, String.class, new FileTreeToStringConveter());
		manager.addConverter(FileTree.class, StringBuilder.class, new FileTreeToStringBuilderConveter());

		System.out.println(manager.requestConvert(fileTree, String.class).toString());
		System.out.println(manager.requestConvert(fileTree, StringBuilder.class).toString());
		System.out.println(manager.requestConvert(fileTree, File.class).toString());
	}
}
