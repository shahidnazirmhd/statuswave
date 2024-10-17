package in.snm.statuswave.status;

import org.springframework.stereotype.Service;

import in.snm.statuswave.model.StatusCreationRequest;

@Service
public class StatusMapper {
    

    public Status toStatus(StatusCreationRequest statusCreationRequest) {
        return Status.builder()
                .subjectFirstName(statusCreationRequest.subjectFirstName())
                .subjectLastName(statusCreationRequest.subjectLastName())
                .subjectMobile(statusCreationRequest.subjectMobile())
                .subjectEmail(statusCreationRequest.subjectEmail())
                .subjectAddress(statusCreationRequest.subjectAddress())
                .workTitle(statusCreationRequest.workTitle())
                .workDescription(statusCreationRequest.workDescription())
                .workBy(statusCreationRequest.workBy())
                .workAssignedTo(statusCreationRequest.workAssignedTo())
                .workContact(statusCreationRequest.workContact())
                .workLocation(statusCreationRequest.workLocation())
                .expectedWorkCompletionDate(statusCreationRequest.expectedWorkCompletionDate())
                .expectedWorkComletionTime(statusCreationRequest.expectedWorkCompletionTime())
                .build();
    }
}
