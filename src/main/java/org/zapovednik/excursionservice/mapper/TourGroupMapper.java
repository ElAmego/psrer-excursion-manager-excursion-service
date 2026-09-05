package org.zapovednik.excursionservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.zapovednik.excursionservice.dto.request.TourGroupExpectationRequestDto;
import org.zapovednik.excursionservice.dto.response.TourGroupExpectationResponseDto;
import org.zapovednik.excursionservice.dto.response.TourGroupResponseDto;
import org.zapovednik.excursionservice.model.entity.TourGroup;

@Mapper(componentModel = "spring")
public interface TourGroupMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)  // устанавливается в сервисе
    @Mapping(target = "organization", ignore = true)
    @Mapping(target = "route", ignore = true)
    @Mapping(target = "responsibleSpecialist", ignore = true)
    @Mapping(target = "driver", ignore = true)
    @Mapping(target = "groupCountries", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "export", ignore = true)
    @Mapping(target = "isPaid", ignore = true)
    @Mapping(target = "contractDate", ignore = true)
    @Mapping(target = "contractNumber", ignore = true)
    @Mapping(target = "isDocumentsSubmitted", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    TourGroup toCreateEntity(TourGroupExpectationRequestDto request);

    @Mapping(target = "organizationId", source = "organization.id")
    @Mapping(target = "organizationName", source = "organization.organizationName")
    @Mapping(target = "founderName", source = "organization.founderName")
    @Mapping(target = "routeId", source = "route.id")
    @Mapping(target = "routeName", source = "route.routeName")
    @Mapping(target = "routeNum", source = "route.routeNum")
    TourGroupExpectationResponseDto toCreateDto(TourGroup tourGroup);

    @Mapping(target = "organizationId", source = "organization.id")
    @Mapping(target = "organizationName", source = "organization.organizationName")
    @Mapping(target = "founderName", source = "organization.founderName")
    @Mapping(target = "driverId", source = "driver.id")
    @Mapping(target = "driverName", source = "driver.driverName")
    @Mapping(target = "responsibleSpecialistId", source = "responsibleSpecialist.id")
    @Mapping(target = "responsibleSpecialistName", source = "responsibleSpecialist.responsibleSpecialistName")
    @Mapping(target = "routeId", source = "route.id")
    @Mapping(target = "routeName", source = "route.routeName")
    @Mapping(target = "routeNum", source = "route.routeNum")
    @Mapping(target = "countries", source = "groupCountries")
    TourGroupResponseDto toDto(TourGroup tourGroup);
}