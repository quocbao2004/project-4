package com.javaweb.controller.admin;

import com.javaweb.model.dto.UserDTO;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller(value = "homeControllerOfAdmin")
public class HomeController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("admin/home");
		return mav;
	}

	@PostMapping("/register")
	public ResponseEntity<?> RegisterUser(@ModelAttribute UserDTO userDTO, HttpServletRequest request, BindingResult result) {
		try{
			if(result.hasErrors()){
				List<String> errorMessages = result.getFieldErrors()
						.stream()
						.map(FieldError::getDefaultMessage)
						.collect(Collectors.toList());
				return ResponseEntity.badRequest().body(errorMessages);
			}
			if(userDTO.getUserName() == null || userDTO.getUserName().trim().equals("") ||
					userDTO.getPassword() == null || userDTO.getPassword().trim().equals("") ||
					userDTO.getFullName() == null || userDTO.getFullName().trim().equals("")) {
				return ResponseEntity.badRequest().body("You filled in missing information");
			}

			if(!userDTO.getPassword().equals(userDTO.getRetype_password())){
				return ResponseEntity.badRequest().body("Password not match");
			}
			UserDTO user = userService.insert(userDTO);
			return ResponseEntity.ok("");
		} catch (Exception ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

}
