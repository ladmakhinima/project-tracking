package com.ladmakhi.projecttracker.features.collection;

import com.ladmakhi.projecttracker.core.annotations.GetCurrentUser;
import com.ladmakhi.projecttracker.features.collection.dtos.CreateCollectionDto;
import com.ladmakhi.projecttracker.features.collection.dtos.GetCollectionDetailDto;
import com.ladmakhi.projecttracker.features.collection.dtos.GetCollectionDto;
import com.ladmakhi.projecttracker.features.collection.dtos.UpdateCollectionDto;
import com.ladmakhi.projecttracker.features.users.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collections")
@RequiredArgsConstructor
public class CollectionController {
    private final CollectionService collectionService;

    @PostMapping
    public ResponseEntity<?> createCollection(@Valid @RequestBody CreateCollectionDto dto, @GetCurrentUser() User user) throws Exception {
        GetCollectionDto responseDto = collectionService.createCollection(dto, user);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getCollections() {
        List<GetCollectionDto> responseDto = collectionService.getCollections();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCollectionDetailById(@PathVariable("id") Long collectionId) throws Exception {
        GetCollectionDetailDto responseDto = collectionService.getCollectionDetailById(collectionId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCollectionById(@PathVariable("id") Long id) throws Exception {
        GetCollectionDto responseDto = collectionService.deleteCollectionById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updateCollectionById(@PathVariable("id") Long id, @RequestBody @Valid UpdateCollectionDto dto) throws Exception {
        GetCollectionDto response = collectionService.updateCollectionById(id, dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
