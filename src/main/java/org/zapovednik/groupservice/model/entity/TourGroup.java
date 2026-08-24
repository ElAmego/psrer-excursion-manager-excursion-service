package org.zapovednik.groupservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.zapovednik.groupservice.model.entity.type.TourGroupStatus;

@Entity
@Table(name = "tour_groups")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourGroup extends BaseEntity {

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TourGroupStatus status;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "is_legal", nullable = false)
    private Boolean isLegal;

    @JoinColumn(name = "organization_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;

    @Column(name = "customer_name", length = 50)
    private String customerName;

    @Column(name = "contact_data", nullable = false, length = 50)
    private String contactData;

    @JoinColumn(name = "responsible_specialist_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ResponsibleSpecialist responsibleSpecialist;

    @JoinColumn(name = "route_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;

    @JoinColumn(name = "driver_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "export", precision = 10, scale = 2)
    private BigDecimal export;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Column(name = "contract_date")
    private LocalDate contractDate;

    @Column(name = "contract_number")
    private Integer contractNumber;

    @Column(name = "is_documents_submitted")
    private Boolean isDocumentsSubmitted;
}