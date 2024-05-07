package com.ak0411.filmfolio.domain.dtos;

import com.ak0411.filmfolio.enums.Rating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {

    private Long id;

    private String text;

    private Rating rating;
}
