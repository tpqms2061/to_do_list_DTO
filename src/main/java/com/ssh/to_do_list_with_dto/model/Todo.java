package com.ssh.to_do_list_with_dto.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.InvalidMediaTypeException;


@Data
@Builder
public class Todo {
    private Integer id;
    private String title;
    private Integer userId;
    private boolean completed;
}

