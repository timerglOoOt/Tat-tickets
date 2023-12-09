<<<<<<< HEAD
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
=======
    package tat_tickets.models;

    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class FileInfo {
        private Integer id;
        private String originalFileName;
        private String storageFileName;
        private Long size;
        private String type;
    }
>>>>>>> d23f224 (feat: add done project)
