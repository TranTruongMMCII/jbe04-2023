package com.r2s.demo.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.demo.DTO.UserRequestDTO;
import com.r2s.demo.DTO.UserResponseDTO;
import com.r2s.demo.entity.User;
import com.r2s.demo.repository.UserRepository;
import com.r2s.demo.service.UserService;

@RestController
@RequestMapping(path = "users")
public class UserController {
	@Autowired
	@Qualifier(value = "UserServiceImpl")
	private UserService userService;

	@Autowired
	UserRepository userRepository;

	/**
	 * 
	 * @param type 0 = all | 1 = deleted | 2 = not deleted
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") int type) {
		return this.userService.getAll(type);
	}

	/**
	 * This method helps to get user by specific id
	 * 
	 * @param id id user need to be found
	 * @return null in case not found
	 * @author Truong
	 * @since 26/07/2023
	 */
//	@GetMapping(path = "/userId/{id}/orderId/{orderId}")
//	public User getUserById(@PathVariable(name = "id", required = false) long id) {
//		System.err.println("getUserById");
//		User foundUser = null;
//
//		for (User user : users) {
//			if (user.getId() == id) {
//				foundUser = user;
//			}
//		}
//
//		return foundUser;
//	}
//
	@GetMapping("/")
	public ResponseEntity<?> getUserByIdRequestParam(@RequestParam(name = "id", defaultValue = "0") long id) {
		User foundUser = this.userService.getById(id);
		return new ResponseEntity<>(new UserResponseDTO(foundUser), HttpStatus.OK);
	}

//
//	@GetMapping("/findByName")
//	public ResponseEntity<?> findByName(@RequestParam(name = "name", defaultValue = "") String name) {
//		String pattern = "%" + name + "%";
//		List<User> users = this.userRepository.findByName(pattern);
//		if (!users.isEmpty()) {
//			return new ResponseEntity<>(users, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//
	@DeleteMapping("")
	public ResponseEntity<?> deleteUserById(@RequestBody Map<String, Long> body) {
		Long id = Long.parseLong(body.get("id").toString());
		Optional<User> foundUser = this.userRepository.findById(id);
		if (foundUser.isPresent()) {
			this.userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> createUser(@RequestBody UserRequestDTO user) {
		return this.userService.save(user);
	}

//	@PutMapping("/{id}")
//	public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody User user) {
//		Optional<User> foundUser = this.userRepository.findById(id);
//		if (foundUser.isPresent()) {
//			User currentUser = foundUser.get();
//			currentUser.setAge(user.getAge());
//			currentUser.setName(user.getName());
//			this.userRepository.save(currentUser);
//			return new ResponseEntity<>(currentUser, HttpStatus.ACCEPTED);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
}
