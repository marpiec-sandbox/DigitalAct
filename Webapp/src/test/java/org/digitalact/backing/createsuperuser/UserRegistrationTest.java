package org.digitalact.backing.createsuperuser;

import org.digitalact.backing.userregistration.UserRegistrationBacking;
import org.digitalact.backing.userregistration.UserRegistrationForm;
import org.digitalact.domain.users.entity.Person;
import org.digitalact.domain.users.query.UserQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.testng.Assert.*;

/**
 * Test weryfikujący możliwość rejestracji superużytkownika.
 */
@ContextConfiguration(locations={"classpath:webapp-spring.xml", "classpath:spring-datasource-test.xml", "classpath:spring-test-beans.xml"})
public class UserRegistrationTest extends AbstractTransactionalTestNGSpringContextTests {

    @Inject
    private UserRegistrationBacking userRegistrationBacking;

    @Inject
    private UserRegistrationForm userRegistrationForm;

    @Inject
    private UserQuery userQuery;

    /**
     * Inicjalizacja przed testem.
     */
    @BeforeMethod
    public void setUp() {
    }

    /**
     * Sprzątanie po teście.
     */
    @AfterMethod
    public void tearDown() {
    }


    private void fillFormWithCorrectData() {
        userRegistrationForm.setEmail("zdfgsiue@sdfgsf.com");
        userRegistrationForm.setName("Marcin Pieciukiewicz");
        userRegistrationForm.setPassword("aaa333");
        userRegistrationForm.setPasswordRetype("aaa333");
    }



        /**
     * Testuje poprawność zakładania konta superużytkownika.
     */
    @Test
    public void testCorrectUserRegistration() {
        //having
        fillFormWithCorrectData();

        //when
        userRegistrationBacking.registerUser();

        //then
        Person person = userQuery.findByEmail("zdfgsiue@sdfgsf.com");
        assertNotNull(person);
    }

    /**
     * Testuje poprawność zakładania konta superużytkownika.
     */
    @Test
    public void testIncorrectUserRegistration() {
        //having
        fillFormWithCorrectData();
        userRegistrationForm.setPasswordRetype("aaa33");

        //when
        userRegistrationBacking.registerUser();

        //then
        Person person = userQuery.findByEmail("zdfgsiue@sdfgsf.com");
        assertNull(person);
    }




}
