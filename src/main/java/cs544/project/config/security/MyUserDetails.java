/**
 * @author Kiros Gebregewergs
 */

package cs544.project.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cs544.project.domain.User;
import cs544.project.repository.UserRepository;
import cs544.project.service.UserServiceImpl;

@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        final User user = userRepository.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + username
                    + "' not found");
        }

        return org.springframework.security.core.userdetails.User//
                .withUsername(username)//
                .password(user.getPassword())//
                .authorities(user.getRoleList())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }

}
