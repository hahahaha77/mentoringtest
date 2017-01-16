package rock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rock.domain.Answer;
import rock.domain.AnswerRepository;

@Service
public class AnswerService {
	@Autowired
	private AnswerRepository answerRepository;

	public void save(Answer answer) {
		answerRepository.save(answer);
	}

}
