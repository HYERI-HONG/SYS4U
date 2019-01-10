package kr.sys4u.filedir;

public class FileTreeToStringConveter implements Convertable<FileTree,String> {

	private static final String CRNL = "\r\n";
	private static final String SPACE = "    ";
	private static final String CHILD_SYMBOL = "└";

	private String converted;

	public FileTreeToStringConveter() {
		this.converted = "";
	}
	
	@Override
	public String convert(final FileTree source) {
		converted += "StringConverter : "+source.getRootNode().getFile().getAbsolutePath()+CRNL;
		convertFileNodeToStringRecursively(source.getRootNode());
		return converted;
	}

	private void convertFileNodeToStringRecursively(final FileNode parentNode) {
	
		for (FileNode childNode : parentNode.getChildren()) {
			converted += getDepthSpace(childNode.getDepth()) + childNode.getFile().getName() + CRNL;
			convertFileNodeToStringRecursively(childNode);
		}
	}

	private String getDepthSpace(final int depth) {
		StringBuilder spaceBuilder = new StringBuilder();
		
		for (int i = 0; i < depth; i++) {
			spaceBuilder.append(SPACE);
		}
		return spaceBuilder.append(CHILD_SYMBOL).toString();
	}







	

}
