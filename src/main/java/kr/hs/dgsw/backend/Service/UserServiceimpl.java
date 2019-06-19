package kr.hs.dgsw.backend.Service;

import kr.hs.dgsw.backend.Domain.User;
import kr.hs.dgsw.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User Login(User user) {
        Optional<User> found = this.userRepository.findByUserid(user.getUserid());
        if(found.isPresent()){
            if(found.get().getPassword().equals(user.getPassword())){
                return found.get();
            }
        }
        return null;
    }

    @Override
    public int Insert(User user) {
        Optional<User> found = this.userRepository.findByUserid(user.getUserid());
        if (found.isPresent()){
            return 0;
        }
        this.userRepository.save(user);
        return 1;
    }
}
