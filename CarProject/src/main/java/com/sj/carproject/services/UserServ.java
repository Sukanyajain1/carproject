package com.sj.carproject.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.sj.carproject.models.LoginUser;
import com.sj.carproject.models.User;
import com.sj.carproject.repositories.UserRepo;

@Service
public class UserServ {
    
    @Autowired
    private UserRepo userRepo;
    
    
// 	----------------------------------------------------------------------
// 	REGISTER PAGE
// 	----------------------------------------------------------------------
    
    // TO-DO: Write register and login methods!
    public User register(User newUser, BindingResult result) {
        if (userRepo.findByEmail(newUser.getEmail()).isPresent()) {
        	result.rejectValue("email", "Unique", "This email is already taken!");
        }
        if (!newUser.getPassword().equals(newUser.getConfirm())) {
        	result.rejectValue("confirm", "Matches", "The confirmation must match your password!");        	
        }
        if (result.hasErrors()) {        	
        	return null;
        } else {
        	String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        	newUser.setPassword(hashed);
        	return userRepo.save(newUser);
        }
    }
    
// 	----------------------------------------------------------------------
// 	LOGIN PAGE
// 	----------------------------------------------------------------------
    public User login(LoginUser newLoginObject, BindingResult result) {
    	if (result.hasErrors()) {        	
        	return null;
        }
    	Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
    	if (!potentialUser.isPresent()) {
    		result.rejectValue("email", "Unique", "Invalid email!");
    		return null;
    	}
    	
    	User user = potentialUser.get();
    	if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
    	    result.rejectValue("password", "Matches", "Invalid Password!");
    	}
    	return user;
    }
    
    
// 	----------------------------------------------------------------------
// 	FIND ONE USER BY ID
// 	----------------------------------------------------------------------
	public User findUser(Long id) {
		Optional <User> optionalUser = userRepo.findById(id);
		if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
	}
	
	
	
// 	----------------------------------------------------------------------
// 	GET ALL USERS
// 	----------------------------------------------------------------------
	public List<User> allUsers() {
		return (List<User>) userRepo.findAll();
	}
	
	
	
// 	----------------------------------------------------------------------
// 	GET USER BY EMAIL
// 	----------------------------------------------------------------------
	public User findUserByEmail(String email) {
		Optional <User> optionalUser = userRepo.findByEmail(email);
		if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
	}

	
	

}