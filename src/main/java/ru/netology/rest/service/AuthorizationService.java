package ru.netology.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.rest.exception.InvalidCredentials;
import ru.netology.rest.exception.UnauthorizedUser;
import ru.netology.rest.model.Authorities;
import ru.netology.rest.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorizationService {

    private final UserRepository userRepository;

    public List<Authorities> getAuthorities(String user, String password) {
        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("USER NAME OR PASSWORD IS EMPTY");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("UNKNOWN USER " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}