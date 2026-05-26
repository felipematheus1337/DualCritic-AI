package dualcritic.ai.service;

import dualcritic.ai.constants.PromptConstants;
import dualcritic.ai.domain.FinalReviewResult;
import dualcritic.ai.domain.ResultReview;
import dualcritic.ai.entity.FinalReviewResultEntity;
import dualcritic.ai.entity.ResultReviewEntity;
import dualcritic.ai.repository.FinalReviewResultEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class FightClubPromptService {

    private final ChatClient chatClient;
    private final FinalReviewResultEntityRepository repository;
    private static final ChatOptions options = ChatOptions
            .builder()
            .model("gpt-5.2")
            .temperature(.99)
            .topP(.95)
            .build();


    public FightClubPromptService(ChatClient.Builder builder, FinalReviewResultEntityRepository repository) {
        this.chatClient = builder
                .defaultSystem(PromptConstants.SYSTEM_TEMPLATE)
                .defaultOptions(options.mutate())
                .build();
        this.repository = repository;
    }

    @Transactional
    @Cacheable("filmes")
    public FinalReviewResult generateFinalReview(String film) {
        BeanOutputConverter<FinalReviewResult> converter = new BeanOutputConverter<>(FinalReviewResult.class);

        FinalReviewResult result = chatClient
                .prompt()
                .user(film)
                .call()
                .entity(converter);

        FinalReviewResultEntity entity = toEntity(result);
        entity.setMovieName(film);

        repository.save(entity);

        return result;

    }

    private FinalReviewResultEntity toEntity(FinalReviewResult result) {
        return FinalReviewResultEntity
                .builder()
                .scriptCritic(toReviewResult(result.getScriptCritic()))
                .durdenCritic(toReviewResult(result.getDurdenCritic()))
                .narratorCritic(toReviewResult(result.getNarratorCritic()))
                .build();
    }

    private ResultReviewEntity toReviewResult(ResultReview scriptCritic) {
        return ResultReviewEntity
                .builder()
                .about(scriptCritic.getAbout())
                .status(scriptCritic.getStatus())
                .rating(scriptCritic.getRating())
                .build();
    }
}
