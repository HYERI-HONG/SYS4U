package kr.sys4u.filedir;
import java.util.List;

public class FileTreeStringConverter implements Convertable<FileTree, String> {
	private static final String SPACE = "  ";
	private static final String CRNL = "\r\n";
	private static final String CHILD_SYMBOL = "L";
	private StringBuilder converted;

	@Override
	public String convert(final FileTree fileTree) {
		if (fileTree == null) {
			throw new IllegalArgumentException();
		}
		converted = new StringBuilder();
		FileNode rootFileNode = fileTree.getRootFileNode();
		
		converted.append(rootFileNode.getFile().getAbsolutePath() + CRNL);
		List<FileNode> childerNode = rootFileNode.getChildren();
		String fileNodeString =  appendStringBuilderRecusively(childerNode);
		
		return fileNodeString;
	}
	
	private String appendStringBuilderRecusively(List<FileNode> childrenNodeList) {
		List<FileNode> recentNodeList = childrenNodeList;
		
		for(FileNode childNode : recentNodeList) {
			converted.append(String.format("%s %s%s %s",
					present(childNode.getDepth()), CHILD_SYMBOL, childNode.getFile().getName(), CRNL));
			appendStringBuilderRecusively(childNode.getChildren());
		}
		
		return converted.toString();
	}
	
	private String present(int depth) {
		StringBuilder tab = new StringBuilder();
		
		for(int i=0; i<depth; i++) {
			tab.append(SPACE);
		}
		
		return tab.toString();
	}

}
