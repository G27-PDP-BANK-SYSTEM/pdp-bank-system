package com.example.card.card;


import com.example.card.card.dto.CardCreateDto;
import com.example.card.card.dto.CardResponseDto;
import com.example.card.card.dto.CardUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/card")
@RequiredArgsConstructor
@RestController
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<CardResponseDto>create(@RequestBody CardCreateDto createDto){
        CardResponseDto cardResponseDto = cardService.create(createDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cardResponseDto);
    }

     @GetMapping("/{id}")
    public ResponseEntity<CardResponseDto>getById(@PathVariable ("id")Integer id){
         CardResponseDto cardResponseDto = cardService.getById(id);
         return ResponseEntity.ok()
                .body(cardResponseDto);
    }

     @GetMapping()
    public ResponseEntity<Page<CardResponseDto>>getAll(Pageable pageable, @RequestParam(required = false) String predicate){
         Page<CardResponseDto> all = cardService.getAll(pageable, predicate);
          return ResponseEntity.status(HttpStatus.OK)
                .body(all);
     }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Integer id){
        cardService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardResponseDto>update(@PathVariable Integer id, @RequestBody CardUpdateDto cardUpdateDto){
        CardResponseDto cardResponseDto = cardService.update(id, cardUpdateDto);
         return ResponseEntity.ok()
                .body(cardResponseDto);
    }






}
