package com.example.summarifyai.controller;

import com.example.summarifyai.model.dto.ChatRequestDTO;
import com.example.summarifyai.model.dto.ChatResponseDTO;
import com.example.summarifyai.model.dto.SummaryHistoryResponseDTO;
import com.example.summarifyai.service.SummaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Summary", description = "Summary API")
public class SummaryController {

    private final SummaryService summaryService;

    @PostMapping("/summarize")
    @Operation(summary = "Summarize text", description = "Summarizes the provided text using AI")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully summarized text"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ChatResponseDTO> summarize(@Valid @RequestBody ChatRequestDTO requestDTO) {
        ChatResponseDTO response = summaryService.summarize(requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history/{id}")
    @Operation(summary = "Get summary history by ID", description = "Retrieves a specific summary history by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved summary history"),
            @ApiResponse(responseCode = "404", description = "Summary history not found"),
            @ApiResponse(responseCode = "400", description = "Invalid ID format")
    })
    public ResponseEntity<SummaryHistoryResponseDTO> getSummaryHistoryById(@PathVariable String id) {
        SummaryHistoryResponseDTO response = summaryService.getSummaryHistoryById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history")
    @Operation(summary = "Get all summary histories", description = "Retrieves all summary histories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all summary histories")
    })
    public ResponseEntity<List<SummaryHistoryResponseDTO>> getAllSummaryHistories() {
        List<SummaryHistoryResponseDTO> response = summaryService.getAllSummaryHistories();
        return ResponseEntity.ok(response);
    }
}
