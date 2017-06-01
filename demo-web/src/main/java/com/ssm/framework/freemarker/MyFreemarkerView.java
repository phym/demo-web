package com.ssm.framework.freemarker;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

/**
 * 定义BasePath 从写后可以在模板中通过${basePath}获取路径
 * MyFreemarkerView.
 *
 * @author zax
 */
public class MyFreemarkerView extends FreeMarkerView {
    private static String BASE_PATH="basePath";
    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        super.exposeHelpers(model, request);
        String basePath =  request.getContextPath() + "/";
        model.put(BASE_PATH, basePath);
    }
}
