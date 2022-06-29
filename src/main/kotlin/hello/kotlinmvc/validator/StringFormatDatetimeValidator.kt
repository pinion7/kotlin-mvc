package hello.kotlinmvc.validator

import hello.kotlinmvc.annotation.StringFormatDateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class StringFormatDatetimeValidator: ConstraintValidator<StringFormatDateTime, String> {

    private var pattern: String? = null;

    override fun initialize(constraintAnnotation: StringFormatDateTime?) {
        this.pattern = constraintAnnotation?.pattern;
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return try {
            println(value)
            LocalDateTime.parse(value, DateTimeFormatter.ofPattern(this.pattern));
            true;
        } catch (e: Exception) {
            false;
        }
    }
}