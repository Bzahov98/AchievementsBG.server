package com.bg.bzahov.achievementsBG.security.jwt;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.bg.bzahov.achievementsBG.constants.SecurityConstants.CONTENT_TYPE;
import static com.bg.bzahov.achievementsBG.constants.SecurityConstants.ENCODING_UTF_8;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING_UTF_8);
        response.getWriter().write(
                createErrorBody(authException)
        );
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: " + authException.getMessage());
//        throw new UsernameNotFoundException("Unauthorized: " + authException.getMessage());

    }

    @NotNull
    private static String createErrorBody(AuthenticationException authException) {
        return "{" +
                    "\"message\":\"Unauthorized: " + authException.getMessage() + "\"," +
                    "\"timestamp\": \"" + new Date().toInstant().toString() + "\"" +
                "}";
    }
}
