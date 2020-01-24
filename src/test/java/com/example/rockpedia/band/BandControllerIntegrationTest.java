package com.example.rockpedia.band;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BandControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testBandReturnedById() throws Exception {
        mockMvc.perform(get("/bands/{id}", 2L))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.name").value("S.U.T.U.R.E."))
                .andExpect(jsonPath("$.genre").value("Black/Crust"))
                .andExpect(jsonPath("$.themes").value("Misanthropy"))
                .andExpect(jsonPath("$.location").value("Metz, Grand Est"))
                .andExpect(jsonPath("$.country").value("France"))
                .andExpect(jsonPath("$.label").value("Black Pandemie Production"))
                .andExpect(jsonPath("$.status").value("Active"))
                .andExpect(jsonPath("$.formed").value("2016"));
    }

    @Test
    public void testDeleteBand() throws Exception {
        mockMvc.perform(delete("/bands/delete/{id}", 34L))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.message").value("Band 34 has been deleted succesfully."));
        mockMvc.perform(delete("/bands/delete/{id}", 34L))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.message").value("No class com.example.rockpedia.band.Band entity with id 34 exists!"));
        mockMvc.perform(get("/bands/{id}", 34L))
                .andExpect(status().isNoContent())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.message").value("There is no band by id 34"));
    }

    @Test
    public void testAddBand() throws Exception {
        mockMvc.perform(post("/bands/add").content("{\"name\":\"Altkönig\",\"genre\":\"Black\",\"themes\":\"Nature, Paganism, Philosophy, War\",\"location\":\"Frankfurt, Hesse\",\"country\":\"Germany\",\"label\":\"Unsigned/independent\",\"status\":\"Split-up\",\"formed\":2008}")
                            .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value("188"))
                .andExpect(jsonPath("$.name").value("Altkönig"))
                .andExpect(jsonPath("$.genre").value("Black"))
                .andExpect(jsonPath("$.themes").value("Nature, Paganism, Philosophy, War"))
                .andExpect(jsonPath("$.location").value("Frankfurt, Hesse"))
                .andExpect(jsonPath("$.country").value("Germany"))
                .andExpect(jsonPath("$.label").value("Unsigned/independent"))
                .andExpect(jsonPath("$.status").value("Split-up"))
                .andExpect(jsonPath("$.formed").value("2008"));
    }

    @Test
    public void testUpdateBand() throws Exception {
        mockMvc.perform(put("/bands/update/{id}", 120L).content("{\"name\":\"Altkönig\",\"genre\":\"Black\",\"themes\":\"Nature, Paganism, Philosophy, War\",\"location\":\"Frankfurt, Hesse\",\"country\":\"Germany\",\"label\":\"Unsigned/independent\",\"status\":\"Split-up\",\"formed\":2008}")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value("120"))
                .andExpect(jsonPath("$.name").value("Altkönig"))
                .andExpect(jsonPath("$.genre").value("Black"))
                .andExpect(jsonPath("$.themes").value("Nature, Paganism, Philosophy, War"))
                .andExpect(jsonPath("$.location").value("Frankfurt, Hesse"))
                .andExpect(jsonPath("$.country").value("Germany"))
                .andExpect(jsonPath("$.label").value("Unsigned/independent"))
                .andExpect(jsonPath("$.status").value("Split-up"))
                .andExpect(jsonPath("$.formed").value("2008"));
    }

    @Test
    public void testBandReturnBySearch() throws Exception {
        mockMvc.perform(get("/bands/search").param("q", "met"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(8)))
                .andExpect(jsonPath("$[1].id").value("16"))
                .andExpect(jsonPath("$[1].name").value("Metaphoris"))
                .andExpect(jsonPath("$[1].genre").value("Thrash/Death"))
                .andExpect(jsonPath("$[1].themes").value("Hate, Chaos"))
                .andExpect(jsonPath("$[1].location").value("Vantaa"))
                .andExpect(jsonPath("$[1].country").value("Finland"))
                .andExpect(jsonPath("$[1].label").value("Unsigned/independent"))
                .andExpect(jsonPath("$[1].status").value("Changed name"))
                .andExpect(jsonPath("$[1].formed").value("2007"))

                .andExpect(jsonPath("$[7].id").value("2"))
                .andExpect(jsonPath("$[7].name").value("S.U.T.U.R.E."))
                .andExpect(jsonPath("$[7].genre").value("Black/Crust"))
                .andExpect(jsonPath("$[7].themes").value("Misanthropy"))
                .andExpect(jsonPath("$[7].location").value("Metz, Grand Est"))
                .andExpect(jsonPath("$[7].country").value("France"))
                .andExpect(jsonPath("$[7].label").value("Black Pandemie Production"))
                .andExpect(jsonPath("$[7].status").value("Active"))
                .andExpect(jsonPath("$[7].formed").value("2016"));
    }

    @Test
    public void testBandAdvancedSearch() throws Exception {
        mockMvc.perform(get("/bands/search").param("q", "profeta"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value("7"))
                .andExpect(jsonPath("$[0].name").value("Corazón Profeta"))
                .andExpect(jsonPath("$[0].genre").value("Heavy"))
                .andExpect(jsonPath("$[0].themes").value("Personal and social issues, Heavy"))
                .andExpect(jsonPath("$[0].location").value("Necochea, Buenos Aires"))
                .andExpect(jsonPath("$[0].country").value("Argentina"))
                .andExpect(jsonPath("$[0].label").value("Unsigned/independent"))
                .andExpect(jsonPath("$[0].status").value("Active"))
                .andExpect(jsonPath("$[0].formed").value("2000"));

        mockMvc.perform(get("/bands/search").param("q", "essencia"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value("12"))
                .andExpect(jsonPath("$[0].name").value("Essência Insana"))
                .andExpect(jsonPath("$[0].genre").value("Thrash/Black"))
                .andExpect(jsonPath("$[0].themes").value("Social Criticism, Carnage, Misanthropy"))
                .andExpect(jsonPath("$[0].location").value("Campos Sales, Ceará"))
                .andExpect(jsonPath("$[0].country").value("Brazil"))
                .andExpect(jsonPath("$[0].label").value("Unsigned/independent"))
                .andExpect(jsonPath("$[0].status").value("Active"))
                .andExpect(jsonPath("$[0].formed").value("2012"));



    }

    @Test
    public void testExportCSV() throws Exception {
        mockMvc.perform(get("/bands/search").param("q", "ae").param("csv", "true"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/csv;charset=UTF-8"))
                .andExpect(content().string("ID,NAME,GENRE,THEMES,LOCATION,COUNTRY,LABEL,STATUS,DATE OF FORMATION\n" +
                        "85,\"Lunae\",\"Melodic Heavy\",\"Nature, Personal feelings\",\"Milan, Lombardy\",\"Italy\",\"Unsigned/independent\",\"Split-up\",1998\n" +
                        "123,\"Maestah\",\"Heavy/Power\",\"Christianity\",\"Paraná\",\"Brazil\",\"Unsigned/independent\",\"Active\",2012\n" +
                        "49,\"Kratic\",\"Sludge\",\"Life, Humanity\",\"Oslo/Bærum\",\"Norway\",\"Unsigned/independent\",\"Split-up\",2005\n"));
    }
}
