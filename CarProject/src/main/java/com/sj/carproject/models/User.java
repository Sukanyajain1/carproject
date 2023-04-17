package com.sj.carproject.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;





// ----------------------------------------------------------------------
// MEMBER VARIABLES
// ----------------------------------------------------------------------

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "First name is required!")
	@Size(min=2, max=25, message="First name must be between 2 and 25 characters.")
	private String firstName;

	@NotEmpty(message = "Last name is required!")
	@Size(min=2, max=25, message="Last name must be between 2 and 25 characters.")
	private String lastName;
	
	@NotEmpty(message = "Email address is required!")
	@Email(message = "Please enter a valid email address.")
	private String email;
	
	@NotEmpty(message="This field must be completed.")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    private String password;
    
    @Transient
    @NotEmpty(message="This field must be completed.")
    @Size(min=8, max=128, message="Confirmation Password must be between 8 and 128 characters")
    private String confirm;
  
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    
//methods for the created at/ updated at data types in mysql db
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	
    
// ----------------------------------------------------------------------
// RELATIONSHIP
// ----------------------------------------------------------------------
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Car> cars;

     
     
     
// ----------------------------------------------------------------------
// CONSTRUCTORS
// ----------------------------------------------------------------------
    public User() {}
    
	public User(Long id,
			@NotEmpty(message = "First name is required!") @Size(min = 2, max = 25, message = "First name must be between 2 and 25 characters.") String firstName,
			@NotEmpty(message = "Last name is required!") @Size(min = 2, max = 25, message = "Last name must be between 2 and 25 characters.") String lastName,
			@NotEmpty(message = "Email address is required!") @Email(message = "Please enter a valid email address.") String email,
			@NotEmpty(message = "This field must be completed.") @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters") String password,
			@NotEmpty(message = "This field must be completed.") @Size(min = 8, max = 128, message = "Confirm Password must be between 8 and 128 characters") String confirm,
			Date createdAt, Date updatedAt, List<Car> cars) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirm = confirm;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.cars = cars;
	}
	
	
	
// ----------------------------------------------------------------------
// GETTERS AND SETTERS
// ----------------------------------------------------------------------

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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

     
     
     
}
