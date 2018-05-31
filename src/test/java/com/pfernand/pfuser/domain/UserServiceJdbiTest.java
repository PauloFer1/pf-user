package com.pfernand.pfuser.domain;


import com.pfernand.pfuser.model.Email;
import com.pfernand.pfuser.model.User;
import com.pfernand.pfuser.repository.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceJdbiTest {

    private static final String UUID = "QWERTY-ER12TY-567896-GHTY78Y";
    private static final Email EMAIL = new Email("paulo@mail.com");
    private static final User USER = User.builder()
            .uuid(UUID)
            .firstName("Paulo")
            .lastName("Fernandes")
            .email(EMAIL.getEmail())
            .password("JHUYTFGRT4567FGUI")
            .createdAt(LocalDateTime.now())
            .build();

    @Mock
    private UserDao userDao;

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceJdbi(userDao);
    }

    @Test
    public void saveUserCallDao() {
        // Given
        // When
        userService.saveUser(USER);

        // Then
        Mockito.verify(userDao).insert(USER);
    }

    @Test
    public void getUserUuidReturnsValidUser() {
        // Given
        // When
        Mockito.when(userDao.getUserByUuid(UUID)).thenReturn(USER);
        Optional<User> userOptional = userService.getUser(UUID);

        // Then
        assertThat(userOptional.get()).isEqualTo(USER);
    }

    @Test
    public void getUserUuidNotFoundReturnsOptionalEmpty() {
        // Given
        // When
        Mockito.when(userDao.getUserByUuid(UUID)).thenReturn(null);
        Optional<User> userOptional = userService.getUser(UUID);

        // Then
        assertThat(userOptional.isPresent()).isFalse();
    }

    @Test
    public void getUserEmailReturnsValidUser() {
        // Given
        // When
        Mockito.when(userDao.getUserByEmail(EMAIL.getEmail())).thenReturn(USER);
        Optional<User> userOptional = userService.getUser(EMAIL);

        // Then
        assertThat(userOptional.get()).isEqualTo(USER);
    }

    @Test
    public void getUserEmailNotFoundReturnsOptionalEmpty() {
        // Given
        // When
        Mockito.when(userDao.getUserByEmail(EMAIL.getEmail())).thenReturn(null);
        Optional<User> userOptional = userService.getUser(EMAIL);

        // Then
        assertThat(userOptional.isPresent()).isFalse();
    }
}