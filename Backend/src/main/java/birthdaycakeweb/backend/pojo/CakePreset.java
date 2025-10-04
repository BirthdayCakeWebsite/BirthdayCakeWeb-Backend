package birthdaycakeweb.backend.pojo;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.databind.JsonNode;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.time.Instant;
import java.util.UUID;
import com.fasterxml.jackson.databind.JsonNode;

@Entity
@Table(name = "cake_preset")
public class CakePreset {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "client_id",nullable = false)
    private String clientId;
    @Column(name = "name_text")
    private String nameText;
    @Column(name = "age_number")
    private Integer ageNumber;
    @Column(name = "shape")
    private String shape; // "round"/"square"
    @Column(name = "size_diameter")
    private Integer sizeDiameter; // for round cakes
    @Column(name = "size_w")
    private Integer sizeW;
    @Column(name = "size_h")// for square cakes
    private Integer sizeH; // for square cakes
    @Column(name = "flavor")
    private String flavor;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "toppings", columnDefinition = "jsonb")
    private JsonNode toppings;  // stores JSON raw

    @Column(name = "candles_count")
    private Integer candlesCount;

    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }

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

    public JsonNode getToppings() {
        return toppings;
    }

    public void setToppings(JsonNode toppings) {
        this.toppings = toppings;
    }

    public Integer getCandlesCount() {
        return candlesCount;
    }

    public void setCandlesCount(Integer candlesCount) {
        this.candlesCount = candlesCount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
