package com.example.summarifyai.model.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SummaryHistoryResponseDTO {
    private String id;
    private String inputText;
    private String summaryText;
    private String createdAt;
    private String updatedAt;
}
