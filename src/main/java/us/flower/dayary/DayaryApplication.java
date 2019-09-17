package us.flower.dayary;

import java.io.File;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DayaryApplication {

	
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	
    @Value("${moimImagePath}")
    private String moimImagePath;

    public static void main(String[] args) {
        SpringApplication.run(DayaryApplication.class, args);
    }

    //모임이미지디랙토리생성
    @PostConstruct
    public void makeDirectory() {
        File moimImagePath = new File(this.moimImagePath);
        if(!moimImagePath.exists()){
            moimImagePath.mkdirs();
        }
    }

}
