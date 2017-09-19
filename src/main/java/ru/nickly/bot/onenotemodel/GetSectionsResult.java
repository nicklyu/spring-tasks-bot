package ru.nickly.bot.onenotemodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSectionsResult {
    private List<Section> value;
}
