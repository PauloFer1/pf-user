package com.pfernand.pfuser.core.domain;


import com.pfernand.pfuser.adapter.repository.UserJdbiDao;
import com.pfernand.pfuser.core.model.Email;
import com.pfernand.pfuser.core.model.User;
import com.pfernand.pfuser.exceptions.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final String UUID = "QWERTY-ER12TY-567896-GHTY78Y";
    private static final Email EMAIL = new Email("paulo@mail.com");
    private static final User USER = User.builder()
            .uuid(UUID)
            .firstName("Paulo")
            .lastName("Fernandes")
            .email(EMAIL.getEmail())
            .password("JHUYTFGRT4567FGUI")
            .createdAt(LocalDateTime.now().toInstant(ZoneOffset.UTC))
            .build();

    @Mock
    private UserJdbiDao userJdbiDao;

    @InjectMocks
    private UserService userService;


    @Test
    public void saveUserCallDao() {
        // Given
        // When
        userService.saveUser(USER);

        // Then
        Mockito.verify(userJdbiDao).insert(USER);
    }

    @Test
    public void getUserUuidReturnsValidUser() {
        // Given
        // When
        Mockito.when(userJdbiDao.getUserByUuid(USER.getUuid()))
                .thenReturn(Optional.of(USER));
        User user = userService.getUser(UUID);

        // Then
        assertThat(user).isEqualTo(USER);
    }

    @Test
    public void getUserUuidNotFoundThrowsException() {
        // Given
        // When
        Mockito.when(userJdbiDao.getUserByUuid(UUID))
                .thenReturn(Optional.empty());

        // Then
        assertThatExceptionOfType(UserNotFoundException.class)
                .isThrownBy(() -> userService.getUser(UUID))
                .withMessageContaining("User not found with: " + UUID);
    }

    @Test
    public void getUserEmailReturnsValidUser() {
        // Given
        // When
        Mockito.when(userJdbiDao.getUserByEmail(EMAIL.getEmail()))
                .thenReturn(Optional.of(USER));
        User user = userService.getUser(EMAIL);

        // Then
        assertThat(user).isEqualTo(USER);
    }

    @Test
    public void getUserEmailNotFoundThrowsException() {
        // Given
        // When
        Mockito.when(userJdbiDao.getUserByEmail(EMAIL.getEmail()))
                .thenReturn(Optional.empty());

        // Then
        assertThatExceptionOfType(UserNotFoundException.class)
                .isThrownBy(() -> userService.getUser(EMAIL))
                .withMessageContaining("User not found with: " + EMAIL.getEmail());
    }
}