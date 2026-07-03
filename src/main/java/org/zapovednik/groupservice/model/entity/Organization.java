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
    @Table(name = "organizations")
    @Data
    @EqualsAndHashCode(callSuper = true)
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class Organization extends BaseEntity {

        @Column(name = "organization_name", unique = true, nullable = false, length = 50)
        private String organizationName;
    }