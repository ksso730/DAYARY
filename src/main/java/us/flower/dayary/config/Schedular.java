package us.flower.dayary.config;


import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import us.flower.dayary.service.moim.todo.ToDoWriteService;
@Component
public class Schedular {
	@Autowired 
	private ToDoWriteService todoWriteservice;
	
	@Scheduled(cron="0 0 0 * * *")
	public void simplePrintln(){//계획을 다들고와서 자정떄 한번에 suspend로 업데이트..
	 	Date date=new java.sql.Date(System.currentTimeMillis());
	 	todoWriteservice.updateById(date);
	} 
}
