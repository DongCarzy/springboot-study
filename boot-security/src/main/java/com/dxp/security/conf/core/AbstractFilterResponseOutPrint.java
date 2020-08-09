package com.dxp.security.conf.core;

import com.dxp.security.web.vo.R;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dxp
 * 2020/8/9 3:22 下午
 */
public abstract class AbstractFilterResponseOutPrint {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    protected void printJson(R<?> r, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(MAPPER.writeValueAsString(r));
    }
}
