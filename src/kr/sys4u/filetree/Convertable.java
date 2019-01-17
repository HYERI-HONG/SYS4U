package kr.sys4u.filetree;

public interface Convertable<S,R> {
	
	public R convert(S source);

}
