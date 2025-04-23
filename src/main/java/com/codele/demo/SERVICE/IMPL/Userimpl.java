package com.codele.demo.SERVICE.IMPL;

import com.codele.demo.REPOSITORY.UserRepository;
import com.codele.demo.SERVICE.Userservice;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Userimpl implements Userservice {

    private final UserRepository userRepository;


   @Override
   public UserDetailsService userDetailsService(){
       return new UserDetailsService() {
           @Override
           public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
               return (UserDetails) userRepository.findByEmail(username)
                       .orElseThrow(()-> new UsernameNotFoundException("user not found "));

           }
       };
   }


}
