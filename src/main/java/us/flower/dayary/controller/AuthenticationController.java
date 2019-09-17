package us.flower.dayary.controller;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
	public Map<String, Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		returnData.put("code", "1");
		String jwt = tokenProvider.generateToken(authentication);
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
