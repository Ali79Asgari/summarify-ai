package com.example.summarifyai.service;

import com.example.summarifyai.exception.ApiException;
import com.example.summarifyai.exception.ResourceNotFoundException;
import com.example.summarifyai.model.dto.ChatRequestDTO;
import com.example.summarifyai.model.dto.ChatResponseDTO;
import com.example.summarifyai.model.dto.SummaryHistoryResponseDTO;
import com.example.summarifyai.model.entity.SummaryHistory;
import com.example.summarifyai.provider.AISummarizer;
import com.example.summarifyai.repository.SummaryHistoryRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class SummaryServiceImpl implements SummaryService {

    private final AISummarizer aiSummarizer;
    private final SummaryHistoryRepository summaryHistoryRepository;

    public SummaryServiceImpl(
            @Qualifier("openAISummarizer") AISummarizer aiSummarizer,
            SummaryHistoryRepository summaryHistoryRepository
    ) {
        this.aiSummarizer = aiSummarizer;
        this.summaryHistoryRepository = summaryHistoryRepository;
    }

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public ChatResponseDTO summarize(ChatRequestDTO requestDTO) {
        if (requestDTO == null || requestDTO.getText() == null || requestDTO.getText().isEmpty()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Request text cannot be null or empty");
        }

        ChatResponseDTO responseDTO = aiSummarizer.summarize(requestDTO);

        if (responseDTO == null || responseDTO.getSummary() == null || responseDTO.getSummary().isEmpty()) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Summary cannot be null or empty");
        }

        try {
            SummaryHistory summaryHistory = SummaryHistory.builder()
                    .inputText(requestDTO.getText())
                    .summaryText(responseDTO.getSummary())
                    .build();

            summaryHistoryRepository.save(summaryHistory);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save summary history: " + e.getMessage(), e);
        }

        return responseDTO;
    }

    @Override
    public SummaryHistoryResponseDTO getSummaryHistoryById(String id) {
        try {
            UUID uuid = UUID.fromString(id);
            SummaryHistory summaryHistory = summaryHistoryRepository.findById(uuid)
                    .orElseThrow(() -> new ResourceNotFoundException("Summary history", "id", id));

            return mapToDTO(summaryHistory);
        } catch (IllegalArgumentException _) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid ID format: " + id);
        }
    }

    @Override
    public List<SummaryHistoryResponseDTO> getAllSummaryHistories() {
        return summaryHistoryRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    private SummaryHistoryResponseDTO mapToDTO(SummaryHistory entity) {
        return SummaryHistoryResponseDTO.builder()
                .id(entity.getId().toString())
                .inputText(entity.getInputText())
                .summaryText(entity.getSummaryText())
                .createdAt(entity.getCreatedAt() != null ? entity.getCreatedAt().format(DATE_FORMATTER) : null)
                .updatedAt(entity.getUpdatedAt() != null ? entity.getUpdatedAt().format(DATE_FORMATTER) : null)
                .build();
    }
}
