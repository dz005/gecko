package org.gecko.core.base.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ConvertUtils {

    public static <S, T> List<T> convert(Converter<S, T> converter, Collection<S> sources) {
        if (sources == null) {
            return null;
        }
        List<T> list = new ArrayList<T>();
        for (S s : sources) {
            list.add(converter.convert(s));
        }
        return list;
    }

}
