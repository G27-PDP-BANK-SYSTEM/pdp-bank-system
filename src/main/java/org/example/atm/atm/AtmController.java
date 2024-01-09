package org.example.atm.atm;

import lombok.RequiredArgsConstructor;
import org.example.atm.atm.dto.ATMCreateDto;
import org.example.atm.atm.dto.ResponseDto;
import org.example.atm.atm.entity.ATM;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/atm")
public class AtmController {
    private final ATMService atmService;

    @GetMapping
    public List<ATM> get() {
        return atmService.getAll();
    }

    @PostMapping
    public ResponseDto create(@RequestBody ATMCreateDto createDto) {
        return atmService.create(createDto);
    }

    @GetMapping("/closest")
    public List<ResponseDto> getClosest(
            @RequestParam("lon") Double lon,
            @RequestParam("lat") Double lat) {
        return atmService.closest(lon, lat);
    }
}
