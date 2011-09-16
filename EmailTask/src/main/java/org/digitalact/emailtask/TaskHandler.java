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

    public TaskHandler(Class<? extends T> taskClass) {
        this.taskClass = taskClass;
    }

    public Class<? extends Task> getTaskClass() {
        return taskClass;
    }

    abstract public void registerHandler();
    
    public abstract void handle(T task) throws DomainException;
    
}
