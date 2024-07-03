package com.codevillain.backend.hello.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Hello {
    @JsonProperty(value="id", required=true, index = 1, defaultValue = "1")
    private final long id;
    @JsonProperty(value="content", required=true, index = 2, defaultValue = "World")
    private final String content;

    public Hello(long id, String content) {
        this.id = id;
        this.content = content;
    }

}
