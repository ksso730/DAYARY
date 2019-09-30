package us.flower.dayary.service.moim.todo.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import us.flower.dayary.domain.Common;
import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.People;
import us.flower.dayary.domain.ToDoWrite;
import us.flower.dayary.domain.ToDoWriteList;
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
	ToDoWriteListRepository toDowriteListRepository;
	@Override
	public void saveList(ToDoWriteList list,String id) {
		// TODO Auto-generated method stub
			list.getToDoWrite().setCreate_date(new java.sql.Date(System.currentTimeMillis()));
			//생성자 설정
			People p=peopleRepository.findByEmail(id);
			list.getToDoWrite().setPeople(p);
			//모임 설정
			Optional<Moim> moimOne=moimRepository.findById(list.getMoim().getId());
			list.getToDoWrite().setMoim(moimOne.get());
		   //todowrite 저장하고 객체 반환
			 ToDoWrite t =toDowriteRepository.save(list.getToDoWrite());
		   String[] todo=list.getPlan_list().split(",");
		   //todo계획에 있는 목록들인 list를 하나씩 목록에 빼서 넣어주기
		  for(String i: todo) {
			  //각각 저장을 위해 객체 생성
			  ToDoWriteList todolist=new ToDoWriteList(); 
			  todolist.setToDoWrite(t);
			  todolist.setPlan_list(i);
			  todolist.setPeople(p);
			  todolist.setMoim(moimOne.get());
			  todolist.setCheckConfirm('N');
			  toDowriteListRepository.save(todolist);
		  }
	}
	@Override
	public Page<ToDoWrite> findByMoim_id(Pageable pageable, long id) {
		// TODO Auto-generated method stub
		Page<ToDoWrite> todo=toDowriteRepository.findByMoim_id(pageable,id);
		 for(int i=0;i<todo.getContent().size();i++) {
			 //모임 아이디 가져와서 해당todo의 총갯수 ,체크된갯수 반환
			 String x=Integer.toString(toDowriteListRepository.countByCheckConfirmAndToDoWrite_id('Y',todo.getContent().get(i).getId()));
			  x+="/"+Integer.toString(toDowriteListRepository.countByToDoWrite_id(todo.getContent().get(i).getId()));
			 todo.getContent().get(i).setCount(x);
		 }
	
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
	
}
