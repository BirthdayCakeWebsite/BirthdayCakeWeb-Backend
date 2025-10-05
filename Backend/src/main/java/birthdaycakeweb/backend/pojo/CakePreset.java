package birthdaycakeweb.backend.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "cake_preset")
public class CakePreset {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "name_text")
    private String nameText;

    @Column(name = "age_number")
    private Integer ageNumber;

    /** Tham chiếu template-bánh */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private CakeTemplate template;

    @Column(name = "flavor")
    private String flavor;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "toppings", columnDefinition = "jsonb")
    private JsonNode toppings;

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

    // Getters & Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getNameText() { return nameText; }
    public void setNameText(String nameText) { this.nameText = nameText; }

    public Integer getAgeNumber() { return ageNumber; }
    public void setAgeNumber(Integer ageNumber) { this.ageNumber = ageNumber; }

    public CakeTemplate getTemplate() { return template; }
    public void setTemplate(CakeTemplate template) { this.template = template; }

    public String getFlavor() { return flavor; }
    public void setFlavor(String flavor) { this.flavor = flavor; }

    public JsonNode getToppings() { return toppings; }
    public void setToppings(JsonNode toppings) { this.toppings = toppings; }

    public Integer getCandlesCount() { return candlesCount; }
    public void setCandlesCount(Integer candlesCount) { this.candlesCount = candlesCount; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
