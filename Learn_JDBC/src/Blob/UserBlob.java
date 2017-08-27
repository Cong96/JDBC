package Blob;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;

public class UserBlob implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserBlob() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public Clob getResume() {
		return resume;
	}

	public void setResume(Clob resume) {
		this.resume = resume;
	}

	private Integer id;
	private String name;
	private Blob image;
	private Clob resume;

}