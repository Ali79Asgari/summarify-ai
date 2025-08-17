package com.example.summarifyai.provider;

import com.example.summarifyai.exception.OpenAIException;
import com.example.summarifyai.model.dto.ChatRequestDTO;
import com.example.summarifyai.model.dto.ChatResponseDTO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component("openAISummarizer")
public class OpenAISummarizerImpl implements AISummarizer {

    private final ChatClient chatClient;

    public OpenAISummarizerImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public ChatResponseDTO summarize(ChatRequestDTO requestDTO) {
        try {
            String prompt = "Please summarize the following text in a concise manner while preserving the key points:\n\n" + requestDTO.getText();
            String response = chatClient.prompt(prompt).call().content();
            return ChatResponseDTO.builder()
                    .summary(response)
                    .build();
        } catch (Exception e) {
            throw new OpenAIException("Failed to summarize text: " + e.getMessage(), e);
        }
    }
}
