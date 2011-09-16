/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.digitalact.emailtask;

import org.digitalact.emailtask.exception.IncorrectTaskCodeException;
import org.digitalact.emailtask.exception.TaskAlreadyExecutedException;
import org.digitalact.emailtask.exception.TaskExpiredException;
import org.digitalact.exception.DomainException;


/**
 * Klasa odpowiedzialna za wykonanie zadania potwierdzanego emailem.
 * @author Marcin Pieciukiewicz
 */
public interface MailTaskExecutor {

    /**
     * Metoda umożliwiająca zarejestrowanie klasy odpowiedzialnej za obsługę konkretnego zadania.
     * @param handler klasa obsługująca zadanie
     */
    public void registerHandler(TaskHandler handler);

    /**
     * Metoda wykonuje zadanie o podanym kodzie.
     * @param taskCode kod zadania
     * @return url strony oznaczającej poprawne wykonanie zadania
     * @throws TaskExpiredException w przypadku wygaśnięcia zadania
     * @throws TaskAlreadyExecutedException gdy zadanie było już wcześniej wykonane
     * @throws IncorrectTaskCodeException gdy podany kod zadania jest niepoprawny
     * @throws DomainException gdy zgłoszony został wyjątek podczas wykonanywania zadania
     */
    public String executeTask(String taskCode) throws TaskExpiredException, TaskAlreadyExecutedException, IncorrectTaskCodeException,
    DomainException;

}
