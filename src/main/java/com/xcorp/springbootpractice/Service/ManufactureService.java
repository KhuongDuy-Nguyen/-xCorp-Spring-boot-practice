package com.xcorp.springbootpractice.Service;

import com.xcorp.springbootpractice.DTO.Request.Req_ManufactureDTO;
import com.xcorp.springbootpractice.DTO.Response.Res_ManufactureDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManufactureService {
    Page<Res_ManufactureDTO> getAllManufactures(Pageable pageable);

    Res_ManufactureDTO createManufacture(Req_ManufactureDTO manufacture);

    Res_ManufactureDTO updateManufacture(Req_ManufactureDTO newManufacture) throws Exception;

    String removeManufacture(String id) throws Exception;
}
