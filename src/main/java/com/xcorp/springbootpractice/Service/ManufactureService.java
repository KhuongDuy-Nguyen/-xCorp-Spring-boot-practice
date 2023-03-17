package com.xcorp.springbootpractice.Service;

import com.xcorp.springbootpractice.Model.Manufacture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ManufactureService {
    Page<List<Manufacture>> getAllManufactures(Pageable pageable);

    Manufacture createManufacture(Manufacture manufacture);

    Manufacture updateManufacture(Manufacture newManufacture, String id) throws Exception;

    String removeManufacture(String id) throws Exception;
}
