package keepnotespring.model;

import java.util.Date;

import jakarta.persistence.Id;

/*
 * The class "User" will be acting as the data model for the User Table in the database. 
 * Please note that this class is annotated with @Entity annotation. 
 * Hibernate will scan all package for any Java objects annotated with the @Entity annotation. 
 * If it finds any, then it will begin the process of looking through that particular 
 * Java object to recreate it as a table in your database.
 */

public class User {

	/*
	 * This class should have five fields (userId,userName,
	 * userPassword,userMobile,userAddedDate). Out of these five fields, the field
	 * userId should be the primary key. This class should also contain the getters
	 * and setters for the fields, along with the no-arg , parameterized constructor
	 * and toString method.The value of userAddedDate should not be accepted from
	 * the user but should be always initialized with the system date
	 */

	@Id
	private String userId;
	private String userName;
	private String userPassword;
	private String userMobile;
	private Date userAddedDate;

	// No-arg constructor
	public User() {
		this.userAddedDate = new Date(); // Initialize with the system date
	}

	// Parameterized constructor
	public User(String userId, String userName, String userPassword, String userMobile) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userMobile = userMobile;
		this.userAddedDate = new Date(); // Initialize with the system date
	}

	// Getters and setters
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public Date getUserAddedDate() {
		return userAddedDate;
	}

	public void setUserAddedDate(Date userAddedDate) {
		this.userAddedDate = userAddedDate;
	}

	// toString method
	@Override
	public String toString() {
		return "User{" +
				"userId='" + userId + '\'' +
				", userName='" + userName + '\'' +
				", userPassword='" + userPassword + '\'' +
				", userMobile='" + userMobile + '\'' +
				", userAddedDate=" + userAddedDate +
				'}';
	}

}
