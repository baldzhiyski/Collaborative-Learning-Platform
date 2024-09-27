package com.softuni.client.domain.dto.user;

import com.softuni.client.domain.dto.role.RoleDto;
import com.softuni.client.domain.entity.Role;
import com.softuni.client.validation.annotations.*;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatch
public class RegisterDto {

    @NotBlank(message = "{field.required}")
    @UniqueUsername
    private String username;

    private UUID uuid;

    @NotBlank(message = "{field.required}")
    @ValidEmail
    private String email;

    @PasswordAnnotation
    private String password;

    private String confirmPassword;

    @ValidFile
    private MultipartFile profilePicture;

    private Boolean subscribed;

    @NotBlank(message = "{field.required}")
    private String firstName;

    @NotBlank(message = "{field.required}")
    private String lastName;

}
