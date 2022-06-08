package com.cds.org.security;

import com.cds.org.model.User;
import com.cds.org.persistence.UserRepository;
import com.cds.org.security.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user  = userRepository.findByUserName(username);
        user.orElseThrow(()-> new UsernameNotFoundException("user tried to access API does not exist in DB"+ username));

        return user.map(MyUserDetail::new).get();
    }
}
