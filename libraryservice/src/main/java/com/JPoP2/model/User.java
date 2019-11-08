package com.JPoP2.model;

import com.JPoP2.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@ApiModel(description = "All details about the user")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	@ApiModelProperty(notes = "The database generated user id", position = 1, example = "1")
	private Long id;

	@ApiModelProperty(notes = "First name of the user", position = 2, example = "John")
	@NotNull
	private String firstName;

	@ApiModelProperty(notes = "Last name of the user", position = 3, example = "Smith")
	private String lastName;

	@ApiModelProperty(notes = "Email-id of the user", position = 4, example = "john.smith@outlook.com")
	@NotNull
	private String emailId;

	@ApiModelProperty(notes = "Role of the user", position = 5, example = "ADMIN")
	@NotNull
	private Role role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
