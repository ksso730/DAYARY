package us.flower.dayary;

import java.io.File;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import us.flower.dayary.oauth2.UserArgumentResolver;

@SpringBootApplication
public class DayaryApplication {

	
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
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
