package dev.yataroon.hyakka.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.validation.Validator;

@ExtendWith(MockitoExtension.class)
public class MessageValidatorTest {

    @Mock
    private Validator validator;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testValidate() {

    }
}
