package io.umid.supportservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationUserDetailsService implements
        AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private final UserDetailsService userDetailsService;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {

        if (token.getPrincipal() instanceof AccessToken accessToken) {
            return userDetailsService.loadUserByUsername(accessToken.subject());
        }

        throw new UsernameNotFoundException("Principal must be of type AccessToken");
    }
}
