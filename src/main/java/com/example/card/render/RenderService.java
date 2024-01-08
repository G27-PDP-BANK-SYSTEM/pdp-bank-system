package com.example.card.render;

import com.example.card.card.CardRepository;
import com.example.card.card.entity.Card;
import com.example.card.common.exception.EntityNotFoundException;
import com.example.card.render.dto.RenderCreateDto;
import com.example.card.render.dto.RenderResponseDto;
import com.example.card.render.entity.Render;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RenderService {
    private final CardRepository cardRepository;
    private final RenderRepository renderRepository;
    private final ModelMapper modelMapper;


    @Transactional
    public RenderResponseDto createPayment(RenderCreateDto createDto) {

        try {
            Card card = cardRepository.findByCardNumber(createDto.getCardNumber()).orElseThrow(() -> new EntityNotFoundException("Card not Found"));
            Card recipientCard = cardRepository.findByCardNumber(createDto.getRecipientCardNumber()).orElseThrow(() -> new EntityNotFoundException("Card not Found"));

            if (card.getBalance() >= createDto.getRecipientAmount() * (1 + (createDto.getCommission() / 100))) {

                card.setBalance(card.getBalance() - createDto.getRecipientAmount() * (1 + (createDto.getCommission() / 100)));


                recipientCard.setBalance(recipientCard.getBalance() + createDto.getRecipientAmount());

                Render render = modelMapper.map(createDto, Render.class);

                renderRepository.save(render);

                return modelMapper.map(render, RenderResponseDto.class);
            } else {
                String errorMessage = "Not enough money. You don't have enough funds";
                log.error("There are insufficient funds for the money transfer: {}", errorMessage);
                throw new jakarta.persistence.EntityNotFoundException(errorMessage);
            }
        } catch (Exception e) {
            String errorMessage = "An error occurred: " + e.getMessage();
            log.error(errorMessage, e);
            throw new jakarta.persistence.EntityNotFoundException(errorMessage);
        }

    }

    public void delete(Long id) {
        renderRepository.deleteById(id);
    }

    public RenderResponseDto getPayment(Long id) {
        Render render = renderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Render not found for id=%s".formatted(id)));
        return modelMapper.map(render, RenderResponseDto.class);
    }

    public Page<RenderResponseDto> getAll(Pageable pageable, String predicate) {
        Page<Render> all = renderRepository.findAll(pageable);
        List<RenderResponseDto> renderResponseDtos = all.getContent().stream()
                .map(render -> modelMapper.map(render, RenderResponseDto.class))
                .collect(Collectors.toList());

        return new PageImpl<>(renderResponseDtos, all.getPageable(), all.getTotalElements());
    }
}
