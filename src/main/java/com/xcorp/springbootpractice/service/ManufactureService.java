package com.xcorp.springbootpractice.service;

import com.xcorp.springbootpractice.dto.request.ReqManufactureDto;
import com.xcorp.springbootpractice.dto.response.ResManufactureDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManufactureService {
  Page<ResManufactureDto> getAllManufactures(Pageable pageable);

  ResManufactureDto createManufacture(ReqManufactureDto manufacture);

  ResManufactureDto updateManufacture(ReqManufactureDto newManufacture);

  String removeManufacture(String id);
}
