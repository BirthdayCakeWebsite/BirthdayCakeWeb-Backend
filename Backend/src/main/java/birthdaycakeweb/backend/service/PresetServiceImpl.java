package birthdaycakeweb.backend.service;

import birthdaycakeweb.backend.dto.CreatePresetRequest;
import birthdaycakeweb.backend.dto.PresetResponse;
import birthdaycakeweb.backend.pojo.CakePreset;
import birthdaycakeweb.backend.pojo.CakeTemplate;
import birthdaycakeweb.backend.pojo.Client;
import birthdaycakeweb.backend.repository.CakePresetRepository;
import birthdaycakeweb.backend.repository.CakeTemplateRepository;
import birthdaycakeweb.backend.repository.ClientRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PresetServiceImpl implements PresetService {

    private final ClientRepository clientRepository;
    private final CakePresetRepository cakePresetRepository;
    private final CakeTemplateRepository cakeTemplateRepository;
    private final ObjectMapper objectMapper;

    public PresetServiceImpl(ClientRepository clientRepository,
                             CakePresetRepository cakePresetRepository,
                             CakeTemplateRepository cakeTemplateRepository,
                             ObjectMapper objectMapper) {
        this.clientRepository = clientRepository;
        this.cakePresetRepository = cakePresetRepository;
        this.cakeTemplateRepository = cakeTemplateRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional
    public UUID createPreset(CreatePresetRequest req) {
        // 1) Upsert client
        if (!clientRepository.existsById(req.getClientId())) {
            Client client = new Client();
            client.setClientId(req.getClientId());
            clientRepository.save(client);
        }

        // 2) Validate template
        if (req.getTemplateId() == null || req.getTemplateId().isBlank()) {
            throw new IllegalArgumentException("templateId is required");
        }
        CakeTemplate template = cakeTemplateRepository.findById(req.getTemplateId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid templateId: " + req.getTemplateId()));

        // 3) Map DTO -> Entity
        CakePreset preset = new CakePreset();
        preset.setClientId(req.getClientId());
        preset.setNameText(req.getNameText());
        preset.setAgeNumber(req.getAgeNumber());
        preset.setTemplate(template);
        preset.setFlavor(req.getFlavor());

        // toppings: Object/Map/List -> JsonNode (null-safe)
        JsonNode toppingsNode = (req.getToppings() == null)
                ? null
                : objectMapper.valueToTree(req.getToppings());
        preset.setToppings(toppingsNode);

        // candlesCount normalize
        Integer candlesCount = req.getCandlesCount();
        if (candlesCount == null && req.getAgeNumber() != null) {
            candlesCount = req.getAgeNumber();
        }
        if (candlesCount != null) {
            candlesCount = Math.max(0, Math.min(candlesCount, 50));
        }
        preset.setCandlesCount(candlesCount);

        // 4) Save & return id
        CakePreset saved = cakePresetRepository.save(preset);
        return saved.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PresetResponse> listByClient(String clientId) {
        return cakePresetRepository.findByClientIdOrderByCreatedAtDesc(clientId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PresetResponse getById(UUID id) {
        CakePreset preset = cakePresetRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Preset not found with id: " + id));
        return mapToResponse(preset);
    }

    private PresetResponse mapToResponse(CakePreset preset) {
        PresetResponse res = new PresetResponse();
        res.setId(preset.getId());
        res.setClientId(preset.getClientId());
        res.setNameText(preset.getNameText());
        res.setAgeNumber(preset.getAgeNumber());

        if (preset.getTemplate() != null) {
            res.setTemplateId(preset.getTemplate().getId());
            res.setTemplateShape(preset.getTemplate().getShape());
            res.setTemplateTiers(preset.getTemplate().getTiers());
        }

        res.setFlavor(preset.getFlavor());
        res.setCandlesCount(preset.getCandlesCount());
        res.setToppings(preset.getToppings());
        return res;
    }
}
