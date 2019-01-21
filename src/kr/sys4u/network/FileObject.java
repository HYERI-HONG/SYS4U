package kr.sys4u.network;

import java.io.Serializable;

public class FileObject implements Serializable {

	private static final long serialVersionUID = -4933012608523195041L;
	
	private String path;
	private String type;
	private byte[] data;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
