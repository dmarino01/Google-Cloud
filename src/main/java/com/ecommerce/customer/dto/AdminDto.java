package com.ecommerce.customer.dto;



import javax.validation.constraints.Size;


public class AdminDto {
    @Size(min = 3, max = 10, message = "Invalid first name!(3-10 characters)")
    private String firstName;
    @Size(min = 3, max = 10, message = "Invalid first name!(3-10 characters)")
    private String lastName;

    private String username;
    @Size(min = 5, max = 15, message = "Invalid password !(5-15 characters)")
    private String password;

    private String repeatPassword;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public AdminDto(@Size(min = 3, max = 10, message = "Invalid first name!(3-10 characters)") String firstName,
			@Size(min = 3, max = 10, message = "Invalid first name!(3-10 characters)") String lastName, String username,
			@Size(min = 5, max = 15, message = "Invalid password !(5-15 characters)") String password,
			String repeatPassword) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.repeatPassword = repeatPassword;
	}

	public AdminDto() {
	
	}
    
    
    
}
