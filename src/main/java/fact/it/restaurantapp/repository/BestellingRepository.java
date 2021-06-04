package fact.it.restaurantapp.repository;

import fact.it.restaurantapp.model.Bestelling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.GregorianCalendar;
import java.util.List;

@Repository
public interface BestellingRepository extends JpaRepository<Bestelling, Long> {
    @Query("select distinct b.datum from Bestelling b ")
    List<GregorianCalendar> getdatum();

    @Query("select distinct b.tafel.code from Bestelling b ")
    List<String> getcode();


    @Query("select b from Bestelling b where b.datum = :gregorianCalendar")
    List<Bestelling> searchdatum(@Param("gregorianCalendar") GregorianCalendar gregorianCalendar);

    @Query("select b from Bestelling b where b.tafel.code =?1")
    List<Bestelling> searchtafel(String code);

    List<Bestelling> findAllByDatum(GregorianCalendar gregorianCalendar);


}
