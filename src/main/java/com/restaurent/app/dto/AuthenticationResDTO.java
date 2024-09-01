package com.restaurent.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Kanchana Kalansooriya
 * @since 8/17/2024
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResDTO {
    String username;
    String role;
    String firstName;
    String lastName;


}
