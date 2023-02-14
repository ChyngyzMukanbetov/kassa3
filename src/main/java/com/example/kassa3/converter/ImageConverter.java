package com.example.kassa3.converter;

import com.example.kassa3.model.dto.ImageDto;
import com.example.kassa3.model.entity.Image;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ImageConverter {
    ImageDto toDTO(Image image);
    Image toModel(ImageDto imageDto);
}
