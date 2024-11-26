package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import tacos.data.IngredientRepository;
import tacos.domain.Ingredient;

@Slf4j
@Component
public class IngredientByIdConverter
        implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id) {
        log.info("converter ingredient id: " + id);
        return ingredientRepository.findById(id);
    }

}
