package com.dxp.shiro.support;

import com.dxp.shiro.common.ErrorEnum;
import com.dxp.shiro.web.vo.R;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 异常统一处理
 *
 * @author carzy.
 * @date 16:53 2018/12/26
 */
@RestControllerAdvice
@Order(-1)
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(BindException.class)
    public R<String> bindException(BindException ex) {
        List<FieldError> errors = ex.getFieldErrors();
        String errorMsg = errors.stream().map(e -> e.getField() + ":" + e.getDefaultMessage()).collect(Collectors.joining(","));
        return R.err(errorMsg, ErrorEnum.E_10003);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<String> bindException(MethodArgumentNotValidException mx) {
        List<FieldError> errors = mx.getBindingResult().getFieldErrors();
        String errorMsg = errors.stream().map(e -> e.getField() + ":" + e.getDefaultMessage()).collect(Collectors.joining(","));
        return R.err(errorMsg, ErrorEnum.E_10003);
    }

    /**
     * 授权异常 (UnauthenticatedException, HostUnauthorizedException, UnauthorizedException)
     *
     * @param e 没有权限的异常
     * @return R
     */
    @ExceptionHandler(AuthorizationException.class)
    public R<String> authorizationException(AuthorizationException e) {
        e.printStackTrace();
        LOGGER.debug("authorization err");
        return R.err("Not authorized", ErrorEnum.E_701);
    }

    /**
     * 认证异常
     * <p>
     * org.apache.shiro.authc.pam.UnsupportedTokenException 身份令牌异常，不支持的身份令牌
     * org.apache.shiro.authc.UnknownAccountException       未知账户/没找到帐号,登录失败
     * org.apache.shiro.authc.LockedAccountException        帐号锁定
     * org.apache.shiro.authz.DisabledAccountException      用户禁用
     * org.apache.shiro.authc.ExcessiveAttemptsException    登录重试次数，超限。只允许在一段时间内允许有一定数量的认证尝试
     * org.apache.shiro.authc.ConcurrentAccessException     一个用户多次登录异常：不允许多次登录，只能登录一次 。即不允许多处登录
     * org.apache.shiro.authz.AccountException              账户异常
     * org.apache.shiro.authz.ExpiredCredentialsException   过期的凭据异常
     * org.apache.shiro.authc.IncorrectCredentialsException 错误的凭据异常
     * org.apache.shiro.authc.CredentialsException          凭据异常
     * org.apache.shiro.authc.AuthenticationException       上面异常的父类
     *
     * @param e 没有权限的异常
     * @return ModelAndView
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.OK)
    public R<String> authenticationException(AuthenticationException e) {
        if (e instanceof UnknownAccountException) {
            return R.err("", ErrorEnum.E_704);
        } else {
            return R.err("", ErrorEnum.E_702);
        }
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R<String> missingServletRequestParameterException(Exception e) {
        return R.err(e.getMessage(), ErrorEnum.E_10003);
    }

    @ExceptionHandler(SQLException.class)
    public R<String> sqlException(SQLException e) {
        return R.err(e.getMessage(), ErrorEnum.E_601);
    }

    @ExceptionHandler(Exception.class)
    public R<String> exception(Exception e) {
        LOGGER.error("Exception err:", e);
        return R.err(e.getMessage(), ErrorEnum.E_500);
    }

}
