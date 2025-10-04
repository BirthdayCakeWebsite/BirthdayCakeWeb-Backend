package birthdaycakeweb.backend.repository;

import birthdaycakeweb.backend.pojo.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {

}