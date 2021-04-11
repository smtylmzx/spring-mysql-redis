package com.sametyilmaz.app.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"})
public class PersonDTO {
    private Long id;

    private String name;

    private String email;

    private List<String> addresses;
}
