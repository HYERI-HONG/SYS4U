package kr.sys4u.filedir;

import java.util.HashMap;
import java.util.Map;

import kr.sys4u.filedir.exception.NoSuchConverterException;

public class ConvertManager {

	private final Map<TypeOfSourceAndResponse<?, ?>, Convertable<?, ?>> converterMap = new HashMap<>();

	public void addConverter(Class<?> sourceType, Class<?> returnType, Convertable<?, ?> converter) {
		converterMap.put(new TypeOfSourceAndResponse<>(sourceType, returnType), converter);
	}

	@SuppressWarnings({ "unchecked" })
	public <S, R> R requestConvert(S source, Class<R> resultTypeClass) {
		if (source == null || resultTypeClass == null) {
			throw new IllegalArgumentException();
		}

		Convertable<S, R> converter = (Convertable<S, R>) converterMap
				.get(new TypeOfSourceAndResponse<>(source.getClass(), resultTypeClass));
		if (converter == null) {
			throw new NoSuchConverterException("No Converter for ResultType: " + resultTypeClass.getName());
		}
		return converter.convert(source);
	}

	private static class TypeOfSourceAndResponse<S, R> {
		Class<S> s;
		Class<R> r;

		public TypeOfSourceAndResponse(Class<S> s, Class<R> r) {
			this.s = s;
			this.r = r;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((r == null) ? 0 : r.hashCode());
			result = prime * result + ((s == null) ? 0 : s.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			@SuppressWarnings("unchecked")
			TypeOfSourceAndResponse<S, R> other = (TypeOfSourceAndResponse<S, R>) obj;
			if (r == null) {
				if (other.r != null)
					return false;
			} else if (!r.equals(other.r))
				return false;
			if (s == null) {
				if (other.s != null)
					return false;
			} else if (!s.equals(other.s))
				return false;
			return true;
		}

	}

}
