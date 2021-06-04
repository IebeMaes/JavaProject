package fact.it.restaurantapp.controller;


import fact.it.restaurantapp.model.TakeAwayBestelling;
import fact.it.restaurantapp.repository.TakeAwayBestellingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private TakeAwayBestellingRepository takeAwayBestellingRepository;


    @GetMapping("/tabestellingen")
    public List<TakeAwayBestelling> geefall(){
        return takeAwayBestellingRepository.getAllOrderByName();
    }

    @PostMapping("/tabestellingen")
    public TakeAwayBestelling voegbestellingtoe(@RequestBody TakeAwayBestelling takeAwayBestelling){
        takeAwayBestellingRepository.save(takeAwayBestelling);
        return takeAwayBestelling;
    }

    @DeleteMapping("/tabestellingen/{id}")
    public List<TakeAwayBestelling> delete(@PathVariable Long id){
        takeAwayBestellingRepository.deleteById(id);
        return takeAwayBestellingRepository.getAllOrderByName();
    }


}
