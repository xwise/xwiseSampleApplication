package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.MyFirstComponentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MyFirstComponent and its DTO MyFirstComponentDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MyFirstComponentMapper extends EntityMapper<MyFirstComponentDTO, MyFirstComponent> {



    default MyFirstComponent fromId(Long id) {
        if (id == null) {
            return null;
        }
        MyFirstComponent myFirstComponent = new MyFirstComponent();
        myFirstComponent.setId(id);
        return myFirstComponent;
    }
}
