package org.example.atm.cloud;

import org.example.atm.atm.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient( name = "googleMatrixFeign", url = "https://maps.googleapis.com/maps/api/distancematrix/json" )
@Component
public interface GoogleMatrixFeign
{
    @GetMapping
    ResponseDto getMatrix(
        @RequestParam( "key" ) String key,
        @RequestParam( "origins" ) String origins,
        @RequestParam( "destinations" ) String destinations );
}
