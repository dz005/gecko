package org.gecko.modular.code.service.impl;

import org.gecko.modular.code.entity.Project;
import org.gecko.modular.code.mapper.ProjectMapper;
import org.gecko.modular.code.service.IProjectService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目 服务实现类
 * </p>
 *
 * @author zhi.deng
 * @since 2018-06-01
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

}
