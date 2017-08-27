package entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Date birthday;
	private float salary;

	public User() {
	}

	public User(Integer id, String name, Date birthday, float salary) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.salary = salary;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String toString() {
		return "[id=" + id + ",name=" + name + ",birthday=" + birthday
				+ ",salary=" + salary + "]";
	}
}
