package entity.embedded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
@Builder
public class Birthday {
    private LocalDate birthDate;

    public Long getAge() {
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }
}
