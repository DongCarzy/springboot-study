package com.dxp.security.conf.core;

import com.dxp.security.web.vo.R;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出
 *
 * @author dxp
 * 2020/8/7 9:19 下午
 */
public class AjaxLogoutSuccessHandler extends AbstractFilterResponseOutPrint implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        printJson(R.suc(""), httpServletResponse);
    }
}
