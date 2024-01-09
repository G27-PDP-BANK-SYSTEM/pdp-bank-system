package org.example.pdpbanksystem.card;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.pdpbanksystem.card.dto.CardCreateDto;
import org.example.pdpbanksystem.card.dto.CardResponseDto;
import org.example.pdpbanksystem.card.dto.CardUpdateDto;
import org.example.pdpbanksystem.card.entity.Card;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    private final ModelMapper modelMapper;
    public CardResponseDto update(Integer id, CardUpdateDto cardUpdateDto) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));
        modelMapper.map(cardUpdateDto,card);
        Card save = cardRepository.save(card);
        return modelMapper.map(save,CardResponseDto.class);
    }

    @Transactional
    public CardResponseDto create(CardCreateDto createDto) {
        Card card = modelMapper.map(createDto, Card.class);
        Card save = cardRepository.save(card);
        return modelMapper.map(save, CardResponseDto.class);
    }

    public CardResponseDto getById(Integer id) {
         Card card = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));

         return modelMapper.map(card, CardResponseDto.class);
    }


    public void delete(Integer id) {
       Card card = cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));
       cardRepository.deleteById(card.getId());
    }

        public Page<CardResponseDto> getAll(Pageable pageable, String predicate) {
        Page<Card> all = cardRepository.findAll(pageable);

       return all.map(card -> modelMapper.map(card, CardResponseDto.class));
    }

}
