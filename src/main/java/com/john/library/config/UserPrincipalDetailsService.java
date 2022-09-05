//package com.john.library.config;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.john.library.entity.User;
//import com.john.library.repo.UserRepository;
//
//@Service
//public class UserPrincipalDetailsService implements UserDetailsService {
//    private UserRepository userRepository;
//
//    public UserPrincipalDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = this.userRepository.findByEmail(s);
//        UserPrincipal userPrincipal = new UserPrincipal(user);
//        return userPrincipal;
//    }
//}
