package kr.sys4u.filedir;

public class Main {

	public static void main(String[] args) {
		final String dirPath = "C:/Program Files/Java";
	
		FileTree fileTree = new FileTree(dirPath);
		System.out.println(new FileTreeStringConveter().convert(fileTree));
		System.out.println(new FileTreeStringBuilderConveter().convert(fileTree));
		
	}

}
