package edu.bu.soap;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


//mark class as Controller
@RestController
public class InfectionsController {
//autowire the infectionsService class
    @Autowired
    InfectionsService infectionsService;
   /* @Autowired
    InfectionsService infectionsService;
    @Autowired
    UserAccountsService userAccountsService;*/
    

//creating a get mapping that retrieves all the infections detail from the database
    @GetMapping("/infections")
    private List<Infections> getAllInfections() {
        return infectionsService.getAllInfections();
    }

//creating a get mapping that retrieves the detail of a specific infection
    @GetMapping("/infection/{infectionId}")
    private Infections getInfections(@PathVariable("infectionId") int infectionId) {
        return infectionsService.getInfectionsById(infectionId);
    }

//creating a delete mapping that deletes a specified infection
    @DeleteMapping("/infection/{infectionId}")
    private void deleteInfection(@PathVariable("infectionId") int infectionId) {
    	infectionsService.delete(infectionId);
    }

//creating post mapping that post the infection detail in the database
    @PostMapping("/infections")
    private int saveInfection(@RequestBody Infections infections) {
    	infectionsService.saveOrUpdate(infections);
        return infections.getId();
    }

//creating put mapping that updates the infection detail
    @PutMapping("/infections")
    private Infections update(@RequestBody Infections infections) {
    	infectionsService.saveOrUpdate(infections);
        return infections;
    }
}