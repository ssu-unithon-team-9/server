package com.example.remember.report.repository;

import com.example.remember.report.domain.Report;
import com.example.remember.report.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportJpaRepository extends JpaRepository<ReportEntity, Long> {
    Optional<ReportEntity> findByUserId(Long userId);
}
