package fact.it.restaurantapp.repository;

import fact.it.restaurantapp.model.Zaalpersoneel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZaalpersoneelRepository extends JpaRepository<Zaalpersoneel, Long> {
}
