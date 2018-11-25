package com.pfernand.pfuser.adapter.repository;

import com.pfernand.pfuser.core.model.User;
import org.jdbi.v3.core.Jdbi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserJdbiRepositoryTest {

    private static final User USER = User.builder().email("test@mail.com").uuid("uuid").build();

    @Mock
    private Jdbi jdbi;

    @InjectMocks
    private UserJdbiRepository userJdbiRepository;

    @Test
    public void insertTest() {
        // Given
        // When
        userJdbiRepository.insert(USER);

        // Then
        Mockito.verify(jdbi, Mockito.times(1))
                .useExtension(Mockito.any(), Mockito.any());

    }

    @Test
    public void getUserByEmailTest() {
        // Given
        // When
        Mockito.when(jdbi.withExtension(Mockito.any(), Mockito.any()))
                .thenReturn(USER);
        User user = userJdbiRepository.getUserByEmail(USER.getEmail());

        // Then
        assertEquals(USER, user);
    }

    @Test
    public void getUserByUuidTest() {
        // Given
        // When
        Mockito.when(jdbi.withExtension(Mockito.any(), Mockito.any()))
                .thenReturn(USER);
        User user = userJdbiRepository.getUserByUuid(USER.getUuid());

        // Then
        assertEquals(USER, user);
    }

}