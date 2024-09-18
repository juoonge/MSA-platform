package com.sparta.delivery_service.infrastructure;

import com.sparta.delivery_service.application.dto.hubdto.HubDTO;
import com.sparta.delivery_service.application.dto.hubdto.HubPathDTO;
import java.util.UUID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface HubService {
    HubDTO getHub(@PathVariable("hubId") UUID hubId);

    HubPathDTO CreateHubPath(@RequestBody HubPathDTO hubPathDTO);
}
