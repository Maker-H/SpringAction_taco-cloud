package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.domain.Ingredient;
import tacos.domain.Ingredient.Type;
import tacos.domain.Taco;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {

        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("F", "Flour", Type.WRAP),
                new Ingredient("P", "Ground Beef", Type.PROTEIN),
                new Ingredient("P22", "Ground Beef222", Type.PROTEIN),
                new Ingredient("S", "Sour Cream", Type.SAUCE)
        );

        Type[] types = Type.values();
        for (Type t : types) {
            String typeName = t.toString().toLowerCase();
            List<Ingredient> ingredientsFilterByType = this.filterByType(ingredients, t);

            model.addAttribute(typeName, ingredientsFilterByType);
        }

        model.addAttribute("taco", new Taco());

        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processDesign(Model model, @Valid Taco design, Errors errors) {
        if (errors.hasErrors()) {
//            return this.showDesignForm(model, errors);
            log.error("error taco: " + design);
            return "design";
        }
        //TODO: 타코만들어지기 위해 선택된 식자재 내역 불러오기
        log.info("Processing design: " + design);

        return "redirect:/orders/current";
    }

}
