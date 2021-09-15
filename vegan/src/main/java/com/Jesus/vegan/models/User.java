package com.Jesus.vegan.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min=2, max=50)
	private String firstName;
	
	@NotBlank
	@Size(min=2, max=50)
	private String lastName;
	
	@NotBlank
	@Size(max=100)
	@Email(message="Please enter a valid email address")
	private String email;
	
	@NotBlank
	@Size(min=8, message="Password must be at least 8 Characters")
	private String password;
	
	@Transient
	private String passwordConfirmation;
	
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="foodie")
    private List<Food> foodRecipes;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name="likes",
    		joinColumns = @JoinColumn(name="user_id"),
    		inverseJoinColumns=@JoinColumn(name="food_id")
    		)
    private List<Food> likedFoods;
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
    	this.setUpdatedAt(new Date());
    }
    
	public User() {
		
	}
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
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
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
	public List<Food> getFoodRecipes() {
		return foodRecipes;
	}
	public void setFoodRecipes(List<Food> foodRecipes) {
		this.foodRecipes = foodRecipes;
	}
	public List<Food> getLikedFoods() {
		return likedFoods;
	}
	public void setLikedFoods(List<Food> likedFoods) {
		this.likedFoods = likedFoods;
	}
}
