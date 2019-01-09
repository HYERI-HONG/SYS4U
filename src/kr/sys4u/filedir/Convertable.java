package kr.sys4u.filedir;

public interface Convertable<S, R> {
	public R convert(S source);	
}
