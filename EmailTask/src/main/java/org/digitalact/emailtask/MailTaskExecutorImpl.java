package org.digitalact.emailtask;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import org.digitalact.emailtask.dao.EmailTaskDao;
import org.digitalact.emailtask.entity.EmailTask;
import org.digitalact.emailtask.exception.IncorrectTaskCodeException;
import org.digitalact.emailtask.exception.TaskAlreadyExecutedException;
import org.digitalact.emailtask.exception.TaskExpiredException;
import org.digitalact.exception.DomainException;
import org.digitalact.utils.MyJsonSerializer;

/**
 * Serwlet odpowiedzialny za wykonanie zadania wywołanego linkiem z przesłanego emaila.
 *
 * @author mpieciukiewicz
 */
@Named
public class MailTaskExecutorImpl implements MailTaskExecutor {

    @Inject
    private EmailTaskDao emailTaskDao;
    private Map<Class<? extends Task>, TaskHandler> handlers =
            new HashMap<Class<? extends Task>, TaskHandler>();

    @Override
    public void registerHandler(TaskHandler handler) {
        handlers.put(handler.getTaskClass(), handler);
    }

    @Override
    public String executeTask(String taskCode) throws TaskExpiredException, TaskAlreadyExecutedException, IncorrectTaskCodeException,
            DomainException {
        EmailTask emailTask = emailTaskDao.findByTaskCode(taskCode);

        if (emailTask == null) {
            throw new IncorrectTaskCodeException();
        }

        if(taskAlreadyExecuted(emailTask)) {
            throw new TaskAlreadyExecutedException();
        }

        Task task = deserializeToTask(emailTask);

        if (taskExpired(emailTask))
        {
            throw new TaskExpiredException(task.getExiredLink());
        }
        
        Date executionTime = new Date();
        TaskHandler taskHandler = handlers.get(task.getClass());
        taskHandler.handle(task);

        emailTask.setExecutionTime(executionTime);
        emailTaskDao.save(emailTask);

        return task.getSuccessLink();
    }

    private Task deserializeToTask(EmailTask emailTask) throws IllegalStateException {
        try {
            Class<?> taskClass = Class.forName(emailTask.getTaskClass());
            Task task = MyJsonSerializer.deserialize(emailTask.getSerializedTask(), taskClass);
            return task;
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Error getting class named " + emailTask.getTaskClass(), ex);
        }
    }

    private boolean taskExpired(EmailTask emailTask) {
        return emailTask.getExpiriationTime().before(new Date());
    }

    private boolean taskAlreadyExecuted(EmailTask emailTask) {
        return emailTask.getExecutionTime() != null;
    }
}
