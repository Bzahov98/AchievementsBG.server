package com.bg.bzahov.achievementsBG.model.validators;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;

public class YearOfBirthValidator implements ConstraintValidator<ValidYearOfBirth, Integer> {

    public static final int MAX_YEARS = 100;

    @Override
    public void initialize(ValidYearOfBirth constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer yearOfBirth, ConstraintValidatorContext constraintValidatorContext) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return isAgeValid(yearOfBirth, currentYear) && yearOfBirth <= currentYear;
    }

    private static boolean isAgeValid(Integer yearOfBirth, int currentYear) {
        return currentYear - yearOfBirth < MAX_YEARS && currentYear - yearOfBirth > 0;
    }
}