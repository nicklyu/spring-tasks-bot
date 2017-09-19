package ru.nickly.bot.onenotemodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetOneNoteEntities {
    private List<OneNoteEntity> value;
}
