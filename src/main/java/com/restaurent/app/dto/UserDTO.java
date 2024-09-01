package com.restaurent.app.dto;

import com.restaurent.app.enums.ROLE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Kanchana Kalansooriya
 * @since 8/13/2024
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private ROLE role;

}
