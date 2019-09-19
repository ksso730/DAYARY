package us.flower.dayary.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import us.flower.dayary.domain.People;
import us.flower.dayary.repository.PeopleRepository;

@Service 
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	PeopleRepository peopleRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		People user = peopleRepository.findByEmail(email);
		return UserPrincipal.create(user);
	}
 
	public UserDetails loadUserById(Long id) {
		  People user = peopleRepository.findById(id).orElseThrow(
		            () -> new UsernameNotFoundException("User not found with id : " + id)
		        );
		      

		        return UserPrincipal.create(user);
	}

	

//	@Override
//	@Transactional
//	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
//		 People user = peopleRepository.findById(id);
//		 
//		return UserPrincipal.create(user);
//	}
//
//	public UserDetails loadUserByNo(Long no) {
//		 People user = peopleRepository.findById(no).orElseThrow(
//		            () -> new UsernameNotFoundException("User not found with id : " + no)
//			        );
//		return UserPrincipal.create(user);
//	}

//	@Override
//	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
//		  People user = peopleRepository.findById(id).orElseThrow(
//		            () -> new UsernameNotFoundException("User not found with id : " + id)
//		        );
//		return UserPrincipal.create(user);
//		      
//	}
//	/*이 메서드가 로그인 접속할수 있는 메서드이다*/
//	@Transactional
//    public UserDetails loadUserByNo(Long no) {
//		  People user = peopleRepository.findByNo(no).orElseThrow(
//		            () -> new UsernameNotFoundException("User not found with id : " + no)
//		        );
//		return UserPrincipal.create(user);
//    }
	

}