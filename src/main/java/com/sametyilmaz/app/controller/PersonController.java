package com.sametyilmaz.app.controller;

import com.sametyilmaz.app.dto.PersonDTO;
import com.sametyilmaz.app.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDTO> create(@Validated @RequestBody PersonDTO personDTO){
        return ResponseEntity.ok(personService.save(personDTO));
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> list(){
        return ResponseEntity.ok(personService.getAll());
    }
}
