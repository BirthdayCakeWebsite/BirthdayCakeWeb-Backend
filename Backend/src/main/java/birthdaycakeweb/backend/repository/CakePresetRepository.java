package birthdaycakeweb.backend.repository;

import birthdaycakeweb.backend.pojo.CakePreset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CakePresetRepository extends JpaRepository<CakePreset, UUID> {
    List<CakePreset> findByClientIdOrderByCreatedAtDesc(String clientId);
}
