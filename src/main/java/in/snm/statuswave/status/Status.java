package in.snm.statuswave.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import in.snm.statuswave.common.BaseEntity;
import in.snm.statuswave.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Status extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String subjectFirstName;
    private String subjectLastName;
    private String subjectMobile;
    private String subjectEmail;
    private String subjectAddress;
    private String workTitle;
    private String workDescription;
    private String workBy;
    private String workAssignedTo;
    private String workContact;
    private String workLocation;
    private LocalDate expectedWorkCompletionDate;
    private LocalTime expectedWorkComletionTime;
    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER)
    private List<StatusProgress> progresses;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;
}
