package tat_tickets.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileInfo {
    private Integer id;
    private String originalFileName;
    private String storageFileName;
    private Long size;
    private String type;
}
