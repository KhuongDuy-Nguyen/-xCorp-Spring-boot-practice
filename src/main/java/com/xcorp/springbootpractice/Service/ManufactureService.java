package com.xcorp.springbootpractice.Service;

import com.xcorp.springbootpractice.DTO.Request.ReqManufactureDto;
import com.xcorp.springbootpractice.DTO.Response.ResManufactureDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManufactureService {
  Page<ResManufactureDto> getAllManufactures(Pageable pageable);

  ResManufactureDto createManufacture(ReqManufactureDto manufacture);

  ResManufactureDto updateManufacture(ReqManufactureDto newManufacture);

  String removeManufacture(String id);
}
