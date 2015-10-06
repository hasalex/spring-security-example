package fr.sewatech.formation.spring.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alexis Hassler
 */
public class PingControllerTest {

    @Before
    public void init() {
        SecurityContextHolder.getContext().setAuthentication(new TestingAuthenticationToken("TEST", "TEST"));
    }

    @Test
    public void ping_should_return_answer() throws Exception {
        // GIVEN
        PingController pingController = new PingController();

        // WHEN
        PingController.Answer answer = pingController.ping();

        // THEN
        assertThat(answer.value).isEqualTo("OK");
    }

    @Test
    public void testSimplePing() throws Exception {
        // GIVEN
        PingController pingController = new PingController();

        // WHEN
        String answer = pingController.simplePing();

        // THEN
        assertThat(answer).isEqualTo("OK");
    }
}