package com.karma.controller;

import com.karma.model.User;
import com.karma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {

    //	@Qualifier("userService")
    @Autowired // ap dung cho method va variablies
    private UserService userService;

    @GetMapping("/change-language")
    public String changLanguage() {
        return "redirect:/admin/user/list";
    }


    @GetMapping("/user/add")
    public String addUser() {
        return "admin/user-add";
    }

    @PostMapping("/user/add")
    public String addUser(HttpServletRequest req, @RequestParam("name") String name,
                          @RequestParam("username") String username, @RequestParam("password") String password,
                          @RequestParam("role") String role, @RequestParam("avatarFile") MultipartFile avatarFile)
            throws IOException {

        String age = req.getParameter("age");
        User u = new User();
//		u.setId(Integer.parseInt(req.getParameter("id")));
        u.setName(name);
        u.setUsername(username);
        u.setAge(Integer.parseInt(age));
        u.setPassword(password);
        u.setRole(role);
        // Save file to hard-disk
        // Get url , save to DB
        if (avatarFile != null && !avatarFile.isEmpty()) {
            String originalFileName = avatarFile.getOriginalFilename();
            int lastIndex = originalFileName.lastIndexOf(".");
            String extFile = originalFileName.substring(lastIndex);

            String avatarFileName = System.currentTimeMillis() + extFile;

            String folderLocation = "E:\\UPLOADFILE\\";

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(folderLocation + avatarFileName);

                fileOutputStream.write(avatarFile.getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();

                // save string file to db
                u.setAvatarFilename(avatarFileName);
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        }

        userService.add(u);

        return "redirect:/admin/user/list";
    }

    @GetMapping("/user/list")
    public String userList(Model model) {

        List<User> users = userService.search("");
        model.addAttribute("userList", users);
        return "admin/user-list";
    }

    @GetMapping("/user/view") /// ?id=123
    public String viewUserDetail(Model model, @RequestParam(value = "id") int id) {
        User user = userService.get(id);
        model.addAttribute("user", user);
        return "admin/view-user";
    }

//	@GetMapping("/user/search")
//	public String searchUser(Model model) {
//		List<User> users = userService.search("");
//
//		model.addAttribute("userList", users);
//
//		return "admin/search-user";
//	}

    @PostMapping("/user/search")
    public String searchUser(Model model, @RequestParam(value = "keyword") String name) {

        List<User> users = userService.search(name);
        model.addAttribute("userList", users);
        return "admin/search-user";
    }

    /// user/delete/123
    @GetMapping("/user/delete/{id}")
    public String deleteUser(Model model, @PathVariable(value = "id") int id) {

        userService.delete(id);
        return "redirect:/admin/user/list";
    }

    /// user/edit/123
    @GetMapping("/user/update/{id}")
    public String editUser(Model model, @PathVariable("id") int id) {

        User user = userService.get(id);
        model.addAttribute("user", user);

        return "admin/user-update";
    }

    @PostMapping("/user/update")
    public String editUser(Model model, @ModelAttribute User user) throws IOException {

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
        userService.update(user);

        model.addAttribute("user", user);
        return "redirect:/admin/user/list";
    }

    @GetMapping("/user/image")
    public void download(HttpServletResponse resp, @RequestParam("filename") String fileName) {
        String folderLocation = "E:\\UPLOADFILE\\";
        try {
            File file = new File(folderLocation + fileName);

            // put image to Résp
            Files.copy(file.toPath(), resp.getOutputStream());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
