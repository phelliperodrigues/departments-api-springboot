package com.phellipe.departaments.dto;

import com.phellipe.departaments.model.enums.BoardDirector;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentsDTO {

    private String  name;
    private String  region;
    private String  city;
    private String  state;
    private BoardDirector boardDirector;

}
