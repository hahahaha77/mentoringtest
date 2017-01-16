package rock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rock.domain.User;
import rock.domain.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public void save(User user) {
		userRepository.save(user);
	}

	public User findByUserId(String userId) {
		return userRepository.findByUserId(userId);
	}

	public Object findAll() {
		return userRepository.findAll();
	}

	public User findOne(Long id) {
		return userRepository.findOne(id);
	}
}
