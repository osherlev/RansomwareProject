package repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import entities.KeyEntity;


@SuppressWarnings("rawtypes")
@Repository
public interface KeyRepository extends JpaRepository<KeyEntity, String>  {

}
