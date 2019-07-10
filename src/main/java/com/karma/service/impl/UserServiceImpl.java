package com.karma.service.impl;

import com.karma.dao.UserDao;
import com.karma.model.Address;
import com.karma.model.User;
import com.karma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service // tao bean
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public void add(User user) {

        List<Address> addresses = new ArrayList<Address>();
        Address hanoi = new Address();
        hanoi.setId(1);//gia su co address id = 1
        addresses.add(hanoi);

        user.setAddress(addresses);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode("2901"));
        userDao.save(user);
//		userDao.addUser(user.getName(), user.getUsername(), user.getPassword(), user.getRole(), user.getAge());
    }

    @Override
    public void update(User user) {
        User currentUser = userDao.get(user.getId());
        if (currentUser != null) {

            currentUser.setName(user.getName());
            currentUser.setUsername(user.getUsername());
            currentUser.setPassword(user.getPassword());
            currentUser.setRole(user.getRole());
            currentUser.setAge(user.getAge());
            if (user.getAvatarFilename() != null) {
                String filename = currentUser.getAvatarFilename();
                String folderLocation = "E:\\UPLOADFILE\\";
                File file = new File(folderLocation + filename);
                if (file.exists()) {
                    file.delete();
                }
                currentUser.setAvatarFilename(user.getAvatarFilename());
            }
            userDao.save(currentUser);
        }
    }

    @Override
    public void delete(int id) {

        userDao.deleteById(id);
    }

    @Override
    public User get(int id) {
        return userDao.get(id);
    }

    @Override
    public List<User> search(String name) {
        return userDao.search("%" + name + "%");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user");
        }
        //convert User to Security User
        //list role of login user
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        org.springframework.security.core.userdetails.User currentUser =
                new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
        return currentUser;
    }
}
