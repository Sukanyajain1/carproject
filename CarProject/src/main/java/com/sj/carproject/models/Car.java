package com.sj.carproject.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

//=============================================================================
//MEMBER VARIABLES
//=============================================================================

@Entity
@Table(name="cars")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Car color is required!")
	@Size(min=3, max=25, message="Color of the car must be between 3 and 25 characters.")
	private String color;
	
	@NotNull
	@Max(value=15, message="Number of seats cannot exceed 15!")
	private Integer numOfSeats;
	
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
	
 // =============================================================================
 // RELATIONSHIP
 // =============================================================================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

 // =============================================================================
 // CONSTRUCTORS
 // =============================================================================
    public Car() {}
    
	public Car(Long id,
			@NotEmpty(message = "Car color is required!") @Size(min = 3, max = 25, message = "Color of the car must be between 3 and 25 characters.") String color,
			@NotNull @Max(value = 15, message = "Number of seats cannot exceed 15!") Integer numOfSeats,
			Date createdAt,
			Date updatedAt,
			User user) {

		this.id = id;
		this.color = color;
		this.numOfSeats = numOfSeats;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
	}
	
	
	
// =============================================================================
// GETTERS AND SETTERS
// =============================================================================
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getNumOfSeats() {
		return numOfSeats;
	}
	public void setNumOfSeats(Integer numOfSeats) {
		this.numOfSeats = numOfSeats;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
    
    
    
    
}
