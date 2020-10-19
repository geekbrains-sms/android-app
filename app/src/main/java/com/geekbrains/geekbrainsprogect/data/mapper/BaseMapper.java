package com.geekbrains.geekbrainsprogect.data.mapper;

import com.geekbrains.geekbrainsprogect.data.api.dto.ProductDTO;
import com.geekbrains.geekbrainsprogect.data.model.entity.join.ProductWithCategory;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProduct;
import com.geekbrains.geekbrainsprogect.domain.model.ProductModel;

import java.util.List;

public interface BaseMapper<DTO, Entity, Model, Interf> {
    Model toModel(Interf object);
    DTO toDto(Interf object);
    Entity toEntity(Interf object);
    List<Model> toModelList(List<? extends Interf>list);
    List<DTO>toDtoList(List<? extends Interf>list);
    List<Entity>toEntityList(List<? extends Interf>list);
}
