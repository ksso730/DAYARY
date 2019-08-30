package us.flower.dayary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import us.flower.dayary.common.BCRYPT;
import us.flower.dayary.domain.People;
import us.flower.dayary.repository.PeopleRepository;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PeopleController {

	private PeopleRepository peopleRepository;
	private BCRYPT bcrypt;

	public PeopleController(PeopleRepository peopleRepository, BCRYPT bcrypt){
		this.peopleRepository=peopleRepository;
		this.bcrypt=bcrypt;
	}

	@GetMapping("/signupView")
	public String signupView() {
		return "people/signup";
	}
	@GetMapping("/signinView")
	public String signinView() {
		return "people/signin";
	}

	@ResponseBody
	@PostMapping("/signup")
	public Map<String,Object> signup(@RequestBody People people){
		Map<String,Object> returnData = new HashMap<String,Object>();

		try{
			if(!peopleRepository.existsById(people.getId())){
                people.setPassword(bcrypt.hashpw(people.getPassword()));//비밀번호bcrypt해쉬
				peopleRepository.save(people);
				returnData.put("code","1");
				returnData.put("message","가입완료:)");
			}else{
				returnData.put("code","2");
				returnData.put("message","이미 가입 된 아이디입니다:(");
			}
		}catch (Exception e){
			returnData.put("code","E3290");
			returnData.put("message","잠시 후, 다시 시도해주세요:(");
		}
		return returnData;
	}

    @ResponseBody
    @PostMapping("/signin")
    public Map<String,Object> signin(@RequestBody People people, HttpSession session){
        Map<String,Object> returnData = new HashMap<String,Object>();
        try{
			if(peopleRepository.existsById(people.getId())){
				People dbPeople = peopleRepository.findById(people.getId());
				if(bcrypt.checkpw(people.getPassword(),dbPeople.getPassword())){//비밀번호가맞다면
					session.setAttribute("peopleNo",dbPeople.getNo());//NO세션저장
					returnData.put("code","1");
				}else{//비밀번호가다르면
					returnData.put("code","0");
					returnData.put("message","아이디 또는 비밀번호를 확인해주세요:(");
				}
			}else{//없는아이디이면
				returnData.put("code","0");
				returnData.put("message","아이디 또는 비밀번호를 확인해주세요:(");
			}
        }catch (Exception e){
        	e.printStackTrace();
            returnData.put("code","E4024");
            returnData.put("message","잠시 후, 다시 시도해주세요:(");
        }
        return returnData;
    }

}
