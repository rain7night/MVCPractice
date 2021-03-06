package login.spring_security.controller;

import login.spring_security.model.User;
import login.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/getAllUser")
	public String getAllUser(HttpServletRequest request){
		request.setAttribute("userList", userService.listAllUser());
		return "/springMvcTest";
	}
	
	@RequestMapping("/getUser")
	public String getUser(String id,HttpServletRequest request){
		Long userId = Long.parseLong(id);
		request.setAttribute("user", userService.getUser(userId));
		return "/editUser";
	}
	
	@RequestMapping("/addUser")
	public String addUser(User user,HttpServletRequest request){
		userService.addUser(user);
		return "redirect:/user/getAllUser";
	}
	
	@RequestMapping("/delUser")
	public void delUser(String id,HttpServletResponse response){
		 String result = "{\"result\":\"success\"}";   
		Long userId = Long.parseLong(id);
		userService.delUser(userId);
		response.setContentType("application/json");
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
