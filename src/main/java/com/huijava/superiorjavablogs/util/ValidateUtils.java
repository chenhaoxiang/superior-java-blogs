/**
 * huijava.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.util;


import com.huijava.superiorjavablogs.common.result.ValidateResult;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static com.google.common.collect.Iterables.getFirst;

/**
 * @author chenhx
 * @version ValidateUtils.java, v 0.1 2018-07-31 下午 10:54
 */
public class ValidateUtils {

    /**
     * 参数校验
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> ValidateResult validate(T object) {

        ValidateResult validateResult = new ValidateResult();
        validateResult.setSuccess(true);
        validateResult.setMsg("验证成功！");

        //获得验证器
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        //执行验证
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
        //如果有验证信息，则将第一个取出来包装成异常返回
        ConstraintViolation<T> constraintViolation = getFirst(constraintViolations, null);
        if (constraintViolation != null) {

            validateResult.setSuccess(false);
            validateResult.setMsg("[" + constraintViolation.getPropertyPath() + "]" + constraintViolation.getMessage());
        }

        return validateResult;
    }

}