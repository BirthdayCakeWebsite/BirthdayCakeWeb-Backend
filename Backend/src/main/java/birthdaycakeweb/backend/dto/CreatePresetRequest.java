package birthdaycakeweb.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePresetRequest {

    @NotBlank
    private String clientId;

    /** Mã template: R1/R2/R3 (tròn 1-3 tầng), S1/S2/S3 (vuông 1-3 tầng) */
    @NotBlank
    private String templateId;

    private String nameText;

    @Min(0) @Max(120)
    private Integer ageNumber;

    private String flavor;

    /** FE gửi object/array, BE sẽ map sang JsonNode */
    private Object toppings;

    @Min(0) @Max(50)
    private Integer candlesCount;

    // Getters & Setters
    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getTemplateId() { return templateId; }
    public void setTemplateId(String templateId) { this.templateId = templateId; }

    public String getNameText() { return nameText; }
    public void setNameText(String nameText) { this.nameText = nameText; }

    public Integer getAgeNumber() { return ageNumber; }
    public void setAgeNumber(Integer ageNumber) { this.ageNumber = ageNumber; }

    public String getFlavor() { return flavor; }
    public void setFlavor(String flavor) { this.flavor = flavor; }

    public Object getToppings() { return toppings; }
    public void setToppings(Object toppings) { this.toppings = toppings; }

    public Integer getCandlesCount() { return candlesCount; }
    public void setCandlesCount(Integer candlesCount) { this.candlesCount = candlesCount; }
}
