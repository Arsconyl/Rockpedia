package com.example.rockpedia.init;

import com.example.rockpedia.dao.BandDAO;
import com.example.rockpedia.entity.Band;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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
        }
    }
}
