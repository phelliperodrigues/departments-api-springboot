package com.phellipe.departaments.dto;

import com.phellipe.departaments.model.enums.BoardDirector;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentsDTO {

    private Long id;
    @NotEmpty(message = "Name is required")
    private String  name;

    @NotEmpty(message = "Region is required")
    private String  region;

    @NotEmpty(message = "City is required")
    private String  city;

    @NotEmpty(message = "State is required")
    private String  state;

    @NotNull(message = "Board directors is required")
    private BoardDirector boardDirector;

}
