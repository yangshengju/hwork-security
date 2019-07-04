package com.hwork.validator;

import com.hwork.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint ,Object> {
    Logger logger = LoggerFactory.getLogger(MyConstraintValidator.class);

    @Autowired
    HelloService helloService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        logger.info("MyConstraintValidator initialize...");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        helloService.greeting("Zhangsan");
        logger.info("in method isValid :" + value);
        return true;
    }
}
