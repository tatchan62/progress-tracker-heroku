package io.myproject.tasktracker.validator;

import io.myproject.tasktracker.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// validate password and confirmPassword
@Component
public class UserValidator implements Validator {

    // This aClass equals the User class
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    // Password validation
    @Override
    public void validate(Object object, Errors errors) {

        User user = (User) object;

        if (user.getPassword().length() < 6){
            errors.rejectValue("password", "Length", "Password must be at least 6 characters");
        }
        // if password not equal
        if (!user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue("confirmPassword", "Match", "Password must be matched");
        }
    }
}
