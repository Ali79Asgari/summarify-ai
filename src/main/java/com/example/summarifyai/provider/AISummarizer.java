package com.example.summarifyai.provider;

import com.example.summarifyai.model.dto.ChatRequestDTO;
import com.example.summarifyai.model.dto.ChatResponseDTO;

public interface AISummarizer {
    ChatResponseDTO summarize(ChatRequestDTO requestDTO);
}
