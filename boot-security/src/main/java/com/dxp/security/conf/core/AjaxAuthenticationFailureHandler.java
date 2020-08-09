package com.dxp.security.conf.core;

import com.dxp.security.constant.BusStatus;
import com.dxp.security.web.vo.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败
 *
 * @author dxp
 * 2020/8/7 9:04 下午
 */
@Component
public class AjaxAuthenticationFailureHandler extends AbstractFilterResponseOutPrint implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        printJson(R.err(BusStatus.AUTHORIZED_ERROR), httpServletResponse);
    }
}
