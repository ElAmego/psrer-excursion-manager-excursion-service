package org.zapovednik.groupservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.zapovednik.groupservice.dto.request.GroupCountryRequestDto;
import org.zapovednik.groupservice.dto.response.GroupCountryResponseDto;
import org.zapovednik.groupservice.model.entity.GroupCountry;

@Mapper(componentModel = "spring")
public interface GroupCountryMapper {

    @Mapping(target = "country", ignore = true)
    GroupCountry toEntity(final GroupCountryRequestDto requestDto);

    @Mapping(target = "countryId", source = "country.id")
    @Mapping(target = "twoLetterCountryCode", source = "country.twoLetterCountryCode")
    @Mapping(target = "countryName", source = "country.countryName")
    GroupCountryResponseDto toDto(final GroupCountry groupCountry);
}