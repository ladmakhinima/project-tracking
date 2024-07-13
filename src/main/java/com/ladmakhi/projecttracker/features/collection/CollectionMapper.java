package com.ladmakhi.projecttracker.features.collection;

import com.ladmakhi.projecttracker.features.collection.dtos.GetCollectionDetailDto;
import com.ladmakhi.projecttracker.features.collection.dtos.GetCollectionDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CollectionMapper {
    GetCollectionDto mapCollectionToGetCollectionDto(Collection collection);

    List<GetCollectionDto> mapListOfCollectionToListOfGetCollectionDto(List<Collection> collections);

    GetCollectionDetailDto mapCollectionToGetCollectionDetailDto(Collection collection);
}
