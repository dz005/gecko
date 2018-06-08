package org.gecko.modular.code.provider;

import org.gecko.core.gen.modal.GenEntity;

import java.util.List;

/**
 *
 * @author: dengzhi
 * @date: 2018/6/6
 */
public interface GenEntityProvider {

    List<GenEntity> getGenEntityList(Long projectId, List<Long> tableIds);
}
