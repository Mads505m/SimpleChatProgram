package com.example.simplechatprogramfinal.Usecase;

import com.example.simplechatprogramfinal.DBController.user.UserRepository;
import com.example.simplechatprogramfinal.Entity.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
class UserUtilTest {
    @Mock
    private UserRepository userRepository;
    private Users user;
    private UserUtil userUtil;

    @BeforeEach
    void setUp() {
        user = new Users(1, "Jens", "Jens123", "Jens@gmail.com");
    }

    @Test
    void getCurrentEmail() {
        userUtil.getCurrentUser();
        verify(userUtil).getCurrentUser();
    }
}