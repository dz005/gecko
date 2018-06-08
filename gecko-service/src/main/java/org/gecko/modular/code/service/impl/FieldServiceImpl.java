package org.gecko.modular.code.service.impl;

import org.gecko.modular.code.entity.Field;
import org.gecko.modular.code.mapper.FieldMapper;
import org.gecko.modular.code.service.IFieldService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字段 服务实现类
 * </p>
 *
 * @author zhi.deng
 * @since 2018-06-07
 */
@Service
public class FieldServiceImpl extends ServiceImpl<FieldMapper, Field> implements IFieldService {

}
