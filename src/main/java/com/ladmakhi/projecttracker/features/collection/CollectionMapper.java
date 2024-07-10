package com.ladmakhi.projecttracker.features.collection;

import com.ladmakhi.projecttracker.features.collection.dtos.GetCollectionDetailDto;
import com.ladmakhi.projecttracker.features.collection.dtos.GetCollectionDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollectionMapper {
    public GetCollectionDto mapCollectionToGetCollectionDto(Collection collection) {
        GetCollectionDto dto = new GetCollectionDto(
                collection.getId(),
                collection.getName(),
                collection.getBoard()
        );
        return dto;
    }

    public List<GetCollectionDto> mapListOfCollectionToListOfGetCollectionDto(List<Collection> collections) {
        return collections.stream().map(collection -> {
            GetCollectionDto dto = mapCollectionToGetCollectionDto(collection);
            return dto;
        }).toList();
    }

    public GetCollectionDetailDto mapCollectionToGetCollectionDetailDto(Collection collection) {
        GetCollectionDetailDto dto = new GetCollectionDetailDto(
                collection.getId(),
                collection.getName(),
                collection.getBoard(),
                collection.getTasks()
        );
        return dto;
    }
}
