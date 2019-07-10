package com.karma.api.controller;

import com.karma.model.User;
import com.karma.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*") // Security: origins= cho phep domain/user nao duoc access
@RequestMapping("/api")
public class APIUserController {
// @ResponseBody : cho rieng tung method
	private static  final Logger logger = LoggerFactory.getLogger(APIUserController.class);
	@Autowired
	private UserService userService;

	@GetMapping("/user") // ?id=1
	public User get(@RequestParam("id") int id) {
		return userService.get(id);

	}

	@PostMapping("/users") /// ?name=abc
	public List<User> search(@RequestParam("name") String name) {
		logger.debug("tim ten"+name);
		return userService.search(name);
	}

	@PostMapping("/user")
	public User add(@RequestBody User user) {
		userService.add(user);
		return user;
	}

	// upload file
	@PostMapping("/user-with-file")
	public User addWithFile(@ModelAttribute User user) throws IOException {
		if (user.getAvatarFile() != null && !user.getAvatarFile().isEmpty()) {
			String originalFileName = user.getAvatarFile().getOriginalFilename();
			int lastIndex = originalFileName.lastIndexOf(".");
			String extFile = originalFileName.substring(lastIndex);

			String avatarFileName = System.currentTimeMillis() + extFile;

			String folderLocation = "E:\\UPLOADFILE\\";

			try {
				FileOutputStream fileOutputStream = new FileOutputStream(folderLocation + avatarFileName);

				fileOutputStream.write(user.getAvatarFile().getBytes());
				fileOutputStream.flush();
				fileOutputStream.close();

				// save string file to db
				user.setAvatarFilename(avatarFileName);
			} catch (FileNotFoundException e) {
				System.out.println(e);
			}
		}
		userService.add(user);
		return user;
	}

	@DeleteMapping("/user") /// ?id=1
	public void delete(@RequestParam("id") int id) {
		userService.delete(id);
	}

	@PutMapping("/user")
	public void update(@RequestBody User user) {
		userService.update(user);
	}
}
