package us.flower.dayary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.File;

@SpringBootApplication
public class DayaryApplication {

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
