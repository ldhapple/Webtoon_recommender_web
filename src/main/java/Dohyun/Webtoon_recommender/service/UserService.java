package Dohyun.Webtoon_recommender.service;

import Dohyun.Webtoon_recommender.model.Role;
import Dohyun.Webtoon_recommender.model.User;
import Dohyun.Webtoon_recommender.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    public List<User> findAll(){
//        return userRepository.findAll();
//    }

//    public boolean hasErrors(User user, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            return true;
//        }
//
//
//        if(userRepository.findById(user.getId()) != null){
//            bindingResult.rejectValue("userId", null, "중복된 아이디입니다.");
//            return true;
//        }
//
//        return false;
//    }

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User save(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        user.setMbti(user.getMbti());
        user.setSex(user.getSex());
        user.setAge(user.getAge());

        Role role = new Role();
        role.setId(1l);
        user.getRoles().add(role);
        return userRepository.save(user);
    }
}
