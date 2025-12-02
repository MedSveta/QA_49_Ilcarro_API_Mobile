package dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class BookedDto {
    private String email;
    private String startDate;
    private String endDate;

}
