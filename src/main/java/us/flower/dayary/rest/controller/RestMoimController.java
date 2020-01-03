package us.flower.dayary.rest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;
import us.flower.dayary.domain.Meetup;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.repository.moim.MoimRepository;
import us.flower.dayary.repository.moim.meetup.MoimMeetUpRepository;
import us.flower.dayary.service.moim.moimService;

@RestController
public class RestMoimController {

	@Autowired
	moimService moimService;
	@Autowired
	MoimRepository moimrepository;
	@Autowired
	MoimMeetUpRepository moimmeetupRepository;
	/**

	 * 모임 리스트 출력(Paging 처리)
	 *
	 * @param locale
	 * @param Moim
	 * @return moimList
	 * @throws Exception
	 * @author choiseongjun
	 */
	@GetMapping("/rest/moimlistView")
	public ResponseEntity<?> moimListView(@PageableDefault Pageable pageable, HttpSession session
			) {

		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
		pageable = PageRequest.of(page, 9, Sort.Direction.DESC, "id");// 내림차순으로 정렬한다

//		Common common = new Common();
//		common.setCommCode(category);// 검색조건 해올때 필요하다 by choiseongjun 2019-10-06
//
//		if (title != null || category != null || sido_code != null || sigoon_code != null) {
//			Page<Moim> moimList = moimService.selecttitleList(pageable, title, common, sido_code, sigoon_code);// 타이틀을
//																												// 검색한
//																												// 모임리스트
//																												// 출력한다
//			//model.addAttribute("moimList", moimList);
//			long moimListcount = moimList.getTotalElements();// 각각 카운트를 센다
//			//model.addAttribute("moimListcount", moimListcount);
//			return new ResponseEntity<>(moimList, HttpStatus.OK);
//		} else {
//			Page<Moim> moimList = moimService.selectListAll(pageable);// 모든 모임리스트 출력한다
//			//model.addAttribute("moimList", moimList);
//			long moimListcount = moimList.getTotalElements();
//			//model.addAttribute("moimListcount", moimListcount);
//			return new ResponseEntity<>(moimList, HttpStatus.OK);
//		}
		Page<Moim> moimList = moimrepository.findAll(pageable);
		
		JSONObject returnData = new JSONObject();
		returnData.put("moimList", moimList);
		
		 return new ResponseEntity<>(returnData, HttpStatus.OK);

		
	}
	@GetMapping("/rest/moimlistView/moimdetailView/{no}")
	public ResponseEntity<?> RestmoimDetailView(@PathVariable("no") long no, Model model, HttpSession session, Sort sort,
			@PageableDefault Pageable pageable) {
		
		
		JSONObject returnData = new JSONObject();
		moimService.findMoimone(no).ifPresent(moimDetail -> returnData.put("moimDetail", moimDetail));// 모임장중심으로 데이터
		
		List<Meetup> meetupList = moimmeetupRepository.findByMoim_id(no, pageable);//오프라인모임리스트
		returnData.put("meetupList",meetupList);
		// 불러옴
		 return new ResponseEntity<>(returnData, HttpStatus.OK);
	}
}