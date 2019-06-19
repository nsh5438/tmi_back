package kr.hs.dgsw.backend.Service;

import kr.hs.dgsw.backend.Domain.User;

public interface UserService {
    User Login(User user);
    int Insert(User user);
}
