package com.geekbrains.geekbrainsprogect.data.mapper.contract;

import java.util.List;

public interface BaseMapper<DTO, Entity, Model, Interf> {
    Model toModel(Interf object);
    DTO toDto(Interf object);
    Entity toEntity(Interf object);
    List<Model> toModelList(List<? extends Interf>list);
    List<DTO>toDtoList(List<? extends Interf>list);
    List<Entity>toEntityList(List<? extends Interf>list);
}
