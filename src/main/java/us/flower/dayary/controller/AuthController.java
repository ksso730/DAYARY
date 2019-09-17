package us.flower.dayary.controller;

import java.net.URI;
import java.util.Collections;

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
public class AuthController {

	
	 @Autowired
	 AuthenticationManager authenticationManager;
	 @Autowired
	 JwtTokenProvider tokenProvider;
	 @Autowired
	 PeopleRepository peopleRepository;
	 @Autowired
	 RoleRepository roleRepository;
	
	 private BCRYPT bcrypt;
	public AuthController(BCRYPT bcrypt){
		this.bcrypt=bcrypt;
	}

	 
	 @PostMapping("/signin")
	    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getId(),
	                        loginRequest.getPassword()
	                )
	        );

	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        String jwt = tokenProvider.generateToken(authentication);
	        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	    }
	 @PostMapping("/signup")
	    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
	        if(peopleRepository.existsById(signUpRequest.getId())) {
	            return new ResponseEntity(new ApiResponse(false, "id is already taken!"),
	                    HttpStatus.BAD_REQUEST);
	        }

	      
	        // Creating user's account
	        People user = new People(signUpRequest.getId(), signUpRequest.getPassword(),
	        						signUpRequest.getName(), signUpRequest.getPhoto(), signUpRequest.getActivation());

	        user.setPassword(bcrypt.hashpw(user.getPassword()));

	        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
	                .orElseThrow(() -> new AppException("User Role not set."));

	        user.setRoles(Collections.singleton(userRole));

	        People result = peopleRepository.save(user);

	        URI location = ServletUriComponentsBuilder
	                .fromCurrentContextPath().path("/{username}")
	                .buildAndExpand(result.getId()).toUri();

	        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	    }
}
