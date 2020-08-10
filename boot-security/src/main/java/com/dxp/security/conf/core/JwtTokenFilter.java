package com.dxp.security.conf.core;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * JWT
 *
 * @author dxp
 * 2020/8/9 2:29 下午
 */
public class JwtTokenFilter extends BasicAuthenticationFilter {

    public JwtTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public JwtTokenFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, @NonNull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = req.getHeader("token");
        if (jwtToken == null || "".equals(jwtToken)) {
            filterChain.doFilter(req, response);
        } else {
            Claims claims = Jwts.parser().setSigningKey("dxp_20200809").parseClaimsJws(jwtToken)
                    .getBody();
            String username = claims.getSubject();
            if (claims.getExpiration().after(new Date())) {
                List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(token);
                filterChain.doFilter(req, response);
            } else {
                filterChain.doFilter(req, response);
            }
        }
    }
}
