package birthdaycakeweb.backend.service;

import birthdaycakeweb.backend.dto.CreatePresetRequest;
import birthdaycakeweb.backend.dto.PresetResponse;
import birthdaycakeweb.backend.pojo.CakePreset;
import birthdaycakeweb.backend.pojo.Client;
import birthdaycakeweb.backend.repository.CakePresetRepository;
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
    private final ObjectMapper objectMapper;

    public PresetServiceImpl(ClientRepository clientRepository,
                             CakePresetRepository cakePresetRepository,
                             ObjectMapper objectMapper) {
        this.clientRepository = clientRepository;
        this.cakePresetRepository = cakePresetRepository;
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

        // 2) Validate shape/size
        validateShapeAndSize(req);

        // 3) Map DTO -> Entity
        CakePreset preset = new CakePreset();
        preset.setClientId(req.getClientId());
        preset.setNameText(req.getNameText());
        preset.setAgeNumber(req.getAgeNumber());

        // normalize shape
        String shape = req.getShape() == null ? null : req.getShape().trim().toLowerCase();
        preset.setShape(shape);

        preset.setSizeDiameter(req.getSizeDiameter());
        preset.setSizeW(req.getSizeW());
        preset.setSizeH(req.getSizeH());
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

    private void validateShapeAndSize(CreatePresetRequest req) {
        String shape = req.getShape() == null ? null : req.getShape().trim().toLowerCase();
        if (shape == null || shape.isEmpty()) {
            throw new IllegalArgumentException("Shape is required");
        }
        if ("round".equals(shape)) {
            if (req.getSizeDiameter() == null || req.getSizeDiameter() <= 0) {
                throw new IllegalArgumentException("sizeDiameter is required and must be positive for round shape");
            }
        } else if ("square".equals(shape)) {
            if (req.getSizeW() == null || req.getSizeW() <= 0 ||
                    req.getSizeH() == null || req.getSizeH() <= 0) {
                throw new IllegalArgumentException("sizeW and sizeH are required and must be positive for square shape");
            }
        } else {
            throw new IllegalArgumentException("Invalid shape. Must be 'round' or 'square'");
        }
    }

    private PresetResponse mapToResponse(CakePreset preset) {
        PresetResponse res = new PresetResponse();
        res.setId(preset.getId());
        res.setClientId(preset.getClientId());
        res.setNameText(preset.getNameText());
        res.setAgeNumber(preset.getAgeNumber());
        res.setShape(preset.getShape());
        res.setSizeDiameter(preset.getSizeDiameter());
        res.setSizeW(preset.getSizeW());
        res.setSizeH(preset.getSizeH());
        res.setFlavor(preset.getFlavor());
        res.setCandlesCount(preset.getCandlesCount());

        // trả thẳng JsonNode (Jackson tự serialize ra JSON)
        res.setToppings(preset.getToppings());

        return res;
    }
}
