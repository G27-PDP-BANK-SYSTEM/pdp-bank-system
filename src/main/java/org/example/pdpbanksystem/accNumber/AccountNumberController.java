package org.example.pdpbanksystem.accNumber;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.pdpbanksystem.accNumber.dto.AccountNumberResponseDto;
import org.example.pdpbanksystem.accNumber.dto.AccountNumberUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/accountNumber")
@RequiredArgsConstructor
public class AccountNumberController {

    private final AccountNumberService service;

    @PostMapping
    public ResponseEntity<AccountNumberResponseDto> save() {
        AccountNumberResponseDto accountNumberResponseDto = service.create();
        return ResponseEntity.status(HttpStatus.CREATED).body(accountNumberResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<AccountNumberResponseDto>> getAll(Pageable pageble,
                                                                 @RequestParam(required = false) String predicate) {
        Page<AccountNumberResponseDto> allUser = service.getAll(pageble, predicate);
        return ResponseEntity.ok(allUser);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<AccountNumberResponseDto> get(@PathVariable Integer id) {
        AccountNumberResponseDto accountNumberResponseDto = service.get(id);
        return ResponseEntity.ok(accountNumberResponseDto);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<AccountNumberResponseDto> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<AccountNumberResponseDto> put(@PathVariable Integer id, @Valid @RequestBody AccountNumberUpdateDto updateDto) {
        AccountNumberResponseDto update = service.update(id, updateDto);
        return ResponseEntity.ok(update);
    }
}
