package com.bg.bzahov.achievementsBG.security.jwt;

import com.bg.bzahov.achievementsBG.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.bg.bzahov.achievementsBG.constants.SecurityConstants.AUTH_TOKEN_TYPE_BEARER;
import static com.bg.bzahov.achievementsBG.constants.SecurityConstants.HEADER_AUTHORIZATION;

@AllArgsConstructor
@NoArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTGenerator tokenGenerator;
    @Autowired
    private UserService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        String token = getJWTFromRequest(request);
        if (StringUtils.hasText(token) && tokenGenerator.validateToken(token)) {
            String username = tokenGenerator.getUsernameFromJWT(token);

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null,
                            userDetails.getAuthorities()
                    );

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HEADER_AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(AUTH_TOKEN_TYPE_BEARER)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
