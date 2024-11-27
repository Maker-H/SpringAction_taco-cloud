package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.data.TacoRepository;
import tacos.domain.Taco;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "*") //corf 해결
@RequestMapping(path = "/design",
        produces = "application/json")
public class RestDesignTacoController {

    private TacoRepository tacoRepo;

    @Autowired
    public RestDesignTacoController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping("/recent")
    public CollectionModel<EntityModel<Taco>> recentTacos() {
        log.info("recent controller enter =>");

        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepo.findAll(page).getContent();

        CollectionModel<EntityModel<Taco>> tacosModel = CollectionModel.wrap(tacos);

//        tacosModel.add(new Link("http://localhost:8080/design/recent", "recents"));

        tacosModel.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(RestDesignTacoController.class).recentTacos()
                ).withRel("recents")
        );
        return tacosModel;

    }

//    @GetMapping("/{id}")
//    public Taco tacoById(@PathVariable("id") Long id) {
//        Optional<Taco> optTaco = tacoRepo.findById(id);
//
//        if (optTaco.isPresent()) {
//            return optTaco.get();
//        }
//
//        return null;
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);

        if (optTaco.isPresent()) {
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);







    }

}
