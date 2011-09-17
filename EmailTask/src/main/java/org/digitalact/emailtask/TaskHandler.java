/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.digitalact.emailtask;

import org.digitalact.exception.DomainException;

/**
 * Klasa odpowiedzialna za wykonanie konkretnego zadania.
 * @author Marcin Pieciukiewicz
 */
public abstract class TaskHandler<T extends Task> {
    
    private Class<? extends T> taskClass;

    /**
     * Konstruktor.
     * @param taskClass klasa zadania, którą będzie obsługiwał handler
     */
    public TaskHandler(Class<? extends T> taskClass) {
        this.taskClass = taskClass;
    }

    public Class<? extends Task> getTaskClass() {
        return taskClass;
    }

    /**
     *  Metoda rejestrująca handler w executorze.
     */
    public abstract void registerHandler();

    /**
     * Metoda odpowiedzialna za obsługę zadania.
     * @param task zadanie do wykonania
     * @throws DomainException w przypadku błędów domeny
     */
    public abstract void handle(T task) throws DomainException;
    
}
