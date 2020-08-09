package com.dxp.security.conf.core;

import com.dxp.security.constant.BusStatus;
import com.dxp.security.web.vo.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dxp
 * 2020/8/7 9:15 下午
 */
@Component
public class AjaxAccessDeniedHandler extends AbstractFilterResponseOutPrint implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        printJson(R.err(BusStatus.FORBIDDEN), httpServletResponse);
    }
}
