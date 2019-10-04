package com.example.rockpedia.init;

import com.example.rockpedia.dao.BandDAO;
import com.example.rockpedia.entity.Band;
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

    private BandDAO bandDAO;

    @Autowired
    public DataInit(BandDAO bandDAO) {
        this.bandDAO = bandDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = bandDAO.count();
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

            bandDAO.save(b1);
            bandDAO.save(b2);

            final BandSearchService service = new BandSearchService();
            final BandSearchQuery query = new BandSearchQuery();
            //             Here we give some parameters to the query
            //             In our case the name of the band and
            //             the second parameter here is exact match, because we are sure that there is a Band named Slayer
            query.setYearOfFormationFrom(2019);
            query.setYearOfFormationTo(2019);
            query.setGenre("Metal");
            //             now we say "search", like pressing the search button
            try {
                final List<com.github.loki.afro.metallum.entity.Band> resultList = service.performSearch(query);
                Band bandToSave;
                for (final com.github.loki.afro.metallum.entity.Band band : resultList) {
                    bandToSave = new Band();
                    bandToSave.setLabel(band.getLabel().getName());
                    bandToSave.setName(band.getName());
                    bandToSave.setStyle(band.getGenre());
                    bandToSave.setTownoforigin(band.getLocation().split(",")[1]);
                    bandToSave.setYearofcreation(band.getYearFormedIn());
                    bandToSave.setMembers(band.getLiveLineup().toString());

                    bandDAO.save(bandToSave);
//                    System.out.println("Bandname: " + band.getName());
//                    System.out.println("Bandgenre: " + band.getGenre());
//                    System.out.println("Bandstatus: " + band.getStatus().asString());
//                    System.out.println("---");

                }
            } catch (MetallumException e) {
                e.printStackTrace();
            }
        }
    }
}
