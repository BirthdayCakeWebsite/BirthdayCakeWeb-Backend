package birthdaycakeweb.backend.dto;

import java.util.UUID;

public class PresetResponse {

    private UUID id;
    private String clientId;
    private String nameText;
    private Integer ageNumber;
    private String shape;
    private Integer sizeDiameter;
    private Integer sizeW;
    private Integer sizeH;
    private String flavor;
    private Object toppings; // parsed from entity's String
    private Integer candlesCount;

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
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
}
