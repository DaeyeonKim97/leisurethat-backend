package com.steady.leisurethatapi.database.repository;

import com.steady.leisurethatapi.database.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
}
