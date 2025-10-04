package birthdaycakeweb.backend.service;

import birthdaycakeweb.backend.dto.CreatePresetRequest;
import birthdaycakeweb.backend.dto.PresetResponse;

import java.util.List;
import java.util.UUID;

public interface PresetService {
    UUID createPreset(CreatePresetRequest req);
    List<PresetResponse> listByClient(String clientId);

    PresetResponse getById(UUID id);
}
