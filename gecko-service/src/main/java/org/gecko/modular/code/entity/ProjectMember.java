package org.gecko.modular.code.entity;

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
 * 项目成员
 * </p>
 *
 * @author zhi.deng
 * @since 2018-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_project_member")
public class ProjectMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("project_id")
    private Long projectId;
    @TableField("user_id")
    private Long userId;


    public static final String PROJECT_ID = "project_id";

    public static final String USER_ID = "user_id";

}
