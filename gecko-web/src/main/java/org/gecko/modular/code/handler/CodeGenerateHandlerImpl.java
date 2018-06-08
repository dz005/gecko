package org.gecko.modular.code.handler;

import lombok.extern.slf4j.Slf4j;
import org.gecko.core.gen.modal.GenEntity;
import org.gecko.core.gen.modal.GenGlobal;
import org.gecko.core.gen.modal.GenTemplate;
import org.gecko.core.gen.parser.Parser;
import org.gecko.modular.code.provider.GenEntityProvider;
import org.gecko.modular.code.provider.GenGlobalProvider;
import org.gecko.modular.code.provider.GenTemplateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * 代码生成处理者实现
 *
 * @author: dengzhi
 * @date: 2018/6/7
 */
@Slf4j
@Component
public class CodeGenerateHandlerImpl implements CodeGenerateHandler {

    @Autowired
    private GenGlobalProvider genGlobalProvider;
    @Autowired
    private GenEntityProvider genEntityProvider;
    @Autowired
    private GenTemplateProvider genTemplateProvider;
    @Autowired
    private Parser parser;

    File generate(Long projectId, List<Long> tableIds) {
        GenGlobal genGlobal = genGlobalProvider.getGenGlobal(projectId);
        List<GenEntity> genEntityList = genEntityProvider.getGenEntityList(projectId, tableIds);
        List<GenTemplate> genTemplate = genTemplateProvider.getGenTemplateList(projectId);

        return null;
    }
}
