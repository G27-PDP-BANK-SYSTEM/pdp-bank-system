package com.example.card.render;

import com.example.card.render.dto.RenderCreateDto;
import com.example.card.render.dto.RenderResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/render")
@RequiredArgsConstructor
public class RenderController {
    private final RenderService paymentService;

    @PostMapping
    public ResponseEntity<RenderResponseDto> createPayment(@RequestBody @Valid RenderCreateDto createDto){
        RenderResponseDto entityResponse = paymentService.createPayment(createDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(entityResponse);
    }

    @GetMapping
    public ResponseEntity<Page<RenderResponseDto>> getAllPayment(Pageable pageable, @RequestParam(required = false) String predicate){
        Page<RenderResponseDto> paymets = paymentService.getAll(pageable, predicate);

        return ResponseEntity.status(HttpStatus.OK).body(paymets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RenderResponseDto> getPayment(@PathVariable Long id){
        RenderResponseDto paymets = paymentService.getPayment(id);

        return ResponseEntity.status(HttpStatus.OK).body(paymets);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable Long id){
        paymentService.delete(id);
        return ResponseEntity.ok().build();
    }

}
