package com.ladmakhi.projecttracker.features.collection;

import com.ladmakhi.projecttracker.features.board.Board;
import com.ladmakhi.projecttracker.features.board.BoardService;
import com.ladmakhi.projecttracker.features.collection.dtos.CreateCollectionDto;
import com.ladmakhi.projecttracker.features.collection.dtos.GetCollectionDetailDto;
import com.ladmakhi.projecttracker.features.collection.dtos.GetCollectionDto;
import com.ladmakhi.projecttracker.features.collection.dtos.UpdateCollectionDto;
import com.ladmakhi.projecttracker.features.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {
    private final CollectionRepository collectionRepository;
    private final BoardService boardService;
    private final CollectionMapper collectionMapper;

    @Override
    public GetCollectionDto createCollection(CreateCollectionDto dto, User createdBy) throws Exception {
        if (collectionRepository.existsByNameAndBoard_Id(dto.getName(), dto.getBoardId())) {
            throw new Exception("Some Collection With This name exist");
        }
        Board board = boardService.getBoardById(dto.getBoardId());

        Collection collection = Collection.builder()
                .name(dto.getName())
                .board(board)
                .createdBy(createdBy)
                .build();
        Collection savedCollection = collectionRepository.save(collection);
        return collectionMapper.mapCollectionToGetCollectionDto(savedCollection);
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
        return collectionRepository.findById(id)
                .orElseThrow(() -> new Exception("Collection Not Found"));
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
        collection.setName(dto.getName());
        collectionRepository.save(collection);
        return collectionMapper.mapCollectionToGetCollectionDto(collection);
    }
}
