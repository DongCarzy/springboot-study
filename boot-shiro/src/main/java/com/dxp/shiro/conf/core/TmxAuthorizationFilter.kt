package com.dxp.shiro.conf.core

import com.dxp.shiro.common.Constants
import com.dxp.shiro.common.ErrorEnum
import com.dxp.shiro.web.vo.R
import com.dxp.shiro.web.vo.R.Companion.err
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.shiro.SecurityUtils
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter
import org.slf4j.LoggerFactory
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author carzy
 *
 *
 * 对没有登录的请求进行拦截, 全部返回json信息. 覆盖掉shiro原本的跳转login.jsp的拦截方式
 * @date 2018/12/28 10:24
 */
class TmxAuthorizationFilter : FormAuthenticationFilter() {
    override fun onAccessDenied(request: ServletRequest, response: ServletResponse): Boolean {
        val httpServletRequest = request as HttpServletRequest
        if (httpServletRequest.method == HttpMethod.OPTIONS.name) {
            return true
        }
        printErrJson(ErrorEnum.E_702, response, request)
        return false
    }

    override fun isAccessAllowed(request: ServletRequest, response: ServletResponse, mappedValue: Any?): Boolean {
        val allowed = super.isAccessAllowed(request, response, mappedValue)
        if (allowed) {
            val session = SecurityUtils.getSubject().session
            val obj = session.getAttribute(Constants.SESSION_USER_INFO)
            if (obj == null) {
                printErrJson(ErrorEnum.E_703, response, request)
                return false
            }
        }
        return allowed
    }

    private fun printErrJson(errorEnum: ErrorEnum, response: ServletResponse, request: ServletRequest) {
        val req = request as HttpServletRequest
        val origin = req.getHeader("Origin")
        val result: R<*> = err("", errorEnum)
        val res = response as HttpServletResponse
        res.status = HttpStatus.OK.value()
        res.characterEncoding = "UTF-8"
        res.contentType = "application/json"
        res.setHeader("Access-Control-Allow-Origin", origin)
        res.setHeader("Access-Control-Allow-Headers", "x-auth-token")
        res.setHeader("Access-Control-Allow-Credentials", "true")
        res.setHeader("Access-Control-Allow-Methods", "GET,DELETE,POST,PUT,OPTIONS,PATCH")
        try {
            res.writer.use { printWriter ->
                printWriter.print(MAPPER.writeValueAsString(result))
                printWriter.flush()
            }
        } catch (e: Exception) {
            LOGGER.error("printErrJson", e)
        }
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(TmxAuthorizationFilter::class.java)
        private val MAPPER = ObjectMapper()
    }
}