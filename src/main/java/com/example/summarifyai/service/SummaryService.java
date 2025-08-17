package com.example.summarifyai.service;

import com.example.summarifyai.model.dto.ChatRequestDTO;
import com.example.summarifyai.model.dto.ChatResponseDTO;
import com.example.summarifyai.model.dto.SummaryHistoryResponseDTO;

import java.util.List;
import java.util.UUID;

public interface SummaryService {
    ChatResponseDTO summarize(ChatRequestDTO requestDTO);

    SummaryHistoryResponseDTO getSummaryHistoryById(String id);

    List<SummaryHistoryResponseDTO> getAllSummaryHistories();
}
