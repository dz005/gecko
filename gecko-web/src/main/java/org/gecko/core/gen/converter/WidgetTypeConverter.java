package org.gecko.core.gen.converter;

import org.gecko.core.base.converter.Converter;
import org.gecko.core.gen.enums.DataType;
import org.gecko.core.gen.enums.WidgetType;

/**
 * 控件转换器
 *
 * @author: dengzhi
 * @date: 2018/6/6
 */
public class WidgetTypeConverter implements Converter<DataType, WidgetType> {

    @Override
    public WidgetType convert(DataType source) {
        switch (source) {
            case BASE_INT:
                return WidgetType.NUMBER;
            case BASE_BOOLEAN:
            case BOOLEAN:
                return WidgetType.SWITCH;
            case DATE:
            case LOCAL_DATE:
                return WidgetType.DATE;
            case TIME:
            case LOCAL_TIME:
                return WidgetType.TEXT;
            case TIMESTAMP:
            case LOCAL_DATE_TIME:
                return WidgetType.DATE_TIME;
            default:
                return WidgetType.TEXT;
        }
    }
}
