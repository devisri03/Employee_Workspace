package com.ls.EmployeeAddressApplication.services;

import com.ls.EmployeeAddressApplication.payloads.AddressDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AddressService {
    AddressDto createAddress(AddressDto addressDto);
    List<AddressDto> getAllAddresses();
    AddressDto getAddressById(Long id) throws RuntimeException;
    AddressDto updateAddressById(Long id,AddressDto addressDto) throws RuntimeException;
    void deleteAddressById(Long id) throws RuntimeException;
}
