package it.opensource.ecompany.service.impl.util;

import it.opensource.ecompany.domain.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.List;

public class CustomerUserAuthorityUtils {

    private static final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");

    private static final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");

    private CustomerUserAuthorityUtils() {

    }

    public static Collection<? extends GrantedAuthority> createAuthorities(Customer customer) {

        String username = customer.getEmail();
        if (username.startsWith("admin")) {
            return ADMIN_ROLES;
        }
        return USER_ROLES;
    }
}
