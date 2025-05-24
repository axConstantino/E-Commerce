package com.axconstantino.profile.infrastructure.mapper;

import com.axconstantino.profile.domain.entities.UserProfile;
import com.axconstantino.profile.infrastructure.persistence.jpa.entities.UserProfileEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring")
public interface UserProfileJpaMapper {
    UserProfileEntity toEntity(UserProfile profile);
    UserProfile toDomain(UserProfileEntity profileEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget UserProfileEntity userProfileEntity, UserProfile userProfile);
}
