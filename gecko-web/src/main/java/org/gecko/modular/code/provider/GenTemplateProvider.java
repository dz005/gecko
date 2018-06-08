package org.gecko.modular.code.provider;

import org.gecko.core.gen.modal.GenTemplate;

import java.util.List;

/**
 * @author: dengzhi
 * @date: 2018/6/6
 */
public interface GenTemplateProvider {

    /**
     * 获取指定项目所有有效的模板
     *
     * @param projectId
     * @return
     */
    List<GenTemplate> getGenTemplateList(Long projectId);

    GenTemplate getGenTemplate(Long templateId);

}
