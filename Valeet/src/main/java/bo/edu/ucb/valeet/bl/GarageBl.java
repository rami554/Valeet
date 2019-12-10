package bo.edu.ucb.valeet.bl;

import bo.edu.ucb.valeet.domain.ValGarage;
import bo.edu.ucb.valeet.repository.GarageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class GarageBl {


    GarageRepository repository;
    @Autowired
    public GarageBl(GarageRepository repository) {

        this.repository = repository;
    }

    public GarageBl() {

    }

    public ValGarage findByGarageId (int id) {

        return repository.findByGarageId(id);
    }

    public ValGarage create(ValGarage garage) {
        return repository.save ( garage );
    }


    public List<ValGarage> all(){
        List<ValGarage> garageList = new ArrayList<> ();
        List<ValGarage> all = repository.findAll();
        for(ValGarage gar: all){
            if(gar.getStatus() == 1){
                garageList.add(gar);
            }
        }
        return garageList;
    }
}
