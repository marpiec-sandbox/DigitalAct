package org.digitalact.engine.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author Marcin Pieciukiewicz
 */
@Named
public class BeanValidator {

    private Validator validator;
    
    @PostConstruct
    public void init() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    public List<String> validate(Object bean) {
        
        List<String> errorMessages = new ArrayList<String>();
        Set<ConstraintViolation<Object>> constraintViolations = validateBean(bean);

        for(ConstraintViolation<Object> violation: constraintViolations) {
            errorMessages.add(violation.getPropertyPath()+" "+violation.getMessage());
        }

        return errorMessages;
    }
    
    public boolean validateWithMessages(Object bean) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Set<ConstraintViolation<Object>> constraintViolations = validateBean(bean);

        for(ConstraintViolation<Object> violation: constraintViolations) {
            FacesMessage facesMessage = new FacesMessage(violation.getMessage());
            facesContext.addMessage(violation.getPropertyPath().toString(), facesMessage);
        }

        return constraintViolations.isEmpty();
    }

    private Set<ConstraintViolation<Object>> validateBean(Object bean) {
        
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(bean);
        return constraintViolations;
    }
    



}
