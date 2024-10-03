package by.polly.beatyshop.modules.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

public record User(
        Long id,

        String name,

        String surname,

        String email,

        @JsonProperty(access = WRITE_ONLY)
        String phoneNumber,

        String password
) {
}
