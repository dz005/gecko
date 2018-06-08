package org.gecko.modular.code.provider;

import org.gecko.core.gen.modal.GenGlobal;
import org.gecko.modular.code.entity.Project;
import org.gecko.modular.code.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: dengzhi
 * @date: 2018/6/6
 */
@Component
public class GenGlobalProviderImpl implements GenGlobalProvider {

    @Autowired
    private IProjectService projectService;

    @Override
    public GenGlobal getGenGlobal(Long projectId) {
        Project project = projectService.selectById(projectId);
        GenGlobal genGlobal = new GenGlobal();
        //TODO
        genGlobal.setAuthor("zhi.deng");
        genGlobal.setCodePackage(project.getCodePackage());
        genGlobal.setEncoded(project.getEncoded());
        genGlobal.setHtmlPath(project.getHtmlPath());
        genGlobal.setJsPath(project.getJsPath());
        genGlobal.setXmlPath(project.getXmlPath());
        return genGlobal;
    }

}
