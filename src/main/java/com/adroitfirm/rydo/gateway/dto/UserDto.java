package com.adroitfirm.rydo.gateway.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.Builder.Default;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;

    @NotBlank(message = "Username should not be blank")
    @Size(max = 100)
    private String name;

    @Email
//    @NotBlank
    @Size(max = 100)
    private String email;

//    @NotBlank
    @Size(max = 15)
    private String phone;

    @Size(max = 255)
    private String password;

    @Default
    @NotBlank
    @Size(max = 20)
    private String role = "CUSTOMER";

    @Size(max = 255)
    private String profileImageUrl;

    @Default
    private Boolean isActive = true;

    @Size(max = 100)
    private String deviceId;
}