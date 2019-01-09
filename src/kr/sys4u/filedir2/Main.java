package kr.sys4u.filedir2;

public class Main {

	public static void main(String[] args) {
		FileTree ft = new FileTree("C:\\Program Files\\Java");
		ft.initialize();
		
		System.out.print(ft.toString());
	}

}
