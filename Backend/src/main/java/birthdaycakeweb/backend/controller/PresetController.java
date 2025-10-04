package birthdaycakeweb.backend.controller;

import birthdaycakeweb.backend.dto.CreatePresetRequest;
import birthdaycakeweb.backend.dto.PresetResponse;
import birthdaycakeweb.backend.service.PresetService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/presets")
@Validated
public class PresetController {

    private final PresetService presetService;

    public PresetController(PresetService presetService) {
        this.presetService = presetService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public IdResponse create(@RequestBody @Valid CreatePresetRequest req) {
        UUID id = presetService.createPreset(req);
        return new IdResponse(id.toString());
    }

    @GetMapping(produces = "application/json")
    public List<PresetResponse> list(@RequestParam String clientId) {
        return presetService.listByClient(clientId);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public PresetResponse getOne(@PathVariable UUID id) {
        return presetService.getById(id);
    }

    // DTO nhỏ gọn để trả id
    public record IdResponse(String id) {}
}
