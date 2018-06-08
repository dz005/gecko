package org.gecko.modular.code.service.impl;

import org.gecko.modular.code.entity.ProjectMember;
import org.gecko.modular.code.mapper.ProjectMemberMapper;
import org.gecko.modular.code.service.IProjectMemberService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目成员 服务实现类
 * </p>
 *
 * @author zhi.deng
 * @since 2018-06-01
 */
@Service
public class ProjectMemberServiceImpl extends ServiceImpl<ProjectMemberMapper, ProjectMember> implements IProjectMemberService {

}
