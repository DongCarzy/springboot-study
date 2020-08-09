package com.dxp.security.conf.core;

import com.dxp.security.common.JWTTokenUtil;
import com.dxp.security.web.entity.sys.User;
import com.dxp.security.web.vo.R;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 登录成功处理
 *
 * @author carzy
 * @date 2020/8/7
 */
@Component
public class AjaxAuthenticationSuccessHandler extends AbstractFilterResponseOutPrint implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        String token = JWTTokenUtil.createAccessToken(user);
        HashMap<String, Object> re = new HashMap<>(4);
        re.put("user", user);
        re.put("token", token);
        printJson(R.suc(re), httpServletResponse);
    }
}
