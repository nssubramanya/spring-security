package com.subbu.springsecdemo.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.subbu.springsecdemo.entity.User;
import com.subbu.springsecdemo.entity.UserRepository;

@Controller
@RequestMapping("/users")
public class MyUsersController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("")
	public ModelAndView showUserList() {
		List<User> users = userRepository.findAll();
		System.out.println("User List:" + users);

		return new ModelAndView("users/list", "users", users);
	}

	@GetMapping("/create")
	public String showCreateUserPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);

		return "users/create";
	}

	@PostMapping("/save")
	public String saveUser(@ModelAttribute("user") User user) {
		if (user.getId() == null) {
			user.setEnrolledOn(LocalDate.now());
		}
		
		System.out.println("Creating User: " + user);

		User u = userRepository.save(user);
		System.out.println("Created User:" + u);

		return "redirect:/users";
	}
	

	@GetMapping("/view/{id}")
	public ModelAndView showUserDetailsPage(@PathVariable Long id) {
		User user = userRepository.findById(id).get();
		System.out.println("Showing User Details: -> " + user);
		return new ModelAndView("users/view", "user", user);
	}

	@GetMapping("/edit/{id}")
	public ModelAndView showUserEditPage(@PathVariable Long id) {		
		User user = userRepository.findById(id).get();
		System.out.println("Edit -> Got User -> " + user);
		
		ModelAndView mav = new ModelAndView("users/edit");
		mav.addObject("user", user);
		
		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		userRepository.deleteById(id);
		
		return "redirect:/users";
	}
}
