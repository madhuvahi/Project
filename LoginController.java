package com.airline.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.airline.model.Flight;
import com.airline.model.Login;
import com.airline.model.User;
import com.airline.service.UserService;


@Controller
public class LoginController {

	 @Autowired
	  UserService userService;
	  @RequestMapping(value = "/login", method = RequestMethod.GET)
	  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("login");
	    mav.addObject("login", new Login());
	    return mav;
	  }
	  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
	  @ModelAttribute("login") Login login) {
	    ModelAndView mav = null;
	    
	    User user = userService.validateUser(login);
	    
	    if (null != user) {
	    mav = new ModelAndView("welcome");
	    mav.addObject("name", user.getName());
	    } else {
	    mav = new ModelAndView("login");
	    mav.addObject("message", "Username or Password is wrong!!");
	    }
	    return mav;
	  }
	  @RequestMapping(value = "/search", method = RequestMethod.GET)
	  public ModelAndView searchFlight(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("search");
	    
	    return mav;
	  }
	  @RequestMapping(value = "/searchProcess", method = RequestMethod.POST)
	 
	  public String viewflight(Model m,@ModelAttribute("search")Flight flight) {
		  List<Flight> flightlist=userService.findFlight(flight);
		  m.addAttribute("flightlist",flightlist);
		  return "view";
	  }
	  
	  @RequestMapping(value="/book/{flightid}")
	  public String reserve(@PathVariable int flightid,Model m) {
		  Flight flight=userService.getDetails(flightid);
		  m.addAttribute("command", flight);
		  return "index";
		  
	  }
	  
		  
	  }
		  
			  
			
			  
		  
		  
	  



