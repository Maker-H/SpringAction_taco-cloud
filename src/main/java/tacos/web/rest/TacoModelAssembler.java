package tacos.web.rest;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import tacos.controller.RestDesignTacoController;
import tacos.domain.Taco;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TacoModelAssembler implements RepresentationModelAssembler<Taco, EntityModel<Taco>> {
    @Override
    public EntityModel<Taco> toModel(Taco taco) {

        EntityModel<Taco> tacoModel = new EntityModel<>(taco);

        tacoModel.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(RestDesignTacoController.class).tacoById(taco.getId())
                ).withSelfRel()
        );

        return tacoModel;
    }


    @Override
    public CollectionModel<EntityModel<Taco>> toCollectionModel(Iterable<? extends Taco> tacos) {
        // 각 Taco 객체를 EntityModel로 변환
        List<EntityModel<Taco>> tacoModels = StreamSupport.stream(tacos.spliterator(), false)
                .map(this::toModel)
                .collect(Collectors.toList());

    // CollectionModel 생성자를 직접 사용
        CollectionModel<EntityModel<Taco>> collectionModel = new CollectionModel<>(tacoModels);

    // 링크 수동 추가
        collectionModel.add(
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestDesignTacoController.class).recentTacos())
                        .withSelfRel()
        );

        return collectionModel;
    }
}
