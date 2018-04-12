package ua.karatnyk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.karatnyk.entity.UserEntity;
import ua.karatnyk.mapper.UserMapper;
import ua.karatnyk.repository.UserRepository;

@Service("userDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UserEntity entity = userRepository.findUserByLoginAndActice(login);
		if(entity == null) throw new UsernameNotFoundException("User not found");
		
		return UserMapper.toSecurityUser(entity);
	}

}
