
// File: Backend/src/main/java/birthdaycakeweb/backend/pojo/Client.java
package birthdaycakeweb.backend.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.PrePersist;

import java.time.Instant;

@Entity
@Table(name = "client")
public class Client {

    @Id
    private String clientId;

    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }

    // Getters and setters (optional, recommended for JPA)
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

