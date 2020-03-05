package com.subbu.springsecdemo.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=1, max=64)
	private String username;
	
	@NotNull
	@Size(min=1, max=64)
	private String password;
	
	@NotNull
	private LocalDate enrolledOn;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	} 
	
	public void setId(Long id) {
		this.id = id;
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

	public LocalDate getEnrolledOn() {
		return enrolledOn;
	}

	public void setEnrolledOn(LocalDate enrolledOn) {
		this.enrolledOn = enrolledOn;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enrolledOn=" + enrolledOn
				+ "]";
	}	
}