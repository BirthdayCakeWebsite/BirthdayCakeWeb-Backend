package birthdaycakeweb.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePresetRequest {

    @NotBlank
    private String clientId;

    @NotBlank
    private String shape; // "round" | "square"

    private String nameText;

    @Min(0)
    @Max(120)
    private Integer ageNumber;

    @Min(1)
    private Integer sizeDiameter; // for round shape

    @Min(1)
    private Integer sizeW; // for square shape

    @Min(1)
    private Integer sizeH; // for square shape

    private String flavor;

    private Object toppings; // FE sends object/array, will be stringified

    private Instant createdAt;
    private Instant updatedAt;
    @Min(0)
    @Max(50)
    private Integer candlesCount;

    // Getters and Setters
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getNameText() {
        return nameText;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText;
    }

    public Integer getAgeNumber() {
        return ageNumber;
    }

    public void setAgeNumber(Integer ageNumber) {
        this.ageNumber = ageNumber;
    }

    public Integer getSizeDiameter() {
        return sizeDiameter;
    }

    public void setSizeDiameter(Integer sizeDiameter) {
        this.sizeDiameter = sizeDiameter;
    }

    public Integer getSizeW() {
        return sizeW;
    }

    public void setSizeW(Integer sizeW) {
        this.sizeW = sizeW;
    }

    public Integer getSizeH() {
        return sizeH;
    }

    public void setSizeH(Integer sizeH) {
        this.sizeH = sizeH;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public Object getToppings() {
        return toppings;
    }

    public void setToppings(Object toppings) {
        this.toppings = toppings;
    }

    public Integer getCandlesCount() {
        return candlesCount;
    }

    public void setCandlesCount(Integer candlesCount) {
        this.candlesCount = candlesCount;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
