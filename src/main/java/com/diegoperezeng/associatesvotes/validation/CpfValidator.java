package com.diegoperezeng.associatesvotes.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CpfValidator implements ConstraintValidator<ValidCpf, String> {
    private static final Pattern CPF_PATTERN = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Null values are validated by @NotNull or @NotBlank annotations
        }
        if (!CPF_PATTERN.matcher(value).matches()) {
            return false;
        }
        return validateCpf(value.replaceAll("\\D", ""));
    }

    private boolean validateCpf(String cpf) {
        int[] factors1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] factors2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        String digit1 = calculateDigit(cpf.substring(0, 9), factors1);
        String digit2 = calculateDigit(cpf.substring(0, 9) + digit1, factors2);
        return cpf.equals(cpf.substring(0, 9) + digit1 + digit2);
    }

    private String calculateDigit(String source, int[] factors) {
        int sum = 0;
        for (int i = 0; i < source.length(); i++) {
            sum += (source.charAt(i) - '0') * factors[i];
        }
        int remainder = sum % 11;
        return remainder < 2 ? "0" : String.valueOf(11 - remainder);
    }
}
