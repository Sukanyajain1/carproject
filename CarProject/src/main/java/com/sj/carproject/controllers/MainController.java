package com.sj.carproject.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sj.carproject.models.Car;
import com.sj.carproject.models.LoginUser;
import com.sj.carproject.models.User;
import com.sj.carproject.services.CarServ;
import com.sj.carproject.services.UserServ;

@Controller
public class MainController {
	private final UserServ userServ;
	private final CarServ carServ;
	
	public MainController(UserServ userServ, CarServ carServ) {
		this.userServ = userServ;
		this.carServ = carServ;
	}
	
	
//	============================================================
//	Render Login/Register Route
//	============================================================	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}

	
//	============================================================
//	Process Registration Route
//	============================================================
	@PostMapping("/register")
	public String register (@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
		userServ.register(newUser, result);
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		//user id put into session
		session.setAttribute("user_id", newUser.getId());
		return "redirect:/dashboard";
	}
			
	
//	============================================================
//	Process Login Route
//	============================================================
	@PostMapping("/login")
	public String login (@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
		User user = userServ.login(newLogin, result);
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}
		//user id put into session
		session.setAttribute("user_id", user.getId());
		return "redirect:/dashboard";
	}
	
	
//	============================================================
//	Logout Route
//	============================================================
	@GetMapping("/logout")
	public String logout (HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
//	============================================================
//	Render Dash board Route
//	============================================================	
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session, RedirectAttributes flash) {
		Long userId = (Long) session.getAttribute("user_id");
		if(userId == null) {
			flash.addFlashAttribute("logAlert", "Please login or register before entering the site!!");
			return "redirect:/";
		}
		User user = userServ.findUser(userId);
		model.addAttribute("loggedUser", user);
		
		List<Car> cars = carServ.getAllCars();
		model.addAttribute("cars", cars);
		return "dashboard.jsp";
	}
	
	
//	============================================================
//	Create Routes
//	============================================================

	@GetMapping("/new")
	public String newCar(HttpSession session, RedirectAttributes flash, Model model) {
		Long userId = (Long) session.getAttribute("user_id");
		if(userId == null) {
			flash.addFlashAttribute("logAlert", "Please login or register before entering the site!!");
			return "redirect:/";
		}
		
		model.addAttribute("userId", userId);
		model.addAttribute("car", new Car());		
		
		return "createCar.jsp";
	}
	
	@PostMapping("/create")
	public String createCar (@Valid @ModelAttribute("car") Car car, HttpSession session, BindingResult result, Model model) {
		if (result.hasErrors()) {
			Long userId = (Long) session.getAttribute("user_id");
			model.addAttribute("userId", userId);
			return "createCar.jsp";
		}else {
			carServ.saveCar(car);
			return "redirect:/dashboard";
		}
	}
	

//  ============================================================
//  Show One Routes
//  ============================================================

	@GetMapping("/show/{id}")
	public String showCar (@PathVariable("id") Long carId, Model model, HttpSession session, RedirectAttributes flash) {
		Long userId = (Long) session.getAttribute("user_id");
		if(userId == null) {
			flash.addFlashAttribute("logAlert", "Please login or register before entering the site!!");
			return "redirect:/";
		}
		
		User user = userServ.findUser(userId);
		Car car = carServ.findOneCar(carId);
		model.addAttribute("loggedUser", user);
		model.addAttribute("car", car);
		
		return "oneCar.jsp";
	}
	
	
//  ============================================================
//  Edit One Routes
//  ============================================================

	@GetMapping("/edit/{id}")
	public String editCar (@PathVariable("id") Long carId, Model model, HttpSession session, RedirectAttributes flash) {
		Long userId = (Long) session.getAttribute("user_id");
		if(userId == null) {
			flash.addFlashAttribute("logAlert", "Please login or register before entering the site!!");
			return "redirect:/";
		}
		
		User user = userServ.findUser(userId);
		Car car = carServ.findOneCar(carId);
		model.addAttribute("loggedUser", user);
		if(car.getUser().getId() == userId) {
			model.addAttribute("car", car);
			return "editCar.jsp";
		}
		flash.addFlashAttribute("driverAlert", "You must be the driver to edit this car's information!");
		return "redirect:/dashboard";
		
	}
	
	@PutMapping("/update/{id}")
	public String updateCar (@Valid @ModelAttribute("car") Car car, BindingResult result) {
		if(result.hasErrors()) {
			return "editCar.jsp"; 
		} else {
			carServ.saveCar(car);
			return "redirect:/dashboard";
		}
	}

	

//  ============================================================
//  Delete One Routes
//  ============================================================
	
	@GetMapping("/delete/{id}")
	public String deleteCar(@PathVariable("id") Long carId, Model model, HttpSession session, RedirectAttributes flash) {
		Long userId = (Long) session.getAttribute("user_id");
		if(userId == null) {
			flash.addFlashAttribute("logAlert", "Please login or register before entering the site!!");
			return "redirect:/";
		}
		
		User user = userServ.findUser(userId);
		Car car = carServ.findOneCar(carId);
		model.addAttribute("loggedUser", user);
		if(car.getUser().getId() == userId) {
			carServ.deleteCarById(carId);
			return "redirect:/dashboard";
		}
		flash.addFlashAttribute("driverAlert", "You must be the driver to delete this car's information!");
		return "redirect:/dashboard";
	}
}
	

	
	
	
	
	
	
	
	
	
