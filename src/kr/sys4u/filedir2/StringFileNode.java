package kr.sys4u.filedir2;
import java.util.List;

public class StringFileNode {
	private static final String SPACE = "  ";
	private static final String CRNL = "\r\n";
	private static final String CHILD_SYMBOL = "L";
	private static StringBuilder FileNodeStringBuilder;

	public static String fileNodeToString(final FileNode rootFileNode) {
		if (rootFileNode == null) {
			throw new IllegalArgumentException();
		}
		FileNodeStringBuilder = new StringBuilder();
		FileNodeStringBuilder.append(rootFileNode.getFile().getAbsolutePath() + CRNL);
		
		List<FileNode> childerNode = rootFileNode.getChildren();
		String fileNodeString =  appendStringBuilderRecusively(childerNode, 0);
		
		return fileNodeString;
	}

	private static String appendStringBuilderRecusively(List<FileNode> childrenNodeList, int depth) {
		List<FileNode> recentNodeList = childrenNodeList;

		depth++;
		for(FileNode childNode : recentNodeList) {
			FileNodeStringBuilder.append(String.format("%s %s%s %s",
					present(depth), CHILD_SYMBOL, childNode.getFile().getName(), CRNL));
			appendStringBuilderRecusively(childNode.getChildren(), depth);
		}
		
		return FileNodeStringBuilder.toString();
	}
	
	private static String present(int depth) {
		StringBuilder tab = new StringBuilder();
		
		for(int i=0; i<depth; i++) {
			tab.append(SPACE);
		}
		
		return tab.toString();
	}
}
