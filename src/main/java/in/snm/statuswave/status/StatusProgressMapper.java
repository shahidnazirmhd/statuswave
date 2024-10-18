package in.snm.statuswave.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StatusProgressMapper {
    

    public List<StatusProgress> toStatusProgressList(Long statusId, List<String> progressNames, List<String> notes) {
    List<StatusProgress> statusProgressList = new ArrayList<>();

    for (int i = 0; i < progressNames.size(); i++) {
        StatusProgress statusProgress =  StatusProgress.builder()
                        .progressName(progressNames.get(i))
                        .note(notes.get(i))
                        .status(Status.builder().id(statusId).build())
                        .isStarted(false)
                        .isOnHold(false)
                        .isOnWork(false)
                        .isCompleted(false)
                        .build();
        statusProgressList.add(statusProgress);
    }
    
    return statusProgressList;
}

}
