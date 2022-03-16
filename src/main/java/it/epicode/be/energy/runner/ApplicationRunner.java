package it.epicode.be.energy.runner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import it.epicode.be.energy.model.Comune;
import it.epicode.be.energy.model.Provincia;
import it.epicode.be.energy.repository.ComuneRepository;
import it.epicode.be.energy.repository.ProvinciaRepository;
import it.epicode.be.energy.service.ProvinciaService;


@Component
public class ApplicationRunner implements CommandLineRunner{

	@Autowired
    ComuneRepository comuneRepo;
    
    @Autowired
    ProvinciaRepository provinciaRepo;
    
    @Autowired
    ProvinciaService provinciaService;

    //commento
    @Override
    public void run(String... args) throws Exception {
       if(provinciaService.findAll().isEmpty()) {
    	caricaProvince();
        caricaComuni();
       }     
    }

    
    public void caricaComuni() {
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
        try (CSVReader reader = new CSVReaderBuilder(
                  new FileReader("comuni-italiani.csv"))
                  .withCSVParser(csvParser)
                  .withSkipLines(1)
                  .build()) {
            String[] values = null;

            while ((values = reader.readNext()) != null) {
                Comune comune = new Comune();
                comune.setNome(values[2]);
                Provincia prov = provinciaRepo.findByNome(values[3]);
                if(prov != null) {
                    comune.setProvincia(prov);
                    comuneRepo.save(comune);
                }
                
                //comuneRepo.save(new Comune(values[0].equals(provinciaRepo.getClass()), values[1], values[2], values[3]));
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    
    public void caricaProvince() {
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
        try (CSVReader reader = new CSVReaderBuilder(
                  new FileReader("province-italiane.csv"))
                  .withCSVParser(csvParser)
                  .withSkipLines(1)
                  .build()) {
           String[] values = null;

            while ((values = reader.readNext()) != null) {
                Provincia prov = new Provincia();
                prov.setSigla(values[0]);
                prov.setNome(values[1]);
                prov.setRegione(values[2]);
                provinciaRepo.save(prov);
                
                //provinciaRepo.save(new Provincia(values[0], values[1], values[2]));
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
		
	}

}
