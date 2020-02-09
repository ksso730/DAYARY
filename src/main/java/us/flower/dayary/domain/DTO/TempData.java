package us.flower.dayary.domain.DTO;


import java.util.Date;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Data;

@Data
public class TempData {

	private long no1;
	private long no2;
	private char data1;
	private String name;
	private long cnt;
	private String progress_done;
	private String progress_total;
	private String title;
	private String a;
	private String b;
}

