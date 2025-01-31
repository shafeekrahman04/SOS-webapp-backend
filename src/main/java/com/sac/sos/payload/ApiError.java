package com.sac.sos.payload;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApiError {
    private int code;
    private String message;
}
