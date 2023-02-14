package com.diegoperezeng.associatesvotes.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = CpfValidator.class)
@Documented
public @interface ValidCpf {
    String message() default "Invalid CPF number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

