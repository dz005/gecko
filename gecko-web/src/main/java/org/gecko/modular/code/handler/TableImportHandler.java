package org.gecko.modular.code.handler;

import java.util.List;
import java.util.Map;

/**
 * 导入表
 *
 * @author: dengzhi
 * @date: 2018/6/4
 */
public interface TableImportHandler {

    /**
     * 导入表，并返回错误信息
     *
     * @param dbId
     * @param tablePrefix
     * @param tableNames
     * @return
     * @throws Exception
     */
    Map<String, Object> handle(Long dbId, String tablePrefix, List<String> tableNames) throws Exception;

}
