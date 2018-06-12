package org.gecko.modular.code.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.gecko.core.base.JsonResponse;
import org.gecko.core.gen.enums.Extension;
import org.gecko.core.gen.modal.GenEntity;
import org.gecko.core.gen.modal.GenGlobal;
import org.gecko.core.gen.modal.GenTemplate;
import org.gecko.core.gen.parser.Parser;
import org.gecko.modular.code.provider.GenEntityProvider;
import org.gecko.modular.code.provider.GenGlobalProvider;
import org.gecko.modular.code.provider.GenTemplateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.List;

/**
 * @author: dengzhi
 * @date: 2018/6/6
 */
@Slf4j
@Controller
@RequestMapping("/gen")
public class GenController {

    @Autowired
    private GenGlobalProvider genGlobalProvider;
    @Autowired
    private GenEntityProvider genEntityProvider;
    @Autowired
    private GenTemplateProvider genTemplateProvider;
    @Autowired
    private Parser parser;

    private String rootPath = "D://gecko";

    @ResponseBody
    @RequestMapping("/batch")
    public JsonResponse batch(@RequestParam Long projectId,
                              @RequestParam("tableIds[]") List<Long> tableIds) throws Exception {
        Assert.notNull(tableIds, "tableIds is empty");
        GenGlobal global = genGlobalProvider.getGenGlobal(projectId);
        Assert.notNull(global, "global is null");
        List<GenEntity> entityList = genEntityProvider.getGenEntityList(projectId, tableIds);
        Assert.notEmpty(entityList, "entityList is empty");
        List<GenTemplate> templateList = genTemplateProvider.getGenTemplateList(projectId);
        Assert.notEmpty(templateList, "templateList is empty");
        File genDir = new File(rootPath, global.getUuid());
        for (GenEntity entity : entityList) {
            //  try {
            singleGen(genDir, templateList, entity, global);
//            } catch (Exception e) {
//                log.error(MessageFormat.format("表 [{0}] 生成代码时出现异常", entity.getTableName()), e);
//            }
        }
        return JsonResponse.SUCCESS;
    }

    private String getDirPath(GenTemplate template, GenGlobal global) {
        String fileName = template.getFileName();
        String globalPath = null;
        if (StringUtils.hasText(fileName)) {
            String tmp = fileName.toLowerCase();
            if (tmp.endsWith(Extension.JS)) {
                globalPath = global.getJsPath();
            } else if (tmp.endsWith(Extension.HTML)) {
                globalPath = global.getHtmlPath();
            } else if (tmp.endsWith(Extension.XML)) {
                globalPath = global.getXmlPath();
            } else if (tmp.endsWith(Extension.JAVA)) {
                globalPath = "java" + File.separator + global.getCodePackage();
            }
        }
        globalPath = StringUtils.hasText(globalPath) ? globalPath.replaceAll("\\.", "\\/") : "";
        String templatePath = StringUtils.hasText(template.getFilePath()) ?
                template.getFilePath().replaceAll("\\.", "\\/") : "";
        return globalPath + File.separator + templatePath;
    }

    private String getFileName(GenTemplate template, GenEntity entity) {
        String fileName = template.getFileName();
        String entityName = entity.getEntityName();
        if (fileName.endsWith(".js") || fileName.endsWith(".html")) {
            return String.format(fileName, entityName.toLowerCase());
        } else {
            return String.format(fileName, entityName);
        }
    }

    protected void singleGen(File genDir, List<GenTemplate> templateList, GenEntity entity, GenGlobal global) throws Exception {
        for (GenTemplate template : templateList) {
            File path = new File(genDir, getDirPath(template, global));
            if (!path.exists()) {
                path.mkdirs();
            }
            String content = parser.parse(template, entity, global);
            File file = new File(path, getFileName(template, entity));
            String encoding = StringUtils.hasText(global.getEncoding()) ? global.getEncoding() : "UTF-8";
            FileUtils.writeStringToFile(file, content, encoding);
        }
    }
}
