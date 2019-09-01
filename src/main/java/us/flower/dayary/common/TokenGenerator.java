package us.flower.dayary.common;

import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

    public String getToken() {
        String token = "";

        String tokenArray[] = {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "O", "P", "R", "S", "T", "U", "X",
                "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7",
                "8", "9"
        };
        for (int i = 0; i < 20; i++) {
            int idx = (int) (Math.random() * tokenArray.length);
            token += (tokenArray[idx]);
        }
        return token;
    }
}
