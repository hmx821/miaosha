package com.hmx.miaosha.validator;

import com.hmx.miaosha.util.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @program: miaosha
 * @description:
 * @author: hmx
 * @create: 2021-10-05 23:05
 **/
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        int i = 1;
        if (required) {
            return ValidatorUtil.isMobile(s);
        }
        if (StringUtils.isBlank(s)) {
            return true;
        }
        return ValidatorUtil.isMobile(s);

    }
}
