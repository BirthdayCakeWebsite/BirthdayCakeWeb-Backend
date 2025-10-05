package birthdaycakeweb.backend.dto;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.UUID;

public class PresetResponse {

    private UUID id;
    private String clientId;

    private String nameText;
    private Integer ageNumber;

    /** Tham chiếu template */
    private String templateId;    // R1/R2/R3/S1/S2/S3
    private String templateShape; // "round" | "square"
    private Integer templateTiers; // 1 | 2 | 3

    private String flavor;
    private JsonNode toppings; // trả thẳng JSON
    private Integer candlesCount;

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getNameText() { return nameText; }
    public void setNameText(String nameText) { this.nameText = nameText; }

    public Integer getAgeNumber() { return ageNumber; }
    public void setAgeNumber(Integer ageNumber) { this.ageNumber = ageNumber; }

    public String getTemplateId() { return templateId; }
    public void setTemplateId(String templateId) { this.templateId = templateId; }

    public String getTemplateShape() { return templateShape; }
    public void setTemplateShape(String templateShape) { this.templateShape = templateShape; }

    public Integer getTemplateTiers() { return templateTiers; }
    public void setTemplateTiers(Integer templateTiers) { this.templateTiers = templateTiers; }

    public String getFlavor() { return flavor; }
    public void setFlavor(String flavor) { this.flavor = flavor; }

    public JsonNode getToppings() { return toppings; }
    public void setToppings(JsonNode toppings) { this.toppings = toppings; }

    public Integer getCandlesCount() { return candlesCount; }
    public void setCandlesCount(Integer candlesCount) { this.candlesCount = candlesCount; }
}
