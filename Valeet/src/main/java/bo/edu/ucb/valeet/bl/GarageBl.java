package bo.edu.ucb.valeet.bl;

import bo.edu.ucb.valeet.domain.ValGarage;

import bo.edu.ucb.valeet.repository.GarageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class GarageBl {

    GarageRepository repository1;
    @Autowired
    public GarageBl(GarageRepository repository1) {

        this.repository1 = repository1;
    }

    public GarageBl() {

    }

    public List findByGarageId (int id) {

        return repository1.findByGarageId(id);
    }

    public ValGarage create(ValGarage garage) {
        return repository1.save ( garage );
    }

}
