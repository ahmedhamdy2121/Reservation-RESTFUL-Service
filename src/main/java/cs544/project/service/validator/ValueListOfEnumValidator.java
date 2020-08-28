/**
 * Ahmed Hamdy
 */
package cs544.project.service.validator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Ahmed Hamdy
 *
 */
public class ValueListOfEnumValidator
        implements ConstraintValidator<ValueListOfEnum, List<String>> {

    private List<String> acceptedValues;

    @Override
    public void initialize(ValueListOfEnum annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(List<String> values,
                           ConstraintValidatorContext context) {
        if (values == null) {
            return true;
        }

        return values.stream().filter(acceptedValues::contains)
                .count() == values.size();
    }

}
