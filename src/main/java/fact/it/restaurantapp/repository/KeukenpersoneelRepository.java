package fact.it.restaurantapp.repository;

import fact.it.restaurantapp.model.Keukenpersoneel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeukenpersoneelRepository extends JpaRepository<Keukenpersoneel, Long> {
}
