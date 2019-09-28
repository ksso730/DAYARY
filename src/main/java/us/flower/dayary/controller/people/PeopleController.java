package us.flower.dayary.controller.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import us.flower.dayary.common.BCRYPT;
import us.flower.dayary.domain.People;
import us.flower.dayary.domain.Role;
import us.flower.dayary.domain.RoleName;
import us.flower.dayary.exception.AppException;
import us.flower.dayary.payload.JwtAuthenticationResponse;
import us.flower.dayary.payload.LoginRequest;
import us.flower.dayary.payload.SignUpRequest;
import us.flower.dayary.repository.people.PeopleRepository;
import us.flower.dayary.repository.people.RoleRepository;
import us.flower.dayary.security.JwtTokenProvider;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.net.URI;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PeopleController {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtTokenProvider tokenProvider;
	@Autowired
	PeopleRepository peopleRepository;
	@Autowired
	RoleRepository roleRepository;

	private BCRYPT bcrypt;

	public PeopleController(BCRYPT bcrypt) {
		this.bcrypt = bcrypt;
	}

	
	@RequestMapping("/people/signinWarning")
	public ModelAndView needLogin() throws Exception{
		ModelAndView mav=new ModelAndView("/people/signinwarning");
		
		return mav;
	}
	
	
	@PostMapping("/signin")
	@ResponseBody
	public Map<String, Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,HttpSession session
												,Model model,ModelAndView mav
												,Principal principal) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    
		
        try {
			if (peopleRepository.existsByEmail(loginRequest.getEmail())) {
				People dbPeople = peopleRepository.findByEmail(loginRequest.getEmail());
					if (bcrypt.checkpw(loginRequest.getPassword(), dbPeople.getPassword())) {// 비밀번호가맞다면
						session.setAttribute("peopleId", dbPeople.getId());// NO세션저장
						session.setAttribute("peopleEmail", dbPeople.getEmail());// ID세션저장
					
						returnData.put("code", "1");
							String jwt = tokenProvider.generateToken(authentication);
							JwtAuthenticationResponse csj= new JwtAuthenticationResponse(jwt);
					        model.addAttribute("csj",csj);
					        System.out.println(jwt);
					    	String savePage = (String)session.getAttribute("savePage");
					        
					    	
					        if(savePage!=null) {
								mav.setViewName("redirect:/"+savePage);
								session.setAttribute("savePage", null);
								returnData.put("savePage",savePage);
								returnData.put("code", "2");								
							}
						returnData.put("message", "로그인 완료!");
					} 
			} 
		} catch (Exception e) {
			e.printStackTrace();
			returnData.put("code", "E4024");
			returnData.put("message", "잠시 후, 다시 시도해주세요:(");
		}
      
		
		return returnData;
	}

	@PostMapping("/signup")
	@ResponseBody
	public Map<String, Object> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (!peopleRepository.existsByEmail(signUpRequest.getEmail())) {
				// Creating user's account
				People user = new People(signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.getName(),
						signUpRequest.getPhoto(), signUpRequest.getActivation());

				user.setPassword(bcrypt.hashpw(user.getPassword()));

				Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
						.orElseThrow(() -> new AppException("먼저 Role테이블에 insert문으로 데이터 넣어주세요.트렐로에 필수 INSERT문 넣었습니다"));

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

	/**
	 * 회원가입 뷰
	 *
	 * @param
	 * @return
	 * @throws @author choiseongjun
	 */
	@GetMapping("/signupView")
	public String signupView() {
		return "people/signup";
	}

	/**
	 * 로그인 뷰
	 *
	 * @param
	 * @return
	 * @throws @author choiseongjun
	 */
	@GetMapping("/signinView")
	public String signinView() {
		return "people/signin";
	}

	


	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("peopleId", null);
		return "main";
	}

}
