package org.example.atm.atm;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.atm.atm.dto.ATMCreateDto;
import org.example.atm.atm.dto.ModelMapperDto;
import org.example.atm.atm.dto.ResponseDto;
import org.example.atm.atm.entity.ATM;
import org.example.atm.cloud.GoogleMatrixFeign;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ATMService {

    private final ATMRepository atmRepository;
    private final GoogleMatrixFeign googleMatrixFeign;
    private final ModelMapperDto mapper;

    final List<ResponseDto> responseList = new ArrayList<>();

    @Transactional
    public List<ATM> getAll() {
        return atmRepository.findAll();
    }

    @Transactional
    public ResponseDto create(ATMCreateDto createDto) {

        ATM atm = mapper.mapper().map(createDto, ATM.class);
        atmRepository.save(atm);
        return mapper.mapper().map(atm, ResponseDto.class);
    }

    @Transactional
    public List<ResponseDto> closest(Double lon, Double lat) {

        List<ATM> aLl = atmRepository.findAll();
        aLl.forEach(atm -> {

            ResponseDto response = googleMatrixFeign.getMatrix(
                    "AIzaSyBlTREKZcPQzhzR_vfcsAqH4EI5stw9EGs",
                    lon.toString() + "," + lat.toString(),
                    atm.getLongitude().toString() + "," + atm.getLatitude().toString()
            );
            responseList.add(response);
        });
        return responseList;
    }
}
