package dualcritic.ai.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FinalReviewResult {

    private ResultReview scriptCritic;
    private ResultReview durdenCritic;
    private ResultReview narratorCritic;
}
