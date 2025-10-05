package birthdaycakeweb.backend.repository;

import birthdaycakeweb.backend.pojo.CakeTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakeTemplateRepository extends JpaRepository<CakeTemplate, String> {
}
