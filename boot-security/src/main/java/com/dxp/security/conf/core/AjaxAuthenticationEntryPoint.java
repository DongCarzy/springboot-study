package com.dxp.security.conf.core;

import com.dxp.security.constant.BusStatus;
import com.dxp.security.web.vo.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未认证
 *
 * @author dxp
 * 2020/8/7 8:49 下午
 */
public class AjaxAuthenticationEntryPoint extends AbstractFilterResponseOutPrint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        printJson(R.err(BusStatus.UNAUTHORIZED), httpServletResponse);
    }
}
