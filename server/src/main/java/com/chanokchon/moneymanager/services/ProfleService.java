package com.chanokchon.moneymanager.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.chanokchon.moneymanager.dtos.ProfileDTO;
import com.chanokchon.moneymanager.entities.ProfileEntity;
import com.chanokchon.moneymanager.repositories.ProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfleService {

    private ProfileRepository profileRepository;

    public ProfileDTO registerProfile(ProfileDTO profileDTO) {
        ProfileEntity newProfile = this.toEntity(profileDTO);

        newProfile.setActivitionToken(UUID.randomUUID().toString());

        newProfile = this.profileRepository.save(newProfile);

        return this.toDTO(newProfile);
    }

    public ProfileEntity toEntity(ProfileDTO profileDTO) {
        return ProfileEntity
            .builder()
            .id(profileDTO.getId())
            .fullName(profileDTO.getFullName())
            .email(profileDTO.getEmail())
            .password(profileDTO.getPassword())
            .profileImageUrl(profileDTO.getProfileImageUrl())
            .createdAt(profileDTO.getCreatedAt())
            .updatedAt(profileDTO.getUpdatedAt())
            .build();
    }

    public ProfileDTO toDTO(ProfileEntity profileEntity) {
        return ProfileDTO
            .builder()
            .id(profileEntity.getId())
            .fullName(profileEntity.getFullName())
            .email(profileEntity.getEmail())
            .profileImageUrl(profileEntity.getProfileImageUrl())
            .createdAt(profileEntity.getCreatedAt())
            .updatedAt(profileEntity.getUpdatedAt())
            .build();
    }
}
