package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.model.Authority;
import com.app.model.EndUser;
import com.app.repository.EndUserRepository;

@Service
public class EndUserDetailsService implements UserDetailsService{

	
	
	
	@Autowired
	private EndUserRepository endUserRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Optional<EndUser> opt= endUserRepository.findByEmail(username);

		if(opt.isPresent()) {
			
			
			//return new CustomerUserDetails(opt.get());
			
			EndUser endUser= opt.get();
			
			List<GrantedAuthority> authorities = new ArrayList<>();
		
			
			
			List<Authority> auths= endUser.getAuthorities();
			
			for(Authority auth:auths) {
				SimpleGrantedAuthority sga=new SimpleGrantedAuthority(auth.getName());
				System.out.println("siga "+sga);
				authorities.add(sga);
			}
			
			System.out.println("granted authorities "+authorities);
			
			
			return new User(endUser.getEmail(), endUser.getPassword(), authorities);
			
			
			
		}else
			throw new BadCredentialsException("User Details not found with this username: "+username);
		
		
		
		
		
	}
	
	
//	 private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
//	        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//	        for (Authority authority : authorities) {
//	            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
//	        }
//	        return grantedAuthorities;
//	    }

}
