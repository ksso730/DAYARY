package us.flower.dayary.controller.moim.chat;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimChat;
import us.flower.dayary.domain.People;
import us.flower.dayary.domain.DTO.Message;
import us.flower.dayary.repository.chat.MoimChatRepository;
import us.flower.dayary.repository.moim.MoimRepository;
import us.flower.dayary.service.moim.moimService;

@Controller
public class MoimChatController {

	@Autowired
	MoimRepository moimRepository;
	@Autowired
	moimService moimService;
	@Autowired
	MoimChatRepository moimchatRepository;
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	/**
     * 모임 단체채팅방 채팅 날리기
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun 
     * @Date 2019-10-02
     */
	@MessageMapping("/moimchat")
	@SendTo("/topic/message")
	public Message ttt(Message message) throws Exception{
		
		System.out.println("ID?="+message.getPeopleId());
		System.out.println("MSG=" + message.getMsg());
		System.out.println("MoimNo=" + message.getMoimNo());
		System.out.println("peopleEmail"+message.getPeopleEmail());
		People people=new People();
		people.setId(message.getPeopleId());
		
		Moim moim=new Moim();
		moim.setId(message.getMoimNo());
		
		MoimChat moimchat=new MoimChat();
		moimchat.setPeople(people);
		moimchat.setChatMemo(message.getMsg());
		moimchat.setMoim(moim);
		moimchat.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
		messagingTemplate.convertAndSend("/topic/" + message.getPeopleId(), message.getMsg());
		moimchatRepository.save(moimchat);
		return message;
	} 
	 /**
     * 모임 단체채팅방 조회
     *
     * @param 
     * @return
     * @throws 
     * @author choiseongjun 
     */
    @GetMapping("/moimDetail/moimChatroom/{no}")
    public String moimChatroom(@PathVariable("no") long no,Model model,HttpSession session) {
    	long peopleId = (long) session.getAttribute("peopleId");//일반회원 번호를 던져준다
    	String email = (String) session.getAttribute("peopleEmail");
    	Optional<Moim> moimOne=moimRepository.findById(no);
        List<People> moimpeopleList=moimOne.get().getPeopleList();
        
        moimService.findMoimone(no).ifPresent(moimDetail -> model.addAttribute("moimDetail", moimDetail));//모임장중심으로 데이터 불러옴
        List<MoimChat> moimchatList=moimchatRepository.findByMoim_id(no);//특정모임의 채팅리스트를 들고온다( ex)모임1번의 채팅리스트)
       
        model.addAttribute("moimchatList",moimchatList);
        model.addAttribute("moimpeopleList",moimpeopleList);
        model.addAttribute("moimOne",moimOne); 
        model.addAttribute("no",no);
        model.addAttribute("peopleId",peopleId);
        model.addAttribute("email",email);
    	return "moim/moimChatroom";
    }
}
