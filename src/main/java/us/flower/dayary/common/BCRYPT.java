package us.flower.dayary.common;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BCRYPT {

    public String hashpw(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkpw(String password,String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}