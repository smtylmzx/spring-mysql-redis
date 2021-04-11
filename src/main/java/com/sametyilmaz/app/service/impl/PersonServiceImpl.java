package com.sametyilmaz.app.service.impl;

import com.sametyilmaz.app.dto.PersonDTO;
import com.sametyilmaz.app.entity.Address;
import com.sametyilmaz.app.entity.Person;
import com.sametyilmaz.app.repo.AddressRepository;
import com.sametyilmaz.app.repo.PersonRepository;
import com.sametyilmaz.app.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    @Override
    public PersonDTO save(PersonDTO personDTO) {
        Assert.notNull(personDTO.getEmail(), "Email alanı boş olamaz.");

        Person person = new Person();
        person.setName(personDTO.getName());
        person.setEmail(personDTO.getEmail());

        final Person personDb = personRepository.save(person);
        List<Address> addressList = new ArrayList<>();

        personDTO.getAddresses().forEach(item -> {
            Address address = new Address();
            address.setAddress(item);
            address.setPerson(person);

            addressList.add(address);

        });

        addressRepository.saveAll(addressList);

        personDTO.setId(personDb.getId());

        return personDTO;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<PersonDTO> getAll() {
        List<Person> persons = personRepository.findAll();
        List<PersonDTO> personDTOs = new ArrayList<>();

        persons.forEach(item -> {
            PersonDTO personDTO = new PersonDTO();
            personDTO.setId(item.getId());
            personDTO.setName(item.getName());
            personDTO.setEmail(item.getEmail());
            personDTO.setAddresses(
                    item.getAddresses() != null
                            ? item.getAddresses().stream().map(Address::getAddress).collect(Collectors.toList())
                            : null
            );

            personDTOs.add(personDTO);
        });

        return personDTOs;
    }

    @Override
    public Page<PersonDTO> getAll(Pageable pageable) {
        return null;
    }
}
