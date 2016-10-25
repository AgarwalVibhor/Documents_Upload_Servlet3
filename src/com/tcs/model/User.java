package com.tcs.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "app_user", catalog = "test")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "sso_id", nullable = false, unique = true, length = 30)
	private String ssoId;
	
	@Column(name = "first_name", nullable = false, length = 30)
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 30)
	private String lastName;
	
	@Column(name = "email", nullable = false, length = 30)
	private String email;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
	@Fetch(FetchMode.SELECT)
	private Set<UserDocument> userDocuments = new HashSet<UserDocument>();

	public User() {
		super();
	}

	public User(Integer id, String ssoId, String firstName, String lastName,
			String email, Set<UserDocument> userDocuments) {
		super();
		this.id = id;
		this.ssoId = ssoId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userDocuments = userDocuments;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", ssoId=" + ssoId + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email
				+ ", userDocuments=" + userDocuments + "]";
	}
	
	@Override
	public int hashCode() {
		
		int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		/*
		 * Here in the equals() method, both the conditions are checked :
		 * (1) The two references pointing to the same memory location and if not,
		 * (2) Checking the contents of the two objects
		 */
		
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof User))
			return false;
		User user = (User) obj;
		if(id == null)
		{
			if(user.id != null)
				return false;
		}
		else if(!(id.equals(user.id)))
				return false;
		
		if(ssoId == null)
		{
			if(user.ssoId != null)
				return false;
		}
		else if(!(ssoId.equals(user.ssoId)))
				return false;
		
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
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

	public Set<UserDocument> getUserDocuments() {
		return userDocuments;
	}

	public void setUserDocuments(Set<UserDocument> userDocuments) {
		this.userDocuments = userDocuments;
	}
	
	
	
	
	
	

}
