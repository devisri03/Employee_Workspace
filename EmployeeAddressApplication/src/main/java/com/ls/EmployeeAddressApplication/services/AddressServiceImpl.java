package com.ls.EmployeeAddressApplication.services;

import com.ls.EmployeeAddressApplication.entities.Address;
import com.ls.EmployeeAddressApplication.enums.EMessage;
import com.ls.EmployeeAddressApplication.payloads.AddressDto;
import com.ls.EmployeeAddressApplication.repos.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AddressServiceImpl implements AddressService {

    private ModelMapper mapper;
    private AddressRepository repository;

    @Autowired
    public AddressServiceImpl(ModelMapper mapper, AddressRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }
    @Override
    public AddressDto createAddress(AddressDto addressDto) {
        Address address = mapToEntity(addressDto);
        Address savedAddress = repository.save(address);
        return mapToDto(savedAddress);
    }

    @Override
    public List<AddressDto> getAllAddresses() {
        List<Address> addresses = repository.findAll();
        return addresses.stream().map(address -> mapToDto(address)).collect(Collectors.toList());
    }

    @Override
    public AddressDto getAddressById(Long id) throws RuntimeException {
        Address address = repository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(EMessage.ID_NOT_FOUND)));
        return mapToDto(address);
    }

    @Override
    public AddressDto updateAddressById(Long id, AddressDto addressDto) throws RuntimeException {
        Address address = repository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(EMessage.ID_NOT_FOUND)));
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setCountry(addressDto.getCountry());
        Address savedAddress = repository.save(address);
        return mapToDto(savedAddress);
    }

    @Override
    public void deleteAddressById(Long id) throws RuntimeException {
        Address address = repository.findById(id).orElseThrow(() -> new RuntimeException(String.valueOf(EMessage.ID_NOT_FOUND)));
        repository.delete(address);
    }
    private AddressDto mapToDto(Address address){
        return mapper.map(address,AddressDto.class);
    }

    private Address mapToEntity(AddressDto addressDto){
        return mapper.map(addressDto,Address.class);
    }
}
