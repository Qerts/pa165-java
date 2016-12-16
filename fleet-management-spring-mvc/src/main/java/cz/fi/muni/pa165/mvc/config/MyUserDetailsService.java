package cz.fi.muni.pa165.mvc.config;

import cz.fi.muni.pa165.dto.EmployeeDTO;
import cz.fi.muni.pa165.enums.Role;
import cz.fi.muni.pa165.facade.EmployeeFacade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jozef Krcho
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Inject
    private EmployeeFacade employeeFacade;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(final String email)
            throws UsernameNotFoundException {

        EmployeeDTO user = employeeFacade.findEmployeeByEmail(email);
        List<GrantedAuthority> authorities =
                buildUserAuthority(user.getRole());

        return buildUserForAuthentication(user, authorities);

    }

    // Converts Employee user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(EmployeeDTO user,
                                            List<GrantedAuthority> authorities) {
        return new User(user.getEmail(), user.getPasswordHash(),
                true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Role role) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        switch (role) {
            case EMPLOYEE:
                setAuths.add(new SimpleGrantedAuthority(Role.EMPLOYEE.name()));
                break;
            case SERVICEMAN:
                setAuths.add(new SimpleGrantedAuthority(Role.EMPLOYEE.name()));
                setAuths.add(new SimpleGrantedAuthority(Role.SERVICEMAN.name()));
                break;
            case ADMINISTRATOR:
                setAuths.add(new SimpleGrantedAuthority(Role.EMPLOYEE.name()));
                setAuths.add(new SimpleGrantedAuthority(Role.SERVICEMAN.name()));
                setAuths.add(new SimpleGrantedAuthority(Role.ADMINISTRATOR.name()));
                break;
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }

}
