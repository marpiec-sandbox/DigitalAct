package org.digitalact.emailtask.servlet;

import org.apache.commons.lang.StringUtils;
import org.digitalact.constants.MyConstants;
import org.digitalact.emailtask.MailTaskExecutor;
import org.digitalact.emailtask.exception.IncorrectTaskCodeException;
import org.digitalact.emailtask.exception.TaskAlreadyExecutedException;
import org.digitalact.emailtask.exception.TaskExpiredException;
import org.digitalact.exception.DomainException;
import org.digitalact.utils.MyServletUtils;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Serwlet odpowiedzialny za wykonanie zadania wywołanego linkiem z przesłanego emaila.
 *
 * @author Marcin Pieciukiewicz
 */
public class MailTaskExecutionServlet extends HttpServlet {

    @Inject
    private MailTaskExecutor mailTaskExecutor;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        MyServletUtils.applyBeanInjection(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskCode = request.getParameter(MyConstants.MailTask.TASK_CODE_PARAMETER_NAME);

        if (StringUtils.isNotBlank(taskCode)) {
            executeTask(response, taskCode);
        } else {
            redirectToIncorrectTaskCodePage(response);
        }
    }

    private void executeTask(HttpServletResponse response, String taskCode) throws IOException {
        try {
            String successLink = mailTaskExecutor.executeTask(taskCode);
            redirectToSuccessfulExecutionPage(response, successLink);
        } catch (TaskExpiredException ex) {
            redirectToTaskExpiredPage(response, ex.getTaskExpiredLink());
        } catch (TaskAlreadyExecutedException ex) {
            redirectToTaskAlreadyExecuted(response);
        } catch (IncorrectTaskCodeException ex) {
            redirectToIncorrectTaskCodePage(response);
        } catch (DomainException ex) {
            redirectAfterDomainException(response, ex);
        }
    }

    private void redirectToSuccessfulExecutionPage(HttpServletResponse response, String successLink) throws IOException {
        response.sendRedirect(successLink);
    }

    private void redirectToTaskExpiredPage(HttpServletResponse response, String taskExpiredLink) throws IOException {
        response.sendRedirect(taskExpiredLink);
    }

    private void redirectToIncorrectTaskCodePage(HttpServletResponse response) throws IOException {
        response.sendRedirect(MyConstants.MailTask.INCORRECT_LINK);
    }

    private void redirectAfterDomainException(HttpServletResponse response, DomainException exception) throws IOException {
        response.sendRedirect(MyConstants.MailTask.DOMAIN_EXCEPTION_LINK);
    }

    private void redirectToTaskAlreadyExecuted(HttpServletResponse response) throws IOException {
        response.sendRedirect(MyConstants.MailTask.TASK_ALREADY_EXECUTED_LINK);
    }

}
