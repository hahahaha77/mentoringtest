package rock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rock.domain.Question;
import rock.domain.QuestionRepository;

@Service
public class QuestionService {
	@Autowired
	private QuestionRepository questionRepository;

	public void save(Question newQuestion) {
		questionRepository.save(newQuestion);
	}

	public List<Question> findAll() {
		return questionRepository.findAll();
	}

	public Question findOne(Long id) {
		return questionRepository.findOne(id);
	}

	public void delete(Long id) {
		questionRepository.delete(id);
	}

}
