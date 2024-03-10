package io.umid.supportservice.mapper;

import io.umid.supportservice.dto.ApplicationResponse;
import io.umid.supportservice.model.Application;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApplicationMapper {

    ApplicationResponse mapToResponse(Application application);


}
