package com.tcs.model;

import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "user_document", catalog = "test")
public class UserDocument {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "document_id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(name = "document_name", nullable = false, length = 100)
	private String documentName;
	
	@Column(name = "description", length = 255)
	private String description;
	
	@Column(name = "type", length = 100, nullable = false)
	private String type;
	
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(name = "content", nullable = false)
	private byte[] content;

	public UserDocument() {
		super();
	}

	public UserDocument(Integer id, User user, String documentName,
			String description, String type, byte[] content) {
		super();
		this.id = id;
		this.user = user;
		this.documentName = documentName;
		this.description = description;
		this.type = type;
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "UserDocument [id=" + id + ", user=" + user + ", documentName="
				+ documentName + ", description=" + description + ", type="
				+ type + ", content=" + Arrays.toString(content) + "]";
	}
	
	@Override
	public int hashCode() {
		
		int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((documentName == null) ? 0 : documentName.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof UserDocument))
			return false;
		UserDocument userDocument = (UserDocument) obj;
		if(id == null)
		{
			if(userDocument.id != null)
				return  false;
		}
		else if(!(id.equals(userDocument.id)))
			return false;
		
		if(documentName == null)
		{
			if(userDocument.documentName != null)
				return  false;
		}
		else if(!(documentName.equals(userDocument.documentName)))
			return false;
		
		return true;
	}
	
	
	

}
