package com.teamacronymcoders.eposmajorum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("JUnit")
class TestJUnit {

    @Test
    @DisplayName("Ensure JUnit is working properly")
    void test() {
        Assertions.assertTrue(true);
    }
}