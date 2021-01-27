/**
 * 
 */
package com.mishra.shorturlapi.entity;

import java.util.Date;
import javax.persistence.*;

import org.springframework.web.jsf.FacesContextUtils;

/**
 * @author vishal
 *
 */
@Entity
@Table(name = "url")
public class Url {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private String longURL;
	
	@Column(nullable = false)
	private Date CreatedDate;
	
	private Date expiresDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLongURL() {
		return longURL;
	}

	public void setLongURL(String longUrl) {
		this.longURL = longUrl;
	}

	public Date getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}

	public Date getExpiresDate() {
		return expiresDate;
	}

	public void setExpiresDate(Date expiresDate) {
		this.expiresDate = expiresDate;
	}
	
}
