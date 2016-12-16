package cz.fi.muni.pa165.security;

import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.enums.Role;
import cz.fi.muni.pa165.service.interfaces.EmployeeService;
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
    private EmployeeService employeeService;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(final String email)
            throws UsernameNotFoundException {

        Employee user = employeeService.findByEmail(email);
        List<GrantedAuthority> authorities =
                buildUserAuthority(user.getRole());

        return buildUserForAuthentication(user, authorities);

    }

    // Converts Employee user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(Employee user,
                                            List<GrantedAuthority> authorities) {
        System.out.println("--------------------------------------------------------------");
        System.out.println(user.getEmail() + "  pass:"  + user.getPasswordHash());
        return new User(user.getEmail(), user.getPasswordHash(),
                true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Role role) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        switch (role) {
            case EMPLOYEE:
                setAuths.add(new SimpleGrantedAuthority(Role.EMPLOYEE.toString()));
                break;
            case SERVICEMAN:
                setAuths.add(new SimpleGrantedAuthority(Role.EMPLOYEE.toString()));
                setAuths.add(new SimpleGrantedAuthority(Role.SERVICEMAN.toString()));
                break;
            case ADMINISTRATOR:
                setAuths.add(new SimpleGrantedAuthority(Role.EMPLOYEE.toString()));
                setAuths.add(new SimpleGrantedAuthority(Role.SERVICEMAN.toString()));
                setAuths.add(new SimpleGrantedAuthority(Role.ADMINISTRATOR.toString()));
                break;
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }

}
