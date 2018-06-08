package org.gecko.modular.code.service.impl;

import org.gecko.modular.code.entity.User;
import org.gecko.modular.code.mapper.UserMapper;
import org.gecko.modular.code.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zhi.deng
 * @since 2018-06-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
