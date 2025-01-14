package cn.hutool.extra.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

public class ValidationUtil {
  private static final Validator validator;
  
  static {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      validator = factory.getValidator();
    } 
  }
  
  public static Validator getValidator() {
    return validator;
  }
  
  public static <T> Set<ConstraintViolation<T>> validate(T bean, Class<?>... groups) {
    return validator.validate(bean, groups);
  }
  
  public static <T> Set<ConstraintViolation<T>> validateProperty(T bean, String propertyName, Class<?>... groups) {
    return validator.validateProperty(bean, propertyName, groups);
  }
  
  public static <T> BeanValidationResult warpValidate(T bean, Class<?>... groups) {
    return warpBeanValidationResult(validate(bean, groups));
  }
  
  public static <T> BeanValidationResult warpValidateProperty(T bean, String propertyName, Class<?>... groups) {
    return warpBeanValidationResult(validateProperty(bean, propertyName, groups));
  }
  
  private static <T> BeanValidationResult warpBeanValidationResult(Set<ConstraintViolation<T>> constraintViolations) {
    BeanValidationResult result = new BeanValidationResult(constraintViolations.isEmpty());
    for (ConstraintViolation<T> constraintViolation : constraintViolations) {
      BeanValidationResult.ErrorMessage errorMessage = new BeanValidationResult.ErrorMessage();
      errorMessage.setPropertyName(constraintViolation.getPropertyPath().toString());
      errorMessage.setMessage(constraintViolation.getMessage());
      errorMessage.setValue(constraintViolation.getInvalidValue());
      result.addErrorMessage(errorMessage);
    } 
    return result;
  }
}
