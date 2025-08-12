package com.example.remember.report.entity;

import com.example.remember.report.domain.Report;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String date;

    @Embedded
    private Total total;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Detail> detail;

    @Column(columnDefinition = "TEXT")
    private String advice;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Total {
        private double score;
        private String danger;
        private String goal;

        @ElementCollection
        @CollectionTable(name = "report_total_weak", joinColumns = @JoinColumn(name = "report_id"))
        @Column(name = "weak")
        private List<String> weak;

        @ElementCollection
        @CollectionTable(name = "report_total_summary", joinColumns = @JoinColumn(name = "report_id"))
        @Column(name = "summary")
        private List<String> summary;
    }

    @Entity
    @Table(name = "report_detail")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Detail {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "type")
        private String type;

        private int score;
        private int scoreDifference;
        private int location;
        private int danger;

        @Column(name = "danger_level")
        private String dangerLevel;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "report_id", nullable = false)
        private ReportEntity report;
    }

    public void addDetail(Detail d) {
        if (this.detail == null) this.detail = new java.util.ArrayList<>();
        this.detail.add(d);
        d.setReport(this); // ★ FK 소유자 동기화 (report_id 세팅)
    }

    public static Report toDomain(ReportEntity entity) {
        if (entity == null) {
            return null;
        }

        return Report.builder()
                .userId(entity.getUserId())
                .date(entity.getDate())
                .total(Report.Total.builder()
                        .score(entity.getTotal().getScore())
                        .danger(entity.getTotal().getDanger())
                        .weak(entity.getTotal().getWeak())
                        .summary(entity.getTotal().getSummary())
                        .build()
                )
                .detail(
                        entity.getDetail() != null
                                ? entity.getDetail().stream()
                                .map(d -> Report.Detail.builder()
                                        .type(d.getType())
                                        .score(d.getScore())
                                        .scoreDifference(d.getScoreDifference())
                                        .location(d.getLocation())
                                        .danger(d.getDanger())
                                        .danger_level(d.getDangerLevel())
                                        .build()
                                )
                                .toList()
                                : List.of()
                )
                .advice(entity.getAdvice())
                .build();
    }
}
