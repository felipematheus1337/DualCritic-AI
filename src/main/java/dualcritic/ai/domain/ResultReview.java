package dualcritic.ai.domain;

import dualcritic.ai.domain.enumerations.MovieStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultReview {

    private Integer rating;
    private String about;
    private MovieStatus status;
}
