package rock.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rock.domain.User;
import rock.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("")
	public String create(User user) {
		System.out.println("user : " + user);
		userService.save(user);
		return "redirect:/users";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password ,HttpSession session) {
		User user = userService.findByUserId(userId);
		if(user == null) {
			return "user/login";
		}
		if (!password.equals(user.getPassword())) {
			return "user/login";
		}
		System.out.println("Login Success!");
		session.setAttribute("session_user", user);
		
		return "redirect:/";
	}
	
	@GetMapping("/loginform")
	public String login(Model model) {
		return "user/login";
	}
	
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userService.findAll());
		return "user/list";
	}

	@GetMapping("/form")
	public String form() {
		return "user/form";
	}
	
	@GetMapping("/{id}/updateForm")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		Object tempUser = session.getAttribute("session_user");
		if (tempUser == null) {
			return "redirect:/users/loginform";
		}
		
		User sessiondUser = (User)tempUser;
		if (!id.equals(sessiondUser.getId())) {
			throw new IllegalStateException("You can`t update the anther user.");
		}
		
		User user = userService.findOne(id);
		model.addAttribute("user", user);
		return "user/updateForm";
	}
	
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, User updatedUser, HttpSession session) {
		Object tempUser = session.getAttribute("session_user");
		if (tempUser == null) {
			return "redirect:/users/loginform";
		}
		
		User sessiondUser = (User)tempUser;
		if (!id.equals(sessiondUser.getId())) {
			throw new IllegalStateException("You can`t update the anther user.");
		}
		
		User user = userService.findOne(id);
		user.update(updatedUser);
		userService.save(user);
		
		return "redirect:/users";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("session_user");
		return "redirect:/";
	}
}
