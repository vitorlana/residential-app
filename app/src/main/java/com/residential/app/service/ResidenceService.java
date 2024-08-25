package com.residential.app.service;

import com.residential.app.entity.Condominium;
import com.residential.app.entity.Residence;
import com.residential.app.repository.ResidenceRepository;
import com.residential.app.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.Optional;

@Service
public class ResidenceService {

    @Autowired
    private ResidenceRepository residenceRepository;

    @Autowired
    private CondominiumService condominiumService;

    @Autowired
    private ResidentRepository residentRepository;

    public ResidenceService(ResidenceRepository residenceRepository) {
        this.residenceRepository = residenceRepository;
    }

    public Residence getResidenceByNameResident(String nameResident) {
        return residenceRepository.findByResidentName(nameResident);
    }

    public Residence createNewResidence(Residence residence){
           Residence response =  residenceRepository.save(residence);
        return residence;
    }

    public void updateIdealFraction(Residence residence){

        Optional<Condominium> condominium = condominiumService.getCondominiumByResidence(residence);

        condominium.ifPresent(value -> value.getResidences().forEach(
                residenceStream -> {
                    residenceStream.setIdealFraction(residenceStream.getArea().divide(value.getTotalArea(), 10, RoundingMode.HALF_EVEN));
                    residenceRepository.save(residenceStream);
                }
        ));

    }

    public void updateCondominiumValue(Residence residence){

        Optional<Condominium> condominium = condominiumService.getCondominiumByResidence(residence);

        condominium.ifPresent(value -> value.getResidences().forEach(
                residenceStream -> {
                    residenceStream.setCondominiumValue(residenceStream.getIdealFraction().multiply(value.getExpenses()));
                    residenceRepository.save(residenceStream);
                }
        ));

    }

}
