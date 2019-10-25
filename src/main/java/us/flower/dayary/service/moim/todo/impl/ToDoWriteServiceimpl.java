package us.flower.dayary.service.moim.todo.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import us.flower.dayary.common.FileManager;
import us.flower.dayary.common.TokenGenerator;
import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.Common;
import us.flower.dayary.domain.CommunityBoard;
import us.flower.dayary.domain.CommunityFile;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.People;
import us.flower.dayary.domain.ToDoWrite;
import us.flower.dayary.domain.ToDoWriteList;
import us.flower.dayary.repository.community.CommunityBoardRepository;
import us.flower.dayary.repository.moim.MoimPeopleRepository;
import us.flower.dayary.repository.moim.MoimRepository;
import us.flower.dayary.repository.moim.todo.ToDoWriteListRepository;
import us.flower.dayary.repository.moim.todo.ToDoWriteRepository;
import us.flower.dayary.repository.people.PeopleRepository;
import us.flower.dayary.service.moim.todo.ToDoWriteService;
@Service
public class ToDoWriteServiceimpl implements ToDoWriteService {
   @Autowired
    private PeopleRepository peopleRepository;
   @Autowired
    private MoimRepository moimRepository;
   
   @Autowired 
   ToDoWriteRepository toDowriteRepository;
   @Autowired
   MoimPeopleRepository moimPeopleRepository;
   @Autowired 
   ToDoWriteListRepository toDowriteListRepository;
   @Autowired 
   CommunityBoardRepository communityBoardRepository;
	
	@Autowired
   private TokenGenerator tokenGenerator;
	@Autowired
	private FileManager fileManager;
   @Override
   public void saveList(ToDoWriteList list,String id,long no) {
      // TODO Auto-generated method stub
         list.getToDoWrite().setCreate_date(new java.sql.Date(System.currentTimeMillis()));
         list.getToDoWrite().setStatus("New");
         //생성자 설정
         People p=peopleRepository.findByEmail(id);
         list.getToDoWrite().setPeople(p);
         //모임 설정
         Optional<Moim> moimOne=moimRepository.findById(no);
         list.getToDoWrite().setMoim(moimOne.get());
         String[] todo=list.getPlan_list().split(",");
         String count="0/"+todo.length;
        		 list.getToDoWrite().setCount(count);
         //todowrite 저장하고 객체 반환
         ToDoWrite t =toDowriteRepository.save(list.getToDoWrite());
         //todo계획에 있는 목록들인 list를 하나씩 목록에 빼서 넣어주기
        for(String i: todo) {
           //각각 저장을 위해 객체 생성
           ToDoWriteList todolist=new ToDoWriteList(); 
           todolist.setToDoWrite(t);
           todolist.setPlan_list(i);
           todolist.setPeople(p);
           todolist.setMoim(moimOne.get());
           todolist.setCheckConfirm('N');
           todolist.setDetail('N');
           toDowriteListRepository.save(todolist);
        }
   }
   @Override
   public Page<ToDoWrite> findByMoim_id(Pageable pageable, long id) {
      // TODO Auto-generated method stub
      Page<ToDoWrite> todo=toDowriteRepository.findByMoim_id(pageable,id);
       
      
   
      return todo;
   }
    @Override
   public List<ToDoWriteList> findByToDoWrite_id(long id) {
      // TODO Auto-generated method stub
      //todo아이디 기준으로 리스트불러온다
      return toDowriteListRepository.findByToDoWrite_id(id);
    }
   @Override
   public List<ToDoWrite> findByMoim_id(long id) {
      // TODO Auto-generated method stub
      return toDowriteRepository.findByMoim_id(id);
   }
   @Override
   public ToDoWrite findById(long id) {
      // TODO Auto-generated method stub
      return toDowriteRepository.findById(id);
   }
   @Override
   public void updateList(String list,long no,int count) {
      // TODO Auto-generated method stub
      String[] id=list.split(",");
      ToDoWrite todo=toDowriteRepository.findById(no);
       int total=toDowriteListRepository.countByToDoWrite_id(no);
       int done= id.length+count;
      
       
       for(int i=0;i<id.length;i++) {
    	   if(id[i]=="") {
    		   done=-1;
    	   }
           ToDoWriteList l=new ToDoWriteList();
           Optional<ToDoWriteList> tlist=toDowriteListRepository.findById(Long.parseLong(id[i]));
           l=tlist.get();
           l.setCheckConfirm('Y');
           toDowriteListRepository.save(l);
        }
       
       String x=Integer.toString(done)+"/"+Integer.toString(total);
       todo.setCount(x);
       //상태바 
       if(total!=0) {
         todo.setProgress((double)done/(double)total*100.0);
         }
       else
          todo.setProgress(0);
       if(total==done) {
    	   todo.setStatus("End");
       }else if(done>0) {
    	   todo.setStatus("In Progress");
       }
       toDowriteRepository.save(todo);
   }
   @Override
   public boolean existByMoim_idAndPeople_id(long id, long peopleId) {
      // TODO Auto-generated method stub
      if(moimRepository.existsByIdAndPeople_id(id, peopleId))
         return true;
      else
         return moimPeopleRepository.existsByMoim_idAndPeople_id(id, peopleId);
   
   }
   @Override
   public void updateById(long id, Date date) {
      // TODO Auto-generated method stub
      List<ToDoWrite> list=toDowriteRepository.findByMoim_id(id);
      //현재시간이 todo시작 날짜보다 지나고 100% 완료되지 않은경우 미완료로 상태변경
      System.out.print("status update");
      for(int i=0;i< list.size();i++) {
         ToDoWrite todo=list.get(i);
         if(date.compareTo(todo.getTo_date())>0&&todo.getProgress()!=100) {
            todo.setStatus("Suspend");
         }
         toDowriteRepository.save(todo);
      }
   }
   @Override
   @Transactional
   public void deleteById(long id) {
      // TODO Auto-generated method stub
      toDowriteListRepository.deleteByToDoWrite_id(id);
      toDowriteRepository.deleteById(id);
   }
   @Override
   public List<ToDoWrite> findByMoim_idAndStatus(long id, String status) {
      // TODO Auto-generated method stub
      return toDowriteRepository.findByMoim_idAndStatus(id,status) ;
   }
@Override
public int[] countByMoim_idAndStatus(long id) {
	// TODO Auto-generated method stub
	int[] l=new int[4];
	//배열에 순서대로 갯수 추가 
	l[0]=toDowriteRepository.countBymoim_idAndStatus(id, "New");
	l[1]=toDowriteRepository.countBymoim_idAndStatus(id, "In Progress");
	l[2]=toDowriteRepository.countBymoim_idAndStatus(id, "End");
	l[3]=toDowriteRepository.countBymoim_idAndStatus(id, "Suspend");
	return l;
}
@Override
public void writeBoard(MultipartFile file,CommunityBoard board,long no,String id) {
	// TODO Auto-generated method stub
	//정보 기준으로 작성자와 todowritelist 설정 
	  People people = peopleRepository.findByEmail(id);
	  board.setPeople(people);
	  Optional<ToDoWriteList> todo=toDowriteListRepository.findById(no);
	  board.setToDoWriteList(todo.get());
	
	BoardGroup boardGroup=new BoardGroup();
	boardGroup.setId(8);
	board.setBoardGroup(boardGroup);
	
	board.setDeleteFlag('N');
	System.out.print(board);
	communityBoardRepository.save(board);  
	
	todo.get().setDetail('Y');
	toDowriteListRepository.save(todo.get());
	
 
    
}
@Override
public void changeToDate(ToDoWrite todo) {
	// TODO Auto-generated method stub
	System.out.print(todo);
	Date changeDate=todo.getTo_date2();
	todo=toDowriteRepository.findById(todo.getId());
	todo.setTo_date2(changeDate);
	toDowriteRepository.save(todo);
}
@Override
public CommunityBoard findByToDoWriteList_id(long id) {
	// TODO Auto-generated method stub
	return communityBoardRepository.findByToDoWriteList_id(id);
}}