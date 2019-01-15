package firDir2;

import java.io.File;
import java.util.ArrayList;

public class FileTreeToDirectoryConverter implements Convertable<FileTree, Boolean> {

	private static final String DIR_ROOT_PATH = "C:/test/apartment2";
	private Boolean result;

	public FileTreeToDirectoryConverter() {
		this.result = false;
	}

	@Override
	public Boolean convert(FileTree source) {
		makeDirByFileNodeRecursivly(source.getRootNode(), DIR_ROOT_PATH);
		return result;
	}

	private void makeDirByFileNodeRecursivly(final FileNode parentNode, String dirPath) {

		for (FileNode childNode : parentNode.getChildren()) {
			String currentDirPath = dirPath + "/" + childNode.getFile().getName();
			if (childNode.getChildren().isEmpty()) {
				File file = new File(currentDirPath);
				result = file.mkdirs();
			}
			makeDirByFileNodeRecursivly(childNode, currentDirPath);
		}
	}
}
