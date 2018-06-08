package org.gecko.core.gen.converter;

import org.gecko.core.base.converter.Converter;
import org.gecko.core.gen.enums.DataType;
import org.gecko.core.metadata.ColumnMetaData;

/**
 * 数据类型转换器
 *
 * @author: dengzhi
 * @date: 2018/6/6
 */
public class DataTypeConverter implements Converter<ColumnMetaData, DataType> {

    @Override
    public DataType convert(ColumnMetaData source) {
        String t = source.getTypeName().toLowerCase();
        if (!t.contains("char") && !t.contains("text")) {
            if (t.contains("bigint")) {
                return DataType.LONG;
            } else if (t.contains("int")) {
                return DataType.INTEGER;
            } else if (!t.contains("date") && !t.contains("time") && !t.contains("year")) {
                if (t.contains("text")) {
                    return DataType.STRING;
                } else if (t.contains("bit")) {
                    return DataType.BOOLEAN;
                } else if (t.contains("decimal")) {
                    return DataType.BIG_DECIMAL;
                } else if (t.contains("clob")) {
                    return DataType.CLOB;
                } else if (t.contains("blob")) {
                    return DataType.BLOB;
                } else if (t.contains("binary")) {
                    return DataType.BYTE_ARRAY;
                } else if (t.contains("float")) {
                    return DataType.FLOAT;
                } else if (t.contains("double")) {
                    return DataType.DOUBLE;
                } else {
                    return !t.contains("json") && !t.contains("enum") ? DataType.STRING : DataType.STRING;
                }
            } else {
                return DataType.DATE;
            }
        } else {
            return DataType.STRING;
        }
    }
}
