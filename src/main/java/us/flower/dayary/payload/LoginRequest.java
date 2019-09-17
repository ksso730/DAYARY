package us.flower.dayary.payload;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String id;

    @NotBlank
    private String password;

    
    public String getId() {
		return id;
	}
    public void setId(String id) {
		this.id = id;
    }
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}