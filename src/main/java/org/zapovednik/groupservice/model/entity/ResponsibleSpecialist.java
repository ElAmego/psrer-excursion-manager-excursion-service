package org.zapovednik.groupservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "responsible_specialists")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsibleSpecialist extends BaseEntity {

    @Column(name = "responsible_specialist_name", unique = true, nullable = false, length = 50)
    private String responsibleSpecialistName;

    @Column(name = "responsible_specialist_phone_number", unique = true, nullable = false, length = 9)
    private String responsibleSpecialistPhoneNumber;
}