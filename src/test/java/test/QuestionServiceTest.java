package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rock.domain.Question;
import rock.domain.QuestionRepository;
import rock.domain.User;
import rock.domain.UserRepository;
import rock.service.QuestionService;
import rock.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {

	@Mock private QuestionRepository questionRepository;
	
	@InjectMocks private QuestionService questionService;
	
	@Test
	public void test() {
		Question newQuestion = new Question(1L,2,2,"title","contents");
		when(questionRepository.findOne(1L)).thenReturn(newQuestion);
		User user = questionService.findOne(1L);
		assertEquals(newUser, user);
	}
	
	@Test
	public void create() throws Exception {
		User user = new User(1L,"shjegal","Test0000","제갈세형","shjegal@rockplace.co.kr");
		userService.save(user);
		verify(userRepository).save(user);
	}

}
