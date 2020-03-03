package springboot.thymeleaf.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import springboot.thymeleaf.entity.Student;
import springboot.thymeleaf.repository.StudentRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("coming here:"+username);
		Student user = studentRepository.findByUsername(username);
		
//SimpleGrantedAuthority grantAuthoritiesList = new SimpleGrantedAuthority("ROLE_USER");
		
		List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
		List<String> strAutho = new ArrayList<String>();
		strAutho.add("ROLE_USER");
		for (String string : strAutho) {
			authorityList.add(new SimpleGrantedAuthority(string));
		}
		
		User user1 = new User(user.getUsername(),
				user.getPassword(), authorityList);
		return user1;
		//System.out.println("username:"+user.toString());
		//user.orElseThrow(()-> new UsernameNotFoundException("Not Found:"+ username ));
		//return user.map(MyUserDetails::new).get();
	}

}
