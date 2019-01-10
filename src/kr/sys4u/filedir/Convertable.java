package kr.sys4u.filedir;

public interface Convertable<R,S> {
	
	public R convert(S source);

}
