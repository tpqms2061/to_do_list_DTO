package com.ssh.to_do_list_with_dto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoDto {
    private Integer id;

    @NotBlank(message = "제목을 입력하세요")
    public String title;
    private boolean completed;
}