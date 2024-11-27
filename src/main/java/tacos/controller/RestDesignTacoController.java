package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.data.TacoRepository;
import tacos.domain.Taco;

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
    public Iterable<Taco> recentTacos() {
        log.info("recent controller enter =>");
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Taco> content = tacoRepo.findAll(page).getContent();

        for (Taco t : content) {
            log.info("controller " + t.toString());
        }
        return content;
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
