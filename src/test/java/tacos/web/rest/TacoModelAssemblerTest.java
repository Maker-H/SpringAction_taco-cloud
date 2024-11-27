package tacos.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import tacos.data.TacoRepository;
import tacos.domain.Taco;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TacoModelAssemblerTest {

    @Autowired
    private static TacoRepository tacoRepo;

    public static void main(String[] args) {
        Taco taco = new Taco();
        taco.setId(5L);
        taco.setName("thistaco");
        taco.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        TacoModelAssembler tacoModelAssembler = new TacoModelAssembler();
        EntityModel<Taco> model = tacoModelAssembler.toModel(taco);

        System.out.println(model);

        Taco taco2 = new Taco();
        taco.setId(10L);
        taco.setName("thistaco111");
        taco.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        List<Taco> tacoList = new ArrayList<>();
        tacoList.add(taco2);
        tacoList.add(taco);

        CollectionModel<EntityModel<Taco>> collectionModel = tacoModelAssembler.toCollectionModel(tacoList);
        System.out.println(collectionModel);
    }

}