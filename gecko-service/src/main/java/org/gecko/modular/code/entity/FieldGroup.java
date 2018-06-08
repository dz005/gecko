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
 * 字段组
 * </p>
 *
 * @author zhi.deng
 * @since 2018-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_field_group")
public class FieldGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 表ID
     */
    @TableField("table_id")
    private Long tableId;
    private String name;
    /**
     * 标题
     */
    private String title;
    /**
     * 排序
     */
    @TableField("sort_num")
    private Integer sortNum;


    public static final String ID = "id";

    public static final String TABLE_ID = "table_id";

    public static final String NAME = "name";

    public static final String TITLE = "title";

    public static final String SORT_NUM = "sort_num";

}
