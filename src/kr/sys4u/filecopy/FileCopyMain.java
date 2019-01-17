package kr.sys4u.filecopy;

public class FileCopyMain {

	public static void main(String[] args) {

		FileCopyExcutant fileCopy = new FileCopyExcutant("D:/test1/test.txt", "D:/test2/test.txt");

		// fileCopy.Copy();
		// fileCopy.move();
		// fileCopy.FileCopier();
		fileCopy.FileMover();
	}

}
