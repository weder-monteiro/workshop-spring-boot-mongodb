package br.com.redew.workshopmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.redew.workshopmongodb.domain.User;
import br.com.redew.workshopmongodb.dto.UserDTO;
import br.com.redew.workshopmongodb.repository.UserRepository;
import br.com.redew.workshopmongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User user) {
		return repo.insert(user);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User user) {
		User userUpdate = findById(user.getId());
		updateData(userUpdate, user);
		return repo.save(userUpdate);
	}

	private void updateData(User userUpdate, User user) {
		userUpdate.setName(user.getName());
		userUpdate.setEmail(user.getEmail());
	}

	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}
}
