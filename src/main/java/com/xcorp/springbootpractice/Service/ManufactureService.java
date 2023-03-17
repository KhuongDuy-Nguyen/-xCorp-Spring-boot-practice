package com.xcorp.springbootpractice.Service;

import com.xcorp.springbootpractice.Model.Manufacture;

import java.util.List;

public interface ManufactureService {
    List<Manufacture> getAllManufactures();

    Manufacture createManufacture(Manufacture manufacture);

    Manufacture updateManufacture(Manufacture newManufacture) throws Exception;

    List<Manufacture> removeManufacture(String id) throws Exception;
}
