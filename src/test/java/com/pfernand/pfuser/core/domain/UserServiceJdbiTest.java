package com.pfernand.pfuser.core.domain;


import com.pfernand.pfuser.core.model.Email;
import com.pfernand.pfuser.core.model.User;
import org.jdbi.v3.core.Jdbi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

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
    private Jdbi jdbi;

    @InjectMocks
    private UserServiceJdbi userService;


    @Test
    public void saveUserCallDao() {
        // Given
        // When
        userService.saveUser(USER);

        // Then
        Mockito.verify(jdbi).useExtension(Mockito.any(), Mockito.any());
    }

    @Test
    public void getUserUuidReturnsValidUser() {
        // Given
        // When
        Mockito.when(jdbi.withExtension(Mockito.any(), Mockito.any())).thenReturn(USER);
        User user = userService.getUser(UUID);

        // Then
        assertThat(user).isEqualTo(USER);
    }

    @Test
    public void getUserUuidNotFoundReturnsOptionalEmpty() {
        // Given
        // When
        Mockito.when(jdbi.withExtension(Mockito.any(), Mockito.any())).thenReturn(null);
        User user = userService.getUser(UUID);

        // Then
        assertThat(user).isNull();
    }

    @Test
    public void getUserEmailReturnsValidUser() {
        // Given
        // When
        Mockito.when(jdbi.withExtension(Mockito.any(), Mockito.any())).thenReturn(USER);
        User user = userService.getUser(EMAIL);

        // Then
        assertThat(user).isEqualTo(USER);
    }

    @Test
    public void getUserEmailNotFoundReturnsOptionalEmpty() {
        // Given
        // When
        Mockito.when(jdbi.withExtension(Mockito.any(), Mockito.any())).thenReturn(null);
        User user = userService.getUser(EMAIL);

        // Then
        assertThat(user).isNull();
    }
}