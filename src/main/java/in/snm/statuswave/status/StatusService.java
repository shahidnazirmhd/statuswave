package in.snm.statuswave.status;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.snm.statuswave.model.StatusCreationRequest;
import in.snm.statuswave.user.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatusService {
    private final StatusMapper statusMapper;
    private final StatusProgressMapper statusProgressMapper;
    private final StatusRepository statusRepository;
    private final StatusProgressRepository statusProgressRepository;

    @Transactional
    public Long saveStatus(StatusCreationRequest statusCreationRequest, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Status status = statusMapper.toStatus(statusCreationRequest);
        status.setCreator(user);
        Long statusId = statusRepository.save(status).getId();
        List<StatusProgress> statusProgressList = statusProgressMapper.toStatusProgressList(statusId, statusCreationRequest.progressName(), statusCreationRequest.note());
        statusProgressRepository.saveAll(statusProgressList);
        return statusId;
    }

    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }
}
