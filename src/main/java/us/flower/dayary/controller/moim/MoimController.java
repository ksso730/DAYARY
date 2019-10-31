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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import us.flower.dayary.domain.Common;
import us.flower.dayary.domain.Meetup;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimPeople;
import us.flower.dayary.domain.People;
import us.flower.dayary.repository.chat.MoimChatRepository;
import us.flower.dayary.repository.moim.MoimPeopleRepository;
import us.flower.dayary.repository.moim.MoimRepository;
import us.flower.dayary.repository.moim.meetup.MoimMeetUpRepository;
import us.flower.dayary.repository.moim.picture.MoimBoardFileRepository;
import us.flower.dayary.repository.moim.todo.ToDoWriteRepository;
import us.flower.dayary.service.moim.moimService;



@Controller
public class MoimController {

    @Autowired
    private moimService moimService;

    @Autowired
    MoimPeopleRepository moimpeopleRepository;
	@Autowired
	MoimRepository moimRepository;
	
	@Autowired
	ToDoWriteRepository toDowriteRepository;
	@Autowired
	MoimMeetUpRepository moimmeetupRepository;
    @Autowired
    MoimChatRepository moimchatRepository;
    @Autowired
    MoimBoardFileRepository moimboardfileRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(MoimController.class);
//    @GetMapping("/searchTitle")
//	public String listUsers(Model model, @RequestParam(defaultValue="")  String name) {
//		
//    	System.out.println(name);
//    	model.addAttribute("moimsearchList", moimService.findByTitle(name));
//		return "moim/moimList";
//	}
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
    public Map<String, Object> moimMake(@RequestPart("moim") Moim moim, @RequestPart(name="file",required=false) MultipartFile file, HttpSession session) {
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
        } else if (moim.getSidocode() == null || moim.getSidocode().equals("") || moim.getSigooncode() == null || moim.getSigooncode().equals("")) {
            returnData.put("code", "0");
            returnData.put("message", "활동 지역을 선택해주세요");
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
    public String moimDetailView(@PathVariable("no") long no, Model model,
    							HttpSession session,Sort sort,@PageableDefault Pageable pageable) {
      
    	
    	
        moimService.findMoimone(no).ifPresent(moimDetail -> model.addAttribute("moimDetail", moimDetail));//모임장중심으로 데이터 불러옴
        long peopleId = (long) session.getAttribute("peopleId");//일반회원 번호를 던져준다.참가를 위해 
        session.setAttribute("peopleId", peopleId);
 
        
	   	int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
	    pageable = PageRequest.of(page, 2,Sort.Direction.DESC,"id");//내림차순으로 정렬한다 
	    
        String moimPeopleNo=moimService.findMoimPeopleNoOne(peopleId,no);//참여자단건 조회(모임피플넘버를 단건으로 가져와서 moimPeople_no에 넣어준다)
        List<MoimPeople> joinedpeoplelist=moimpeopleRepository.findByMoim_idAndPeople_id(no,peopleId);//현재 접속한 유저 리스트를 들고옴
        
        
        List<MoimPeople> joinedmoimpeopleList=moimpeopleRepository.findByMoim_id(no);//이건 모임내 전체사람조회
        for(int i=0;i<joinedpeoplelist.size();i++) {
        	long joinedpeople=joinedpeoplelist.get(i).getId();
        	   model.addAttribute("joinedpeople",joinedpeople);
        }
        Optional<Moim> moimOne=moimRepository.findById(no);
        List<People> moimpeopleList=moimOne.get().getPeopleList();
        
        sort = sort.and(new Sort(Sort.Direction.DESC, "id"));
        List<Meetup> meetupList=moimmeetupRepository.findByMoim_id(no,pageable);//오프라인 모임 내림차순정렬로 가져옴
        
        long chatcount=moimchatRepository.countByMoim_id(no);
        
        
        long picturecount = moimboardfileRepository.picturecount(no);
        
        
        long totalPeople = 0;
        for(int i=0;i<=moimpeopleList.size();i++) {//데이터 값 들고온것을 size만큼 반복해서 뽑기 모임리스트까지 <=한 이유는 모임장이 제외됬기때문에 +1해야한다
        	totalPeople++;
        }  
        model.addAttribute("moimPeopleNo", moimPeopleNo);
        model.addAttribute("no",no);
        model.addAttribute("moimOne",moimOne);
        model.addAttribute("moimpeopleList",moimpeopleList);
        model.addAttribute("todoCount",toDowriteRepository.countByMoim_id(no));
        model.addAttribute("totalPeople",totalPeople);//해당하는 모임의 총회원수 뽑기
        model.addAttribute("joinedpeoplelist",joinedpeoplelist);//현재 접속한 유저(모임피플) 정보.
        model.addAttribute("joinedmoimpeopleList",joinedmoimpeopleList);//모임가입된사람전체조회
        model.addAttribute("meetupList",meetupList);
        model.addAttribute("chatcount",chatcount);
        model.addAttribute("picturecount",picturecount);
        
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
	public String moimListView(@PageableDefault Pageable pageable,HttpSession session
			,Model model,@RequestParam(required = false) String title
			,@RequestParam(required = false) String category
			,@RequestParam(required = false) String sido_code
			,@RequestParam(required = false) String sigoon_code
			) {

		System.out.println(sido_code);
		 int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
	        pageable = PageRequest.of(page, 9,Sort.Direction.DESC,"id");//내림차순으로 정렬한다 
	        
	        
	        Common common=new Common();
	        common.setCommCode(category);//검색조건 해올때 필요하다 by choiseongjun 2019-10-06
	    
	    if(title!=null||category!=null||sido_code!=null||sigoon_code!=null) {
	    	Page<Moim> moimList= moimService.selecttitleList(pageable,title,common,sido_code,sigoon_code);//타이틀을 검색한 모임리스트 출력한다	
	    	model.addAttribute("moimList",moimList);
	    	long moimListcount=moimList.getTotalElements();//각각 카운트를 센다 
	    	model.addAttribute("moimListcount",moimListcount);
	    }else{
	    	Page<Moim> moimList= moimService.selectListAll(pageable);//모든 모임리스트 출력한다	
	    	model.addAttribute("moimList",moimList);	    	
	    	long moimListcount=moimList.getTotalElements();
	    	model.addAttribute("moimListcount",moimListcount);
	    }
	    
	
		
		return "moim/moimList"; 
	}
	
	
	@ResponseBody
    @GetMapping("/getMoimImage/{imageName:.+}")
    public byte[] getMoimImage(@PathVariable("imageName") String imageName) throws Exception {
        return moimService.getMoimImage(imageName);
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
		 
		 model.addAttribute("no",no);
		 
	    return "moim/moimUpdate";
	}
	/**
     * 모임 수정하기
     *
     * @param locale
     * @param Moim
     * @return
     * @throws 
     * @author yuna
     */
    @ResponseBody
    @PutMapping("/moimUpdate")
    public Map<String, Object> moimUpdate(@RequestPart("moim") Moim moim, @RequestPart(name="file",required=false) MultipartFile file, HttpSession session) {
    	Map<String, Object> returnData = new HashMap<String, Object>();
        
        String id = (String) session.getAttribute("peopleEmail");
        //String subject = moim.getCategory().getCommName();
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
        }
         
        try {
        	moimService.updateMoim(id, moim, file);       	
            returnData.put("code", "1");
            returnData.put("message", "수정되었습니다");

        } catch (Exception e) {
            returnData.put("code", "E3290");
            returnData.put("message", "데이터 확인 후 다시 시도해주세요.");
        }
        return returnData;
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
