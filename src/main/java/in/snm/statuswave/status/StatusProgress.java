package in.snm.statuswave.status;

import in.snm.statuswave.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StatusProgress extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String progressName;
    private String note;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
}
