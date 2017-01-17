package rock.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rock.domain.Answer;
import rock.domain.AnswerRepository;
import rock.domain.Question;
import rock.domain.QuestionRepository;
import rock.domain.User;
import rock.service.AnswerService;

@Controller
@RequestMapping("/question/{questionId}/answers")
public class AnswerController {
	@Autowired
	private AnswerService answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@PostMapping("")
	public String create(@PathVariable Long questionId, String contents, HttpSession session) {
		Object tempUser = session.getAttribute("session_user");
		if (tempUser == null) {
			return "redirect:/users/loginform";
		}
		User loginUser = (User) session.getAttribute("session_user");
		Question question = questionRepository.findOne(questionId);
		Answer answer = new Answer(loginUser, contents, question);
		answerRepository.save(answer);
		
		return String.format("redirect:/qnas/show/%d", questionId);
	}
}
