package edu.jsp.btm.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/saveUser")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("created");
		User responseUser = userRepository.save(user);
		responseStructure.setData(responseUser);

		return new ResponseEntity(responseStructure, HttpStatus.CREATED);

	}

	@GetMapping("/findById/{userId}")
	public ResponseEntity findUserById(@PathVariable int userId) {
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
			ResponseStructure<User> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Found");
			responseStructure.setData(optional.get());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
		}
		throw new UserNotFoundException("Userwith the given Id" + userId + "Not Found");
		// return null;
	}

	@GetMapping("/findAll")
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@PostMapping("/login")
	public User userLogin(@RequestParam String userEmail, @RequestParam String userPassword) {
		return userRepository.findUserByUserEmailAndUserPassword(userEmail, userPassword);
	}

	@GetMapping("/findUserByEmail")
	public User findByEmail(@RequestParam String userEmail) {
		return userRepository.findUserByuserEmail(userEmail);
	}

	@DeleteMapping("/deleteById")
	public String deleteUserById(@RequestParam int userId) {
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
			userRepository.deleteById(userId);
			return "user with the give Id= " + userId + "Is Deleted";
		}
		return "User Not Found";
	}

	@PatchMapping("/updateById")
	public User updateUserById(int userId, String password) {
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
			User user = optional.get();
			user.setUserPassword("abcd1234");
			return userRepository.save(user);
		}
		return null;
	}

}
