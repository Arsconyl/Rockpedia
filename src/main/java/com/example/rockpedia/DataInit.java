package com.example.rockpedia;

import com.example.rockpedia.band.BandRepository;
import com.example.rockpedia.band.Band;
import com.github.loki.afro.metallum.MetallumException;
import com.github.loki.afro.metallum.search.query.BandSearchQuery;
import com.github.loki.afro.metallum.search.service.advanced.BandSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInit implements ApplicationRunner {

    private BandRepository bandRepository;

    @Autowired
    public DataInit(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = bandRepository.count();
        if(count == 0)
        {
            Band b1 = new Band();
            Band b2 = new Band();

            b1.setName("Rammstein");
            b1.setLabel("Universal Music Group");
            b1.setMembers("Till Lindemann, Paul Landers, Richard Kruspe, Oliver Riedel, Christopher Schneider, Flake Lorenz");
            b1.setStyle("Industrial German Metal");
            b1.setYearofcreation(1994);
            b1.setTownoforigin("Berlin");

            b2.setName("The Beatles");
            b2.setLabel("Apple Records");
            b2.setMembers("Paul McCartney, John Lennon, Ringo Star, George Harrisson");
            b2.setStyle("Rock");
            b2.setYearofcreation(1960);
            b2.setTownoforigin("Liverpool");

            bandRepository.save(b1);
            bandRepository.save(b2);

//            final BandSearchService service = new BandSearchService();
//            final BandSearchQuery query = new BandSearchQuery();
//            //             Here we give some parameters to the query
//            //             In our case the name of the band and
//            //             the second parameter here is exact match, because we are sure that there is a Band named Slayer
//            query.setYearOfFormationFrom(2016);
//            query.setYearOfFormationTo(2019);
//            query.setGenre("Metal");
//            //             now we say "search", like pressing the search button
//            try {
//                final List<com.github.loki.afro.metallum.entity.Band> resultList = service.performSearch(query);
//                Band bandToSave;
//                System.out.println(resultList.size());
//                for (final com.github.loki.afro.metallum.entity.Band band : resultList) {
//                    String townoforigin = band.getLocation().split(",")[1].trim();
//                    String label = band.getLabel().getName().trim();
//                    String name = band.getName();
//                    String style = band.getGenre();
//                    boolean notown = !townoforigin.isEmpty(), nolabel = !label.isEmpty(), namenottoolong = name.length() < 64, stylenottoolong = style.length() < 64;
//                    if(notown && nolabel && namenottoolong && stylenottoolong) {
//                        bandToSave = new Band();
//                        bandToSave.setLabel(label);
//                        bandToSave.setName(name);
//                        bandToSave.setStyle(style);
//                        bandToSave.setTownoforigin(townoforigin);
//                        bandToSave.setYearofcreation(band.getYearFormedIn());
//                        bandToSave.setMembers(band.getLiveLineup().toString());
//
//                        bandRepository.save(bandToSave);
//                    }
//                    else
//                        System.out.println(band.getLabel().getName().length() + " " + band.getLabel().getName() + " " + name);
//                }
//            } catch (MetallumException e) {
//                e.printStackTrace();
//            }
        }
    }
}
