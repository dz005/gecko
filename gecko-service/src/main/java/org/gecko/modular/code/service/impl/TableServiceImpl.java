package org.gecko.modular.code.service.impl;

import org.gecko.modular.code.entity.Table;
import org.gecko.modular.code.mapper.TableMapper;
import org.gecko.modular.code.service.ITableService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 自定义表 服务实现类
 * </p>
 *
 * @author zhi.deng
 * @since 2018-06-07
 */
@Service
public class TableServiceImpl extends ServiceImpl<TableMapper, Table> implements ITableService {

}
