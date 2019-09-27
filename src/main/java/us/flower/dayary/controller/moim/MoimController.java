package us.flower.dayary.controller.moim;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimPeople;
import us.flower.dayary.domain.People;
import us.flower.dayary.repository.moim.MoimPeopleRepository;
import us.flower.dayary.repository.moim.MoimRepository;
import us.flower.dayary.service.moim.moimService;



@Controller
public class MoimController {

    @Autowired
    private moimService moimService;

    @Autowired
    MoimPeopleRepository moimpeopleRepository;
	@Autowired
	MoimRepository moimRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(MoimController.class);
 
    /**
     * 모임 삭제
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun
     * @date 2019-09-20
     */
	@ResponseBody
	@DeleteMapping("/moimDetail/moimDeleteOne/{moimNo}")
	public Map<String, Object> MoimDeleteOne(@PathVariable("moimNo") long moimNo,Model model,Sort sort) {
	
		Map<String, Object> returnData = new HashMap<String, Object>();
		
		try {
			moimService.deleteMoimOne(moimNo);

		  	returnData.put("code", "1");
            returnData.put("message", "삭제되었습니다");

        } catch (Exception e) {
            returnData.put("code", "E3290");
            returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
        }
 

		return returnData;
	}
    
    
   
  
 
    /**
     * 모임 카테고리 목록 조회
     *
     * @param locale
     * @param Moim
     * @return
     * @throws 
     * @author yuna
     */
    @ResponseBody
    @PostMapping("/getMoimCategory")
    public Map<String, Object> getMoimCategory(){
    	
    	Map<String, Object> categoryList = new HashMap<String, Object>();
    	try {
    		categoryList =  moimService.getMoimCategory();
    		System.out.println(categoryList);
    	}catch (Exception e) {
		}
    	return categoryList;
    }

    /**
     * 모임 만들기
     *
     * @param locale
     * @param Moim
     * @return
     * @throws 
     * @author yuna
     */
    @ResponseBody
    @PostMapping("/moimMake")
    public Map<String, Object> moimMake(@RequestPart("moim") Moim moim, @RequestPart("file") MultipartFile file, HttpSession session) {

        Map<String, Object> returnData = new HashMap<String, Object>();
        String id = (String) session.getAttribute("peopleEmail");
        String subject = moim.getCategory().getCommName();
        if (id.equals(null) || id.equals("")) {
            returnData.put("code", "0");
            returnData.put("message", "로그인 후 이용해주세요");
            return returnData;
        } else if (moim.getTitle().equals(null) || moim.getTitle().equals("")) {
            returnData.put("code", "0");
            returnData.put("message", "모임 이름을 입력해주세요");
            return returnData;
        } else if (moim.getPeopleLimit() == 0) {
            returnData.put("code", "0");
            returnData.put("message", "인원수를 입력해주세요");
            return returnData;
        } else if (moim.getCategory() == null || moim.getCategory().equals("")) {
            returnData.put("code", "0");
            returnData.put("message", "모임 주제를 선택해주세요");
            return returnData;
        }
        
        try {
            moimService.saveMoim(id, subject, moim, file);
            returnData.put("code", "1");
            returnData.put("message", "저장되었습니다");

        } catch (Exception e) {
            returnData.put("code", "E3290");
            returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
        }
        return returnData;
    }

    /**
     * 모임 디테일 출력
     *
     * @param locale
     * @param no
     * @return moimDetail,people_no
     * @throws Exception
     * @author choiseongjun
     */
    @GetMapping("/moimlistView/moimdetailView/{no}")
    public String moimDetailView(@PathVariable("no") long no, Model model,HttpSession session) {
      
    	
    	
        moimService.findMoimone(no).ifPresent(moimDetail -> model.addAttribute("moimDetail", moimDetail));//모임장중심으로 데이터 불러옴
        long peopleId = (long) session.getAttribute("peopleId");//일반회원 번호를 던져준다.참가를 위해 
        session.setAttribute("peopleId", peopleId);
 
        String joinedpeople=moimService.findPeopleOne(peopleId);//참여자 조회
        String moimPeopleNo=moimService.findMoimPeopleNoOne(peopleId);//참여자단건 조회(모임피플넘버를 단건으로 가져와서 moimPeople_no에 넣어준다)
       
        
        
        
        Optional<Moim> moimOne=moimRepository.findById(no);
        List<People> moimpeopleList=moimOne.get().getPeopleList();

        long totalPeople = 0;
        for(int i=0;i<=moimpeopleList.size();i++) {//데이터 값 들고온것을 size만큼 반복해서 뽑기 모임리스트까지 <=한 이유는 모임장이 제외됬기때문에 +1해야한다
        	totalPeople++;
        }

        model.addAttribute("no",no);
        model.addAttribute("moimOne",moimOne);
        model.addAttribute("moimPeopleNo",moimPeopleNo);
        model.addAttribute("moimpeopleList",moimpeopleList);
        model.addAttribute("joinedpeople",joinedpeople);
        model.addAttribute("totalPeople",totalPeople);//해당하는 모임의 총회원수 뽑기
        System.out.println("로그찍기");
        	System.out.println(joinedpeople);
        return "moim/moimDetail";  
    }

    /**
	 * 모임 리스트 출력(Paging 처리)
	 *
	 * @param locale
	 * @param Moim
	 * @return moimList
	 * @throws Exception
	 * @author choiseongjun
	 */
	@GetMapping("/moimlistView") 
	public String moimListView(@PageableDefault Pageable pageable,HttpSession session,Model model) {

		 int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
	        pageable = PageRequest.of(page, 9);
	        
	    Page<Moim> moimList= moimService.selectListAll(pageable);//모임리스트 출력한다
		model.addAttribute("moimList",moimList);
		
		return "moim/moimList";
	}
	
	
	@ResponseBody
    @GetMapping("/getMoimImage/{imageName:.+}")
    public byte[] getMoimImage(@PathVariable("imageName") String imageName) throws Exception {
        return moimService.getMoimImage(imageName);
    }
	 /**
	 * 모임 오프라인모임 만들기 화면으로
	 *
	 * @param 
	 * @return 
	 * @throws Exception
	 * @author choiseongjun
	 */
	@GetMapping("/moimoffMakeView")
	public String moimoffMakeView() {
	    return "moim/popup/moimoffMake";
	}
	/**
	 * 모임 수정 화면으로
	 *
	 * @param 
	 * @return 
	 * @throws Exception
	 * @author choiseongjun
	 */
	@GetMapping("/moimUpdateView/{no}")
	public String moimUpdateView(@PathVariable("no") long no,Model model) {
		
		 moimService.findMoimone(no).ifPresent(moimDetail -> model.addAttribute("moimDetail", moimDetail));
		 
		 
	    return "moim/moimUpdate";
	}
	 /**
		 * 모임 만들기 화면으로
		 *
		 * @param 
		 * @return 
		 * @throws Exception
		 * @author choiseongjun
		 */
    @GetMapping("/moimMakeView")
    public String moimMakeView() {
        return "moim/moimMake";
    }

}
