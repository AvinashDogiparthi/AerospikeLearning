package com.learning.aerospike.mapper;

import com.learning.aerospike.model.UserRequest;
import com.learning.aerospike.repository.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public com.learning.aerospike.repository.User toEntity(UserRequest request) {
        if (request == null) {
            return null;
        }

        return new com.learning.aerospike.repository.User(
            request.getId(),
            request.getName(),
            request.getEmail(),
            request.getDepartment(),
            request.getExperience()
        );
    }


    public UserRequest toDto(com.learning.aerospike.repository.User entity) {
        if (entity == null) {
            return null;
        }

        UserRequest dto = new UserRequest();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setDepartment(entity.getDepartment());
        dto.setExperience(entity.getExperience());
        return dto;
    }

    public List<UserRequest> toDtos(List<User> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}