package com.bg.bzahov.achievementsBG.model.validators;

import com.bg.bzahov.achievementsBG.model.Gender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<ValidGender, Gender> {

    @Override
    public void initialize(ValidGender constraintAnnotation) {
    }

    @Override
    public boolean isValid(Gender field, ConstraintValidatorContext constraintValidatorContext) {
        return field != null && validValue(field/*, Gender.class*/);
    }

//    private static boolean validValue(Gender genderField) {
//        return genderField.equals(MALE.toString()) || genderField.equals(FEMALE.toString());
//    }
    private static boolean validValue(Gender genderField) {
        try {
            Gender.valueOf(genderField.toString());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static <E extends Enum<E>> boolean validValue(E value, Class<E> enumClass) {
        try {
            Enum.valueOf(enumClass, value.toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}