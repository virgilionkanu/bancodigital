package br.com.bancodigital.constraint;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Constraint(validatedBy = Cep.CepValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cep {
    String message() default "Cep inválido";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

     class CepValidator implements ConstraintValidator<Cep, String> {

        private final Pattern pattern =
                Pattern.compile("[0-9]{5}-[0-9]{3}");

        @Override
        public boolean isValid(String value,
                               ConstraintValidatorContext context) {
            Matcher m = pattern.matcher(value);
            return m.matches();
        }

    }
}
