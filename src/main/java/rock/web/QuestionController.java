package rock.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rock.domain.Question;
import rock.domain.QuestionRepository;

@Controller
@RequestMapping("/qnas")
public class QuestionController {
	@Autowired
	private QuestionRepository questionRepository;
	
	@PostMapping("")
	public String create(Question qna) {
		System.out.println("qna : " + qna);
		questionRepository.save(qna);
		return "redirect:/qnas";
	}
	
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("qna", questionRepository.findAll());
		return "index";
	}
	
	@GetMapping("/show")
	public String show(Model model) {
		model.addAttribute("qna", questionRepository.findAll());
		return "question/show";
	}
	
	@GetMapping("/form")
	public String form() {
		return "question/form";
	}
	
}
