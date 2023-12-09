package tat_tickets.dao;

import tat_tickets.models.FileInfo;

import java.util.Optional;

public interface FileInfoRepository extends CrudRepository<FileInfo, Integer> {
    Optional<FileInfo> findByOriginalFileName(String originalFileName);
    Optional<FileInfo> findById(Integer fileInfoId);
}
