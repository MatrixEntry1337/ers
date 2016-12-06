package com.ers.beans;

//remember to implement serializable
public class User {
	private int id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private Role role;
	
	public User() {
		super();
	}
	
	public User(int id){
		super();
		this.id = id;
	}
	
	public User(int id, String username, String firstName, String lastName, String email, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(int id, String role) {
		this.role = new Role();
		this.role.setId(id);
		this.role.setRole(role);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", role=" + role + "]";
	}
}
