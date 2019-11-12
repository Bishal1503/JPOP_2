package com.JPoP2.entity;

import com.JPoP2.entity.enums.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@ApiModel(description = "All the details of the user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated user id", position = 1, example = "1")
    private Long id;

    @ApiModelProperty(notes = "First name of the user", position = 2, example = "Justin")
    @NotNull
    private String firstName;

    @ApiModelProperty(notes = "Last name of the user", position = 3, example = "Jhonson")
    private String lastName;

    @ApiModelProperty(notes = "Email-id of the user", position = 4, example = "justin.jhonson@outlook.com")
    @NotNull
    @Column(unique = true)
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
