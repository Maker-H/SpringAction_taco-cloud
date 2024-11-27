package tacos.controller.mvc;

//@Slf4j
//@Controller
//@RequestMapping("/design")
//@SessionAttributes("order")
//public class MVCDesignTacoController {
//
//    private final IngredientRepository ingredientRepository;
//
//    private final TacoRepository tacoRepository;
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public MVCDesignTacoController(IngredientRepository ingredientRepository,
//                                   UserRepository userRepository,
//                                   TacoRepository tacoRepository) {
//        this.ingredientRepository = ingredientRepository;
//        this.tacoRepository = tacoRepository;
//        this.userRepository = userRepository;
//    }
//
//    @GetMapping
//    public String showDesignForm(Model model, Principal principal) {
//
//        log.info("=> design controller [show design form]");
//        List<Ingredient> ingredients = new ArrayList<>();
//        ingredientRepository.findAll().forEach(i -> ingredients.add(i));
//
//        Type[] types = Type.values();
//        for (Type t : types) {
//            String typeName = t.toString().toLowerCase();
//            List<Ingredient> ingredientsFilterByType = this.filterByType(ingredients, t);
//
//            model.addAttribute(typeName, ingredientsFilterByType);
//        }
//
//        try {
//
//            String username = principal.getName();
//            User user = userRepository.findByUsername(username);
//            model.addAttribute("user", user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "design";
//    }
//
//    @PostMapping
//    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
//        log.info("=> design controller [processDesign]");
//        if (errors.hasErrors()) {
//            log.error("error taco: " + design);
//            return "design";
//        }
//        log.info("Processing design: " + design);
//
//        Taco saved = tacoRepository.save(design);
//        order.addDesign(saved);
//
//        return "redirect:/orders/current";
//    }
//
//    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
//        return ingredients.stream()
//                .filter(x -> x.getType().equals(type))
//                .collect(Collectors.toList());
//    }
//
//    @ModelAttribute(name = "order")
//    public Order order() {
//        return new Order();
//    }
//
//    @ModelAttribute(name = "taco")
//    public Taco taco() {
//        return new Taco();
//    }



//}
