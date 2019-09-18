package us.flower.dayary.payload;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SignUpRequest {
  

	@NotBlank
	private String email;
	@NotBlank
	private String password;

	private String name;

	private String photo;

	private String activation;
    
}