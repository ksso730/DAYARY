package us.flower.dayary.controller.chart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import net.minidev.json.JSONObject;
import us.flower.dayary.domain.DTO.TempData;
import us.flower.dayary.service.moim.moimService;
import us.flower.dayary.service.people.PeopleInfoService;

@Controller
public class ChartController {
	
	@Autowired
	moimService moimService;
	@Autowired
	PeopleInfoService peopleInfoService;
	
	/**
	 * 계획비율완료된거중에 순위차트
	 *
	 * @param locale
	 * @param no
	 * @return moimDetail,people_no
	 * @throws Exception
	 * @author choiseongjun
	 */
	@ResponseBody
	@GetMapping("/TodoCompltLankChart/{no}")
	public JSONObject TodoCompltLankChart(@PathVariable("no") long no) {
		JSONObject returnData = new JSONObject();
		
		List<TempData> StachartList = moimService.selectTodoCompltLankChart(no);//계획리스트 그상태별 차트리스트
		returnData.put("StachartList",StachartList);
		return returnData;
	}
	/**
	 * 내 계획리스트 바로 들고오기
	 *
	 * @param locale
	 * @param no
	 * @return moimDetail,people_no
	 * @throws Exception
	 * @author choiseongjun
	 */
	@ResponseBody
	@GetMapping("/selectMyTodoProgress/{no}")
	public JSONObject MyTodoProgress(@PathVariable("no") long no) {
		JSONObject returnData = new JSONObject();
		
		List<TempData> StachartList = peopleInfoService.MyTodoProgress(no);//계획리스트 그상태별 차트리스트
		returnData.put("StachartList",StachartList);
		return returnData;
	}
	/**
	 * 나의 모임 계획 타임라인
	 *
	 * @param locale
	 * @param no
	 * @return moimDetail,people_no
	 * @throws Exception
	 * @author choiseongjun
	 */
	@ResponseBody
	@GetMapping("/TodoTimeline/{no}")
	public JSONObject TodotimeLinelist(@PathVariable("no") long no) {
		JSONObject returnData = new JSONObject();
		
		List<TempData> timeLinelist = moimService.TodotimeLinelist(no);//계획리스트 그상태별 차트리스트
		returnData.put("timeLinelist",timeLinelist);
		return returnData;
	}
}
