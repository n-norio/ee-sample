package biz.allrounder.jee7sample.resources.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import biz.allrounder.jee7sample.resources.PersonJsonView;
import biz.allrounder.jee7sample.resources.constrains.SalaryConstraint;

public class SalaryConstraintValidator implements ConstraintValidator<SalaryConstraint, PersonJsonView> {

	@Override
	public void initialize(SalaryConstraint constraintAnnotation) {
		// TODO Auto-generated method stub	
	}

	@Override
	public boolean isValid(PersonJsonView person, ConstraintValidatorContext context) {
		if ("boss".equals(person.position())) {
			if (person.salary() < 10000) {
				context.disableDefaultConstraintViolation();
				context
	              .buildConstraintViolationWithTemplate("boss invalid.")
	              .addPropertyNode("position")
	              .addConstraintViolation();
				
				context
	              .buildConstraintViolationWithTemplate("salary should be over 10000.")
	              .addPropertyNode("salary")
	              .addConstraintViolation();
				return false;
			}
		}
		return true;
	}

}
