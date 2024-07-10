package com.ladmakhi.projecttracker.features.collection;

import com.ladmakhi.projecttracker.features.collection.dtos.CreateCollectionDto;
import com.ladmakhi.projecttracker.features.collection.dtos.GetCollectionDetailDto;
import com.ladmakhi.projecttracker.features.collection.dtos.GetCollectionDto;
import com.ladmakhi.projecttracker.features.collection.dtos.UpdateCollectionDto;
import com.ladmakhi.projecttracker.features.users.User;

import java.util.List;

public interface CollectionService {
    GetCollectionDto createCollection(CreateCollectionDto dto, User createdBy) throws Exception;

    List<GetCollectionDto> getCollections();

    GetCollectionDetailDto getCollectionDetailById(Long id) throws Exception;

    Collection getCollectionById(Long id) throws Exception;

    GetCollectionDto deleteCollectionById(Long id) throws Exception;

    GetCollectionDto updateCollectionById(Long id, UpdateCollectionDto dto) throws Exception;
}
