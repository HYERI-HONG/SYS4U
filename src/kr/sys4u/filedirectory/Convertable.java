package kr.sys4u.filedirectory;

public interface Convertable<S,R> {
	
	public R convert(S source);

}