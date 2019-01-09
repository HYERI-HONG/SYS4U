package kr.sys4u.filedir;

public class Main {

	public static void main(String[] args) {
		FileTree ft = new FileTree("C:\\Program Files\\Java");
		
		System.out.print(new FileTreeStringConverter().convert(ft));
	}

}
