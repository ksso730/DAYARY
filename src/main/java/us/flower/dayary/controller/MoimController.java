package us.flower.dayary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.service.MoimService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MoimController {

    @Autowired
    private MoimService moimService;

    private static final Logger logger = LoggerFactory.getLogger(MoimController.class);

    /**
     * 모임 만들기
     *
     * @param locale
     * @param Moim
     * @return
     * @throws Exception
     * @author yuna
     */
    @ResponseBody
    @PostMapping("/moimMake")
    public Map<String, Object> moimMake(Moim moim, MultipartFile file, HttpSession session) {

        Map<String, Object> returnData = new HashMap<String, Object>();
        String id = (String) session.getAttribute("peopleId");
        String subject = moim.getCategory().getSubject();

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
        } else if (subject == null || subject.equals("")) {
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
     * @return
     * @throws Exception
     * @author choiseongjun
     */
    @GetMapping("/moimlistView/moimdetailView/{no}")
    public String moimDetailView(@PathVariable("no") long no, Model model) {
        System.out.println(no);
        moimService.findMoimone(no).ifPresent(moimDetail -> model.addAttribute("moimDetail", moimDetail));

        //model.addAttribute("moimDetail",moimDetail);

        return "moim/moimDetail";
    }

	/**
	 * 모임 리스트 출력
	 *
	 * @param locale
	 * @param Moim
	 * @return
	 * @throws Exception
	 * @author choiseongjun
	 */
	@GetMapping("/moimlistView")
	public String moimListView(Model model) {
		Moim moim = new Moim();
		List<Moim> moimList = moimService.findMoim(moim);
		System.out.println(moimList.toString());
		model.addAttribute("moimList", moimList);
		return "moim/moimList";
	}

    @GetMapping("/moimMakeView")
    public String moimMakeView() {
        return "moim/moimMake";
    }

}
