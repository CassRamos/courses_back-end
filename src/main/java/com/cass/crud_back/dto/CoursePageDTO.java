package com.cass.crud_back.dto;

import java.util.List;

public record CoursePageDTO(List<CourseDTO> courseDTOList, long totalElements, int totalPages) {
}
