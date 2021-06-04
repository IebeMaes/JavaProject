package fact.it.restaurantapp.controller;


import fact.it.restaurantapp.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import fact.it.restaurantapp.model.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Controller
public class MainController {
    private BesteldItemRepository besteldItemRepository;
    private BestellingRepository bestellingRepository;
    private GerechtRepository gerechtRepository;
    private KeukenpersoneelRepository keukenpersoneelRepository;
    private TafelRepository tafelRepository;
    private ZaalpersoneelRepository zaalpersoneelRepository;
    private PersoneelRepository personeelRepository;
    private TakeAwayBestellingRepository takeAwayBestellingRepository;

    public MainController(BesteldItemRepository besteldItemRepository, BestellingRepository bestellingRepository, GerechtRepository gerechtRepository, KeukenpersoneelRepository keukenpersoneelRepository, TafelRepository tafelRepository, ZaalpersoneelRepository zaalpersoneelRepository, PersoneelRepository personeelRepository, TakeAwayBestellingRepository takeAwayBestellingRepository) {
        this.besteldItemRepository = besteldItemRepository;
        this.bestellingRepository = bestellingRepository;
        this.gerechtRepository = gerechtRepository;
        this.keukenpersoneelRepository = keukenpersoneelRepository;
        this.tafelRepository = tafelRepository;
        this.zaalpersoneelRepository = zaalpersoneelRepository;
        this.personeelRepository = personeelRepository;
        this.takeAwayBestellingRepository = takeAwayBestellingRepository;
    }


    @RequestMapping("/")
    public String index() {
        return "index";
    }

    boolean first = true;

    @RequestMapping("/opvullen")
    public String opvullen(Model model) throws ParseException {
        if (first == false) {
            String message = "U kan de database slechts eenmalig opvullen.";
            model.addAttribute("message", message);
        }
        if (first == true) {
            Gerecht gerecht1 = new Gerecht();
            gerecht1.setNaam("Stoofvlees");
            gerecht1.setActuelePrijs(19.00);
            gerechtRepository.save(gerecht1);

            Gerecht gerecht2 = new Gerecht();
            gerecht2.setNaam("Pizza");
            gerecht2.setActuelePrijs(13.50);
            gerechtRepository.save(gerecht2);

            Gerecht gerecht3 = new Gerecht();
            gerecht3.setNaam("Burger");
            gerecht3.setActuelePrijs(14.00);
            gerechtRepository.save(gerecht3);

            Gerecht gerecht4 = new Gerecht();
            gerecht4.setNaam("Steak");
            gerecht4.setActuelePrijs(22.50);
            gerechtRepository.save(gerecht4);

            Gerecht gerecht5 = new Gerecht();
            gerecht5.setNaam("Videe");
            gerecht5.setActuelePrijs(18.50);
            gerechtRepository.save(gerecht5);

            for (int i = 1; i < 6; i++) {
                Zaalpersoneel zaalpersoneel = new Zaalpersoneel();
                zaalpersoneel.setNaam("Zaalpersoneel " + i);
                zaalpersoneelRepository.save(zaalpersoneel);

                Tafel tafel = new Tafel();
                tafel.setCode("A" + i);
                tafelRepository.save(tafel);

                BesteldItem besteldItem = new BesteldItem();
                besteldItem.setAantal(i);
                besteldItem.setGerecht(gerechtRepository.getOne((long) i));
                besteldItem.setToegepastePrijs(gerechtRepository.getOne((long) i).getActuelePrijs());


                Bestelling bestelling = new Bestelling();
                ArrayList<BesteldItem> items = new ArrayList<>();
                items.add(besteldItem);
                bestelling.setBesteldItems(items);
                bestelling.setBetaald(false);
                HappyHourBetaling happyHourBetaling = new HappyHourBetaling();
                bestelling.setBetaalStrategie(happyHourBetaling);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                String datum = "2020-05-1" + i;
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(datum);
                gregorianCalendar.setTime(date);
                gregorianCalendar.setTimeZone(TimeZone.getTimeZone("CET"));
                bestelling.setDatum(gregorianCalendar);
                bestelling.setTafel(tafelRepository.getOne((long) i));
                bestelling.setZaalpersoneel(zaalpersoneelRepository.getOne((long) i));
                bestellingRepository.save(bestelling);

                besteldItem.setBestelling(bestelling);
                besteldItemRepository.save(besteldItem);


            }
            Keukenpersoneel keukenpersoneel = new Keukenpersoneel();
            keukenpersoneel.setNaam("Ronny");
            keukenpersoneelRepository.save(keukenpersoneel);
            Keukenpersoneel keukenpersoneel1 = new Keukenpersoneel();
            keukenpersoneel1.setNaam("Seppe");
            keukenpersoneelRepository.save(keukenpersoneel1);
            Keukenpersoneel keukenpersoneel2 = new Keukenpersoneel();
            keukenpersoneel2.setNaam("Tom");
            keukenpersoneelRepository.save(keukenpersoneel2);
            Keukenpersoneel keukenpersoneel3 = new Keukenpersoneel();
            keukenpersoneel3.setNaam("Victor");
            keukenpersoneelRepository.save(keukenpersoneel3);
            Keukenpersoneel keukenpersoneel4 = new Keukenpersoneel();
            keukenpersoneel4.setNaam("Iebe");
            keukenpersoneelRepository.save(keukenpersoneel4);


            String message = "De database is opgevuld.";
            model.addAttribute("message", message);
            first = false;
        }


        return "index";
    }

    @RequestMapping("/personeelsleden")
    public String personeelsleden(Model model) {
        List<Keukenpersoneel> keukenpersoneels = keukenpersoneelRepository.findAll();
        List<Zaalpersoneel> zaalpersoneels = zaalpersoneelRepository.findAll();
        model.addAttribute("keukenpersoneelslist", keukenpersoneels);
        model.addAttribute("zaalpersoneelslist", zaalpersoneels);
        return "personeelsleden";
    }

    @RequestMapping("/addpersoneel")
    public RedirectView addPersoneel(Model model, HttpServletRequest request) {
        String naam = request.getParameter("naam");
        int functie = Integer.parseInt(request.getParameter("functie"));
        if (functie == 0) {
            Zaalpersoneel zaalpersoneel = new Zaalpersoneel();
            zaalpersoneel.setNaam(naam);
            zaalpersoneelRepository.save(zaalpersoneel);
        }
        if (functie == 1) {
            Keukenpersoneel keukenpersoneel = new Keukenpersoneel();
            keukenpersoneel.setNaam(naam);
            keukenpersoneelRepository.save(keukenpersoneel);
        }

        return new RedirectView("/personeelsleden");
    }

    @RequestMapping("/bestellingen")
    public String bestellingen(Model model) {
        List<Bestelling> bestellings = bestellingRepository.findAll();
        model.addAttribute("bestellingslist", bestellings);

        return "bestellingen";
    }

    @RequestMapping("/zoekBestelling")
    public String zoeken(Model model) {
        List<GregorianCalendar> data = bestellingRepository.getdatum();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        List<String> codes = bestellingRepository.getcode();
        model.addAttribute("datalist", data);
        model.addAttribute("codelist", codes);
        model.addAttribute("gregoriancalender", gregorianCalendar);
        return "zoeken";
    }

    @RequestMapping(value = "/zoekdatum", method = RequestMethod.POST)
    public String verwerkenzoeken(Model model, HttpServletRequest request) throws ParseException {
        String datum = request.getParameter("datum");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(datum);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);

        if (!datum.equals("")) {
//            List<Bestelling> list = bestellingRepository.findAllByDatum(gregorianCalendar);
            List<Bestelling> list = bestellingRepository.searchdatum(gregorianCalendar);
            model.addAttribute("bestellingslist", list);
        }
        if (datum.equals("")) {
            List<Bestelling> bestellings = bestellingRepository.findAll();
            model.addAttribute("bestellingslist", bestellings);
        }
        return "bestellingen";
    }

    @RequestMapping("/zoektafel")
    public String zoektafel(Model model, HttpServletRequest request) {
        String tafel = request.getParameter("tafel");
        List<Bestelling> bestellings = bestellingRepository.findAll();
        if (!tafel.equals("leeg")) {
            List<Bestelling> list = bestellingRepository.searchtafel(tafel);
            model.addAttribute("bestellingslist", list);
        }
        if (tafel.equals("leeg")) {
            model.addAttribute("bestellingslist", bestellings);
        }
        return "bestellingen";
    }

    @RequestMapping("/zoektotaal")
    public String zoektotaal(Model model, HttpServletRequest request) {
        Double totaal = Double.parseDouble(request.getParameter("totaal"));
        List<Bestelling> bestellings = bestellingRepository.findAll();
        if (totaal != 0) {
            List<Bestelling> bestellingenzoeken = new ArrayList<>();
            for (Bestelling bestelling : bestellings) {
                if (bestelling.getTotaal() > totaal) {
                    bestellingenzoeken.add(bestelling);
                }
            }
            model.addAttribute("bestellingslist", bestellingenzoeken);
        }
        if (totaal == 0) {
            model.addAttribute("bestellingslist", bestellings);
        }
        return "bestellingen";
    }


    @RequestMapping("/details")
    public String details(Model model, HttpServletRequest request) {
        Long bestellingId = Long.valueOf(request.getParameter("bestellingId"));
        Bestelling bestelling = bestellingRepository.findById(bestellingId).get();
        model.addAttribute("bestelling", bestelling);
        return "details";
    }

    @RequestMapping("/toevoegenbestelling")
    public String toevoegenbestelling(Model model) {
        List<Tafel> tafels = tafelRepository.findAll();
        List<Zaalpersoneel> zaalpersoneels = zaalpersoneelRepository.findAll();
        List<Gerecht> gerechts = gerechtRepository.findAll();

        model.addAttribute("tafels", tafels);
        model.addAttribute("zaalpersoneels", zaalpersoneels);
        model.addAttribute("gerechten", gerechts);
        return "toevoegenbestelling";
    }

    @RequestMapping("/addbestelling")
    public RedirectView addbestelling(Model model, HttpServletRequest request) {
        long personeelid = Long.parseLong(request.getParameter("zaalpersoneel"));
        Zaalpersoneel zaalpersoneel = zaalpersoneelRepository.getOne(personeelid);
        long tafelid = Long.parseLong(request.getParameter("tafel"));
        Tafel tafel = tafelRepository.getOne(tafelid);
        List<Gerecht> gerechts = gerechtRepository.findAll();
        List<Gerecht> formgerechts = new ArrayList<>();
        Boolean happy = Boolean.parseBoolean(request.getParameter("Happy"));
        for (Gerecht gerecht : gerechts) {
            Boolean gerechtformulier = Boolean.parseBoolean(request.getParameter(gerecht.getNaam()));
            if (gerechtformulier) {
                formgerechts.add(gerecht);
            }
        }

        Bestelling bestelling = new Bestelling();
        bestelling.setZaalpersoneel(zaalpersoneel);
        bestelling.setTafel(tafel);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        LocalDate localDate = LocalDate.now();
        Date nu = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        gregorianCalendar.setTime(nu);
        bestelling.setDatum(gregorianCalendar);
        bestelling.setBetaald(false);
        if (happy) {
            HappyHourBetaling happyHourBetaling = new HappyHourBetaling();
            bestelling.setBetaalStrategie(happyHourBetaling);
        }


        List<BesteldItem> besteldItems = new ArrayList<>();
        for (Gerecht gerechtformulier : formgerechts) {
            BesteldItem besteldItem = new BesteldItem();
            besteldItem.setGerecht(gerechtformulier);
            besteldItem.setToegepastePrijs(bestelling.getBetaalStrategie().getToegepastePrijs(gerechtformulier.getActuelePrijs()));
            String aantal = Long.toString(gerechtformulier.getId());
            besteldItem.setAantal(Integer.parseInt(request.getParameter(aantal)));
            besteldItem.setBestelling(bestelling);
            besteldItems.add(besteldItem);
        }
        bestelling.setBesteldItems(besteldItems);
        bestellingRepository.save(bestelling);

        for (BesteldItem besteldItem : besteldItems) {
            besteldItemRepository.save(besteldItem);
        }

        return new RedirectView("/bestellingen");
    }

    @RequestMapping("/zetbetaald")
    public RedirectView zetbetaald(Model model, HttpServletRequest request) {
        Long bestellingId = Long.valueOf(request.getParameter("bestellingId"));
        Bestelling bestelling = bestellingRepository.findById(bestellingId).get();
        bestelling.setBetaald(true);
        bestellingRepository.save(bestelling);

        return new RedirectView("/bestellingen");
    }

    @RequestMapping("/NewTakeAway")
    public String newTakeAway(Model model){
        List<Keukenpersoneel> keukenpersoneels = keukenpersoneelRepository.findAll();
        List<Gerecht> gerechts = gerechtRepository.findAll();
        model.addAttribute("keukenpersoneelslist", keukenpersoneels);
        model.addAttribute("gerechtlist", gerechts);
        return "1_newTakeAway";
    }

    @RequestMapping("/addTakeAway")
    public RedirectView addTakeAway(Model model, HttpServletRequest request){
        //Juiste gerecht ophalen
        Long gerechtid = Long.parseLong(request.getParameter("gerecht"));
        Gerecht gerecht = gerechtRepository.getOne(gerechtid);

        //Juiste personeelslid ophalen
        Long keukenpersoneelsid = Long.parseLong(request.getParameter("keukenpersoneel"));
        Keukenpersoneel keukenpersoneel = keukenpersoneelRepository.getOne(keukenpersoneelsid);

        //aantal ophalen
        int aantal = Integer.parseInt(request.getParameter("aantal"));

        //Datum van vandaag ophalen
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        LocalDate localDate = LocalDate.now();
        Date nu = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        gregorianCalendar.setTime(nu);

        TakeAwayBestelling takeAwayBestelling = new TakeAwayBestelling();
        takeAwayBestelling.setKeukenpersoneel(keukenpersoneel);
        takeAwayBestelling.setGerecht(gerecht);
        takeAwayBestelling.setAantal(aantal);
        takeAwayBestelling.setDatum(gregorianCalendar);
        takeAwayBestellingRepository.save(takeAwayBestelling);
        return new RedirectView("/overzichtTakeAway");
    }

    @RequestMapping("/overzichtTakeAway")
    public String overzichtTakeAway(Model model){
        List<TakeAwayBestelling> takeAwayBestellings = takeAwayBestellingRepository.getAllOrderByName();
        model.addAttribute("takeAwayBestellingen", takeAwayBestellings);
        String DateFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DateFormat);
        model.addAttribute("sdf", sdf);
        return "2_overzichtTakeAway";
    }

    @RequestMapping("/testDecoratorAndObserver")
    public String test(Model model){
        IngangTeller ingangTeller = IngangTeller.getInstance();
        Zaalpersoneel tommy = new Zaalpersoneel();
        tommy.setNaam("Tommy");
        AfhaalVerantwoordelijke afhaalVerantwoordelijke = new AfhaalVerantwoordelijke();
        afhaalVerantwoordelijke.setPersoneel(tommy);
        ingangTeller.attachObserver(afhaalVerantwoordelijke);

        ingangTeller.setAantal(1);
        afhaalVerantwoordelijke.leverBestellingAf();

        String message = "De test is geslaagd, kijk in de console!";
        model.addAttribute("message", message);
        return "index";
    }
}
