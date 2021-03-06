package com.example;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private static Logger logger = LogManager.getLogger("MyUserDetailsService.class");

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameToFind) throws UsernameNotFoundException {
        User user = userRepository.findFirstByUsername(usernameToFind);
        if (user == null) {
            logger.error("->Could not find user with name: " + usernameToFind);
            throw new UsernameNotFoundException("Username was not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
    }

    private Set<GrantedAuthority> getAuthorities(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        logger.error("->Getting roles for user: " + user.getUsername());
        for (Role role : user.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            logger.error("  ->Role: " + role.getRole());
            authorities.add(grantedAuthority);
        }
        return authorities;
    }
}
