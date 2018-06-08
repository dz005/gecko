package org.gecko.core.base.converter;

public interface Converter<S, T> {

    T convert(S source);

}