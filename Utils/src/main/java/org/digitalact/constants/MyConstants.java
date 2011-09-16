package org.digitalact.constants;

/**
 * Klasa zawierająca stałe wykorzystywane w aplikacji.
 * @author Marcin Pieciukiewicz
 */
public final class MyConstants {

    private MyConstants() {
        //empty
    }

    public static final String APPLICATION_URL = "http://localhost:8080/digitalact/";
    
    public static final String MESSAGES_VAR_NAME = "msg";
    public static final String DOMAIN_EXCEPTION_PREFIX = "domainException_";
    public static final String SYSTEM_EMAIL_SENDER_ADDRESS = "m.pieciukiewicz@gmail.com";

    /**
     * Ograniczenia zakładane na encje.
     */
    public static final class Constrains {
        public static final int MAX_PASSWORD_LENGTH = 64;
        public static final int MAX_FULL_CLASS_NAME = 128;

    }

    /**
     * Stałe odnoszące się do zadań potwierdzanych emailem.
     */
    public static final class MailTask {
        public static final int CODE_LENGTH = 64;
        public static final int SERIALIZED_TASK_SIZE = 10240;
        public static final String TASK_CODE_PARAMETER_NAME = "k";
        public static final String INCORRECT_LINK = "incorrect-task-link.html";
        public static final String EXECUTION_URL = APPLICATION_URL+"confirm?"+TASK_CODE_PARAMETER_NAME+"=";
        public static final String DOMAIN_EXCEPTION_LINK = "mailtask/domain-exception.html";
        public static final String TASK_ALREADY_EXECUTED_LINK = "mailtask/task-already-executed.html";

    }
    
}
