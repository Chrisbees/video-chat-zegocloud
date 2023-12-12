package com.chrisbees.video.call.user;

import com.chrisbees.video.call.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class UserService {

    private final List<User> USER_LIST = new ArrayList<>();

    public void registerUser(User user){
        user.setStatus("online");
        USER_LIST.add(user);
    }

    public User login(User user){

        Optional<User> userIndex = USER_LIST.stream()
                .filter(w -> w.getEmail().equals(user.getEmail()))
                .findAny();

        if (userIndex.isPresent()){

            var cUser = userIndex.get();
            if (!cUser.getPassword().equals(user.getPassword())) {
                throw new RuntimeException("Incorrect Password");
            }
            cUser.setStatus("online");
            return cUser;
        }else {
            return null;
        }

    }

    public void logout(String email){
        var userIndex = IntStream.range(0, USER_LIST.size())
                .filter(w -> USER_LIST.get(w).getEmail().equalsIgnoreCase(email))
                .findAny()
                .orElseThrow(() ->new RuntimeException("User not found"));

        var cUser = USER_LIST.get(userIndex);
        cUser.setStatus("offline");
    }

    public List<User> findAllUsers(){
        return USER_LIST;
    }
}
