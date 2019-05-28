package com.oc.climbingtoo.security;

import com.oc.climbingtoo.entity.User;
import com.oc.climbingtoo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;




@Service
@RequiredArgsConstructor
public class SecurityServiceImpl  {

    // private final AuthenticationManager authenticationManager;

    private final UserService userService;


    public User getCurrentUser() {
        Object user = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (user instanceof User) {
            return ((User) user);
        }
        return null;
    }


    public void login(String username, String password) {
        User user = userService.findByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, password, user.getRoleAsGrantedAuthorities());

        // authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

   /* @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    } */
}


