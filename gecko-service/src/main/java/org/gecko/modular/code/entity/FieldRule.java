package org.gecko.modular.code.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字段校验规则
 * </p>
 *
 * @author zhi.deng
 * @since 2018-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_field_rule")
public class FieldRule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 表ID
     */
    @TableField("table_id")
    private Long tableId;
    /**
     * 字段ID
     */
    @TableField("field_id")
    private Long fieldId;
    /**
     * 规则名称
     */
    private String name;
    /**
     * 规则值
     */
    private String value;
    /**
     * 规则提示
     */
    private String message;


    public static final String ID = "id";

    public static final String TABLE_ID = "table_id";

    public static final String FIELD_ID = "field_id";

    public static final String NAME = "name";

    public static final String VALUE = "value";

    public static final String MESSAGE = "message";

}
