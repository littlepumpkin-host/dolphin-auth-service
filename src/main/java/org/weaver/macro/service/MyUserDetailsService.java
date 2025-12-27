package org.weaver.macro.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.weaver.macro.model.UserPrincipal;
import org.weaver.macro.repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final ClientRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var client = repository.findByUsername(username);
        if (client.isPresent()) {
            return new UserPrincipal(client.get());
        }
        throw new UsernameNotFoundException("User not Found");
    }
}
