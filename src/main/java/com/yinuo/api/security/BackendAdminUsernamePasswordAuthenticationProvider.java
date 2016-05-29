package com.yinuo.api.security;

import com.google.common.base.Optional;
import com.yinuo.api.model.type.Authority;
import com.yinuo.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-28
 */
@EnableConfigurationProperties
public class BackendAdminUsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    public static final String INVALID_BACKEND_ADMIN_CREDENTIALS = "Invalid Backend Admin Credentials";

    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<String> username = (Optional) authentication.getPrincipal();
        Optional<String> password = (Optional) authentication.getCredentials();

        if (credentialsMissing(username, password) || credentialsInvalid(username, password)) {
            throw new BadCredentialsException(INVALID_BACKEND_ADMIN_CREDENTIALS);
        }

        return new UsernamePasswordAuthenticationToken(username.get(), null,
                AuthorityUtils.commaSeparatedStringToAuthorityList(Authority.ROLE_SYSTEM_ADMIN.name()));
    }

    private boolean credentialsMissing(Optional<String> username, Optional<String> password) {
        return !username.isPresent() || !password.isPresent();
    }

    private boolean credentialsInvalid(Optional<String> username, Optional<String> password) {
        return !isBackendAdmin(username.get()) || !userService.authenticate(username.get(), password.get());
    }

    private boolean isBackendAdmin(String username) {
        final List<String> authorities = userService.findAuthorities(username);
        if (authorities == null || authorities.isEmpty()) {
            return false;
        }
        return authorities.contains(Authority.ROLE_SYSTEM_ADMIN.name());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(BackendAdminUsernamePasswordAuthenticationToken.class);
    }
}
