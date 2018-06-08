package org.gecko.modular.code.handler;

import org.gecko.core.metadata.ColumnMetaData;
import org.gecko.core.metadata.PrimaryKeyMetaData;
import org.gecko.core.metadata.TableMetaData;
import org.gecko.modular.code.entity.Db;

import java.util.List;

/**
 * 导入单个表
 *
 * @author: dengzhi
 * @date: 2018/6/4
 */
public interface SingleTableHandler {

    void handle(Db db, String tablePrefix, TableMetaData tmd, List<ColumnMetaData> cmdList, List<PrimaryKeyMetaData> pkmdList);

}
