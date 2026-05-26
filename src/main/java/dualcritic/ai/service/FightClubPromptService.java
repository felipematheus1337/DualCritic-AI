package dualcritic.ai.service;

import dualcritic.ai.constants.PromptConstants;
import dualcritic.ai.domain.FinalReviewResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FightClubPromptService {

    private final ChatClient chatClient;
    private static final ChatOptions options = ChatOptions
            .builder()
            .model("gpt-5.2")
            .build();


    public FightClubPromptService(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem(PromptConstants.SYSTEM_TEMPLATE)
                .defaultOptions(options.mutate())
                .build();
    }

    public FinalReviewResult generateFinalReview(String film) {
        BeanOutputConverter<FinalReviewResult> converter = new BeanOutputConverter<>(FinalReviewResult.class);

        return chatClient
                .prompt()
                .user(film)
                .call()
                .entity(converter);
    }
}
