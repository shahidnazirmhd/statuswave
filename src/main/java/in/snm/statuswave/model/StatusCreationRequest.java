package in.snm.statuswave.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import in.snm.statuswave.custom.NotBlankElements;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record StatusCreationRequest(
    @NotBlank(message = "First name cannot be blank") 
    String subjectFirstName,
    
    @NotBlank(message = "Last name cannot be blank") 
    String subjectLastName,
    
    @NotBlank(message = "Mobile number cannot be blank") 
    @Size(min = 10, max = 15, message = "Mobile number must be between 10 and 15 characters")
    String subjectMobile,
    
    @NotBlank(message = "Email cannot be blank") 
    @Email(message = "Invalid email format") 
    String subjectEmail,
    
    @NotBlank(message = "Address cannot be blank") 
    String subjectAddress,
    
    @NotBlank(message = "Work title cannot be blank") 
    String workTitle,
    
    @NotBlank(message = "Work description cannot be blank") 
    String workDescription,
    
    @NotBlank(message = "Work by cannot be blank") 
    String workBy,
    
    @NotBlank(message = "Work assigned to cannot be blank") 
    String workAssignedTo,
    
    @NotBlank(message = "Work contact cannot be blank") 
    String workContact,
    
    @NotBlank(message = "Work location cannot be blank") 
    String workLocation,
    
    @NotNull(message = "Expected work completion date cannot be null") 
    LocalDate expectedWorkCompletionDate,
    
    @NotNull(message = "Expected work completion time cannot be null") 
    LocalTime expectedWorkComletionTime,
    
    @NotNull(message = "Progress name list cannot be null") 
    @Size(min = 2, message = "Progress name list must have at least 2 elements")
    @NotBlankElements
    List<String> progressName,
    
    @NotNull(message = "Note list cannot be null") 
    @Size(min = 2, message = "Note list must have at least 2 elements")
    @NotBlankElements
    List<String> note
) {
    
}
