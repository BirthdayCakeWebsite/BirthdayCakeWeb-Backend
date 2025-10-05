package birthdaycakeweb.backend.pojo;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "cake_template")
public class CakeTemplate {

    /** Ví dụ: R1/R2/R3/S1/S2/S3 */
    @Id
    @Column(length = 10)
    private String id;

    /** "round" | "square" */
    @Column(name = "shape", nullable = false, length = 10)
    private String shape;

    /** 1 | 2 | 3 */
    @Column(name = "tiers", nullable = false)
    private Integer tiers;

    @Column(name = "created_at")
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = Instant.now();
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getShape() { return shape; }
    public void setShape(String shape) { this.shape = shape; }

    public Integer getTiers() { return tiers; }
    public void setTiers(Integer tiers) { this.tiers = tiers; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
