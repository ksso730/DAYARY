package us.flower.dayary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.service.MoimService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MoimController {
	
	private final MoimService moimService;
	
	 /**
	 * 모임 만들기 
	 * @param locale
	 * @param Moim
	 * @return
	 * @throws Exception
	 * @author yuna
	 */
	@ResponseBody
	@PostMapping("/moimMake")
	public Map<String,Object> moimMake(@RequestBody Moim moim) {
		
		System.out.println(moim.toString());
		Map<String,Object> returnData = new HashMap<String,Object>();
		
		try {
			//저장 전 validation 필요
			//카테고리 선택, 인원수 제한 등
			
			if(moimService.existsByNo(moim.getNo())) {
				//저장완료
				moimService.saveMoim(moim);
				returnData.put("code","1");
				returnData.put("message","저장되었습니다");
			}else {
				//저장실패
				returnData.put("code","2");
				returnData.put("message","저장 실패");
			}
			
		}catch (Exception e) {
			returnData.put("code","E3290");
			returnData.put("message","데이터 확인 후 다시 시도해주세요.");
		}
		return null;
	}
	
	
	@GetMapping("/moimlistView")
	public String moimListView() {
		return "moim/moimList";
	}
	@GetMapping("/moimdetailView")
	public String moimDetailView() {
		return "moim/moimDetail";
	}
	@GetMapping("/moimMakeView")
	public String moimMakeView() {
		return "moim/moimMake";
	}
	
	
}
