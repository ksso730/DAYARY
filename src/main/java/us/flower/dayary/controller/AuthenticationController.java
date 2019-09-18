package us.flower.dayary.controller;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import us.flower.dayary.common.BCRYPT;
import us.flower.dayary.domain.People;
import us.flower.dayary.domain.Role;
import us.flower.dayary.domain.RoleName;
import us.flower.dayary.exception.AppException;
import us.flower.dayary.payload.ApiResponse;
import us.flower.dayary.payload.JwtAuthenticationResponse;
import us.flower.dayary.payload.LoginRequest;
import us.flower.dayary.payload.SignUpRequest;
import us.flower.dayary.repository.PeopleRepository;
import us.flower.dayary.repository.RoleRepository;
import us.flower.dayary.security.JwtTokenProvider;

@RestController
public class AuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtTokenProvider tokenProvider;
	@Autowired
	PeopleRepository peopleRepository;
	@Autowired
	RoleRepository roleRepository;

	private BCRYPT bcrypt;

	public AuthenticationController(BCRYPT bcrypt) {
		this.bcrypt = bcrypt;
	}

	@PostMapping("/signin")
	public Map<String, Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,HttpSession session) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String user_id = auth.getName();

//        try {
//			    if(peopleRepository.existsByEmail(loginRequest.getEmail())) {
//			    	People dbPeople=peopleRepository.findByEmail(loginRequest.getEmail());
//			    	
//			    	if(bcrypt.checkpw(loginRequest.getPassword(), dbPeople.getPassword())) {
//			    		SecurityContextHolder.getContext().setAuthentication(authentication);
//			    		returnData.put("code", "1");
//			    		returnData.put("message", "로그인 완료");
//			    		String jwt = tokenProvider.generateToken(authentication);
//			    		System.out.println("로그인 완료");
//			    		session.setAttribute("peopleNo",auth);//NO세션저장
//			    	}else {//비밀번호가 다르면
//			    		returnData.put("code","0");
//						returnData.put("message", "아이디 또는 비밀번호를 확인해주세요:(");
//						System.out.println("비번 틀릶");
//			    	}
//			    	
//			    	
//				}else {//없는 아이디면
//					returnData.put("code", "0");
//					returnData.put("message", "아이디 또는 비밀번호를 확인해주세요:(");
//					System.out.println("아이디 틀리");
//				}
//        }catch(Exception e) {
//        	e.printStackTrace(); 
//        	returnData.put("code","E4024");
//        	returnData.put("message","잠시 후, 다시 시도해주세요:(");
//		}
        try {
			if (peopleRepository.existsByEmail(loginRequest.getEmail())) {
				People dbPeople = peopleRepository.findByEmail(loginRequest.getEmail());
					if (bcrypt.checkpw(loginRequest.getPassword(), dbPeople.getPassword())) {// 비밀번호가맞다면
						session.setAttribute("peopleNo", auth);// NO세션저장
						session.setAttribute("peopleId", dbPeople.getId());// ID세션저장
						returnData.put("code", "1");
						returnData.put("message", "로그인 완료!");
					} else {// 비밀번호가다르면 
						returnData.put("code","0");
						returnData.put("message", "아이디 또는 비밀번호를 확인해주세요:(");
						System.out.println("비밅번호 트림!!");
					}
			} else {// 없는아이디이면
				returnData.put("code", "0");
				returnData.put("message", "아이디 또는 비밀번호를 확인해주세요:(");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnData.put("code", "E4024");
			returnData.put("message", "잠시 후, 다시 시도해주세요:(");
		}
		
		
		return returnData;
	}

	@PostMapping("/signup")
	public Map<String, Object> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (!peopleRepository.existsByEmail(signUpRequest.getEmail())) {
				// Creating user's account
				People user = new People(signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.getName(),
						signUpRequest.getPhoto(), signUpRequest.getActivation());

				user.setPassword(bcrypt.hashpw(user.getPassword()));

				Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
						.orElseThrow(() -> new AppException("User Role not set."));

				user.setRoles(Collections.singleton(userRole));

				People result = peopleRepository.save(user);

				URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{username}")
						.buildAndExpand(result.getId()).toUri();
				returnData.put("code", "1");
				returnData.put("message", "가입완료:)");
			} else {
				returnData.put("code", "2");
				returnData.put("message", "이미 가입 된 아이디입니다:(");
			}
		} catch (Exception e) {
			returnData.put("code", "E3290");
			returnData.put("message", "잠시 후, 다시 시도해주세요:(");
		}
		return returnData;
	}

}
