package com.ls.EmployeeAddressApplication.controller;

import com.ls.EmployeeAddressApplication.enums.EMessage;
import com.ls.EmployeeAddressApplication.payloads.AddressDto;
import com.ls.EmployeeAddressApplication.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class AddressController {
    @Autowired
    private AddressService service;

    @RequestMapping(value = "/address",method = RequestMethod.POST)
    public ResponseEntity<AddressDto> saveAddress(@RequestBody AddressDto addressDto){
        return new ResponseEntity<>(service.createAddress(addressDto), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/address",method = RequestMethod.GET)
    public ResponseEntity<List<AddressDto>> fetchAllAddresses(){
        return new ResponseEntity<>(service.getAllAddresses(),HttpStatus.OK);
    }

    @RequestMapping(value = "/address/{id}",method = RequestMethod.GET)
    public AddressDto fetchAddressById(@PathVariable Long id) throws RuntimeException{
        return service.getAddressById(id);
    }

    @RequestMapping(value = "/address/{id}",method = RequestMethod.PUT)
    public ResponseEntity<AddressDto> updateAddressById(@PathVariable Long id,@RequestBody AddressDto addressDto) throws RuntimeException{
        return new ResponseEntity<>(service.updateAddressById(id, addressDto),HttpStatus.CREATED);
    }

    @RequestMapping(value = "/address/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAddressById(@PathVariable Long id) throws RuntimeException{
        service.deleteAddressById(id);
        return new ResponseEntity<>(String.valueOf(EMessage.ADDRESS_DELETED_SUCCESSFULLY),HttpStatus.OK);
    }
}
