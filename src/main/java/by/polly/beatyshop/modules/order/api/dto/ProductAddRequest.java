package by.polly.beatyshop.modules.order.api.dto;

import java.time.Instant;

public record ProductAddRequest(

        String fio,

        String number,

        Long productId,

        Instant startDate,

        Instant endDate

) {
}
