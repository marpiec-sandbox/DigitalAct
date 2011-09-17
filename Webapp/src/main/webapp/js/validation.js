function validateEmail(email) {
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    return reg.test(email);
}

function getMessageContainer(element) {
    return element.parent().find(".validatorMessage");
}

function hideValidationMessage(element) {
    getMessageContainer(element).html("");
}

function showValidationMessage(element, message) {
    getMessageContainer(element).html(message);
}

function emailValidator() {

    var jqThis = jQuery(this);
    var value = jqThis.val();

    if (validateEmail(value)) {
        hideValidationMessage(jqThis);
    } else {
        showValidationMessage(jqThis, "Niepoprawny adres email");
    }
}

function requiredValidator() {
    var jqThis = jQuery(this);
    var value = jqThis.val();

    if (value.trim() == "") {
        showValidationMessage(jqThis, "Pole jest wymagane");
    } else {
        hideValidationMessage(jqThis);
    }
}

function isFormValidatedOK(element) {
    var validatedOK = true;
    var messages = element.find(".validatorMessage");
    for (var p = 0; p < messages.length; p++) {
        var message = jQuery(messages[p]);
        if (message.html() != "") {
            validatedOK = false;
            break;
        }
    }
    return validatedOK;
}

jQuery(function() {

    jQuery(".emailValidator").each(function() {
        jQuery(this).blur(emailValidator);
    });

    jQuery(".requiredValidator").each(function() {
        jQuery(this).blur(requiredValidator);
    });

    jQuery("form.formValidator").submit(function() {
        var jqThis = jQuery(this);
        jqThis.find(".emailValidator").each(emailValidator);
        jqThis.find(".requiredValidator").each(requiredValidator);

        return isFormValidatedOK(jqThis);
    });


});