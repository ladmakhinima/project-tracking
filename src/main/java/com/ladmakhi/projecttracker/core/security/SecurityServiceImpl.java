package com.ladmakhi.projecttracker.core.security;

import com.ladmakhi.projecttracker.features.users.User;
import com.ladmakhi.projecttracker.features.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Is Not Found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), null);
    }
}
