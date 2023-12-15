package sda.catalogue.sdacataloguerestapi.core.TangerangValidation.TangerangAnnotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TGRMax {
    long maxValue() default 0;

    String message() default "Field maximum {maxValue} characters!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}