package com.app.todo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminViewNoteResponse {
    private String title;
    private String content;

    @JsonProperty("public")
    private boolean isPublic;
    private String createdBy;
}
