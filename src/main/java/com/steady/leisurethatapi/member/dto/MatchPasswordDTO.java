package com.steady.leisurethatapi.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchPasswordDTO {
    private String name;
    private String username;
    private String email;
}
