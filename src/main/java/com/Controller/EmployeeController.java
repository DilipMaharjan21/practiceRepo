package com.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.service.EmployeeService;
import com.dto.EmployeeDTO;

@Controller
public class EmployeeController {
	@Autowired 
	EmployeeService employeeService;
	
	@GetMapping("/showAll")
	
	public String showAllEmployees(Model model){
		List<EmployeeDTO> empList=employeeService.showAllEmp();
		
		model.addAttribute("empList", empList);
		
		
		return "showAllEmployees";
		
		
	}
	
	
@GetMapping("/showRegistrationPage")
	
	public String showRegistration(){		
		
		
		return "employeeRegistration";
		
		
	}

@PostMapping("/registration")
public String registration(@ModelAttribute EmployeeDTO employeeDto) {
	employeeService.saveEmployee(employeeDto);
	return "redirect:/showAll";
}


@GetMapping("/")//in url u need to write it to call login.jsp
String showLoginPage(){
	return "login";
	
}


@PostMapping("/authenticate")	
String authenticateEmp(@RequestParam String emailId,@RequestParam String password,Model model){
	
	
		
		EmployeeDTO employeeDto=employeeService.authenticate(emailId,password);
		if (employeeDto!=null){					
			model.addAttribute("employee", employeeDto);
			return "showEmployee";
		}
		else{
			model.addAttribute("msg", "Wrong credential..");
			return "login";
		}
		
	}




	
	

}
