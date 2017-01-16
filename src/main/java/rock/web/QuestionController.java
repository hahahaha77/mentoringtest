package rock.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rock.domain.Answer;
import rock.domain.AnswerRepository;
import rock.domain.Question;
import rock.domain.QuestionRepository;
import rock.domain.User;
import rock.service.AnswerService;
import rock.service.QuestionService;

@Controller
@RequestMapping("/qnas")
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerRepository;
	
	@PostMapping("")
	public String create(String title, String contents, HttpSession session) {
		
		User sessionUser = (User) session.getAttribute("session_user");
		Question newQuestion = new Question(sessionUser, title, contents);
		System.out.println(newQuestion);
		questionService.save(newQuestion);
		
		return "redirect:/qnas";
	}
	
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("qna", questionService.findAll());
		return "index";
	}
	
	@GetMapping("/show/{id}")
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("qna", questionService.findOne(id));
		
		return "question/show";
	}
	
	@GetMapping("/form")
	public String form(HttpSession session) {
		Object tempUser = session.getAttribute("session_user");
		if (tempUser == null) {
			return "redirect:/users/loginform";
		}
		return "question/form";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, HttpSession session) {
		Object tempUser = session.getAttribute("session_user");
		User sessiondUser = (User)tempUser;
		Question qna = questionService.findOne(id);
		Long writerId = qna.getWriter().getId();
		System.out.println("cccccccc "+writerId);
		
		//if (!writerId.equals(sessiondUser.getId()) || !qna.getAnswer().isEmpty()) {
		if (!writerId.equals(sessiondUser.getId())) {
			return "redirect:/qnas";
		}
		if(!qna.getAnswer().isEmpty()) {
			for(int i=0 ; i <= qna.getAnswer().size() ; i++) {
				Long answerId = qna.getAnswer().get(i).getWriter().getId();
				System.out.println("aaaaaaaaa "+writerId);
				System.out.println("bbbbbbb "+answerId);
				if(writerId != answerId){
					return "redirect:/qnas";
				}
			}
		}
		
		questionService.delete(id);

		return "redirect:/qnas";
	}
}
