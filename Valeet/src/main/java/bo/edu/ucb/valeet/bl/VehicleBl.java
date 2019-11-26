package bo.edu.ucb.valeet.bl;

import bo.edu.ucb.valeet.domain.ValVehicle;
import bo.edu.ucb.valeet.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class VehicleBl {

    VehicleRepository repository;
    @Autowired
    public VehicleBl(VehicleRepository repository) {

        this.repository = repository;
    }

    public VehicleBl() {

    }

    public List findByVehicleId(int id) {

        return repository.findByVehicleId(id);
    }

    public  ValVehicle create(ValVehicle vehicle) {

        return repository.save ( vehicle );
    }

    public List<ValVehicle> all(){
        List<ValVehicle> vehicleList = new ArrayList<>();
        List<ValVehicle> all = repository.findAll();
        for(ValVehicle car: all){
            if(car.getStatus() == 1){
                vehicleList.add(car);
            }
        }
        return vehicleList;
    }

}
