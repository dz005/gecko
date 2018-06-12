package org.gecko.modular.code.service.impl;

import org.gecko.modular.code.entity.Template;
import org.gecko.modular.code.mapper.TemplateMapper;
import org.gecko.modular.code.service.ITemplateService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 模板 服务实现类
 * </p>
 *
 * @author zhi.deng
 * @since 2018-06-12
 */
@Service
public class TemplateServiceImpl extends ServiceImpl<TemplateMapper, Template> implements ITemplateService {

}
