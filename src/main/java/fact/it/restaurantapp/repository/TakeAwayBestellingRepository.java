package fact.it.restaurantapp.repository;

import fact.it.restaurantapp.model.TakeAwayBestelling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TakeAwayBestellingRepository extends JpaRepository<TakeAwayBestelling, Long> {
    @Query("select t from TakeAwayBestelling t order by t.keukenpersoneel.naam")
    List<TakeAwayBestelling> getAllOrderByName();
}
