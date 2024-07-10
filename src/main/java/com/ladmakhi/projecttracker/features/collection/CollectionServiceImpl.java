package com.ladmakhi.projecttracker.features.collection;

import com.ladmakhi.projecttracker.features.board.Board;
import com.ladmakhi.projecttracker.features.board.BoardService;
import com.ladmakhi.projecttracker.features.board.BoardServiceImpl;
import com.ladmakhi.projecttracker.features.collection.dtos.CreateCollectionDto;
import com.ladmakhi.projecttracker.features.collection.dtos.GetCollectionDetailDto;
import com.ladmakhi.projecttracker.features.collection.dtos.GetCollectionDto;
import com.ladmakhi.projecttracker.features.collection.dtos.UpdateCollectionDto;
import com.ladmakhi.projecttracker.features.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public GetCollectionDto createCollection(CreateCollectionDto dto, User createdBy) throws Exception {
        boolean isDuplicatedName = collectionRepository.existsByNameAndBoard_Id(dto.name(), dto.boardId());
        if (isDuplicatedName) {
            throw new Exception("Some Collection With This name exist");
        }
        Board board = boardService.getBoardById(dto.boardId());
        Collection collection = new Collection(dto.name(), board, createdBy);
        collection = collectionRepository.save(collection);
        return collectionMapper.mapCollectionToGetCollectionDto(collection);
    }

    @Override
    public List<GetCollectionDto> getCollections() {
        List<Collection> collections = collectionRepository.findAll();
        return collectionMapper.mapListOfCollectionToListOfGetCollectionDto(collections);
    }

    @Override
    public GetCollectionDetailDto getCollectionDetailById(Long id) throws Exception {
        Collection collection = getCollectionById(id);
        return collectionMapper.mapCollectionToGetCollectionDetailDto(collection);
    }

    @Override
    public Collection getCollectionById(Long id) throws Exception {
        return collectionRepository.findById(id).orElseThrow(() -> new Exception("Collection Not Found"));
    }

    @Override
    public GetCollectionDto deleteCollectionById(Long id) throws Exception {
        Collection collection = getCollectionById(id);
        collectionRepository.delete(collection);
        return collectionMapper.mapCollectionToGetCollectionDto(collection);
    }

    @Override
    public GetCollectionDto updateCollectionById(Long id, UpdateCollectionDto dto) throws Exception {
        Collection collection = getCollectionById(id);
        collection.setName(dto.name());
        collectionRepository.save(collection);
        return collectionMapper.mapCollectionToGetCollectionDto(collection);
    }
}
