package test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.repository.CrudRepository;

import rock.domain.User;
import rock.domain.UserRepository;
import rock.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	@Mock private UserRepository userRepository;
	
	@InjectMocks private UserService userService;
	
	@Test
	public void test() {
		User newUser = new User(1L,"shjegal","Test0000","제갈세형","shjegal@rockplace.co.kr");
		when(userRepository.findOne(1L)).thenReturn(newUser);
		User user = userService.findOne(1L);
		assertEquals(newUser, user);
	}
	@Test
	public void create() throws Exception {
		User user = new User(1L,"shjegal","Test0000","제갈세형","shjegal@rockplace.co.kr");
		userService.save(user);
		verify(userRepository).save(user);
	}

}
