package org.gecko.modular.code.service.impl;

import org.gecko.modular.code.entity.Db;
import org.gecko.modular.code.mapper.DbMapper;
import org.gecko.modular.code.service.IDbService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据库 服务实现类
 * </p>
 *
 * @author zhi.deng
 * @since 2018-06-08
 */
@Service
public class DbServiceImpl extends ServiceImpl<DbMapper, Db> implements IDbService {

}
