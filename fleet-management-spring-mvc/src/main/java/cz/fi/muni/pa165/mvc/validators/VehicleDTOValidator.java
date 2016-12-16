/* package cz.fi.muni.pa165.mvc.validators;

import cz.fi.muni.pa165.dto.VehicleDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Jozef Krcho
 *
public class VehicleDTOValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return VehicleDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        VehicleDTO productCreateDTO = (VehicleDTO) target;
        /*if (productCreateDTO.getColor() == null) return;
        if (productCreateDTO.getPrice() == null) return;
        if (productCreateDTO.getColor() == Color.BLACK && productCreateDTO.getPrice().longValue() > 100)
            errors.rejectValue("price", "ProductCreateDTOValidator.expensive.black.product"); *
    }
} */
