package com.example.ajax.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board extends Page {

    private Long boardId;
    private String name;
    private String gender;
    private String age;
    private String phone;
    private String blindYn;

}
