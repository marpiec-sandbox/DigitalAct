package org.digitalact.engine.validation;

import org.digitalact.faces.MyFacesUtils;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Validator umożliwiający wykonanie walidacji z wykorzystaniem hibernate validatora (jsr303).
 * @author Marcin Pieciukiewicz
 */
@Named
public class BeanValidator {

    private Validator validator;

    /**
     * Inicjalizacja beana.
     */
    @PostConstruct
    public void init() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    /**
     * Wykonywana jest walidacja jsr303 na podanym obiekcie.
     * @param bean obiekt do zwalidowania
     * @return lista z komunikatami błóędów walidacji
     */
    public List<String> validate(Object bean) {
        
        List<String> errorMessages = new ArrayList<String>();
        Set<ConstraintViolation<Object>> constraintViolations = validateBean(bean);

        for(ConstraintViolation<Object> violation: constraintViolations) {
            errorMessages.add(violation.getPropertyPath()+" "+violation.getMessage());
        }

        return errorMessages;
    }

    /**
     * Dokonuje walidację obiektu umieszczając komunikaty błędów w komuniatach JSF.
     * @param bean obiekt do zwalidowania
     * @return false w przypadku błędów walidacji
     */
    public boolean validateWithMessages(Object bean) {
        Set<ConstraintViolation<Object>> constraintViolations = validateBean(bean);

        for(ConstraintViolation<Object> violation: constraintViolations) {
            MyFacesUtils.addValidationMessage(violation.getPropertyPath().toString(),
                    violation.getMessage());
        }

        return constraintViolations.isEmpty();
    }

    private Set<ConstraintViolation<Object>> validateBean(Object bean) {
        return validator.validate(bean);
    }

}
