package com.example.sbb.config.auth;

import com.example.sbb.user.User;
import com.example.sbb.user.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserJPARepository userJPARepository;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userJPARepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            return null;
        }
        else {
            User user = optionalUser.get();
            return new CustomUserDetails(user);
        }
    }
}
