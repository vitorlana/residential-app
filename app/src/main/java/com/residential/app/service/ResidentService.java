package com.residential.app.service;

import com.residential.app.entity.Condominium;
import com.residential.app.entity.Residence;
import com.residential.app.entity.Resident;
import com.residential.app.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResidentService {

    @Autowired
    private final ResidentRepository residentRepository;

    @Autowired
    private CondominiumService condominiumService;

    @Autowired
    private ResidenceService residenceService;

    public ResidentService(ResidentRepository residentRepository, CondominiumService condominiumService) {
        this.residentRepository = residentRepository;
    }

    public Optional<Resident> createNewResident(Resident resident){
        return Optional.of(residentRepository.save(resident));
    }

    public Optional<Resident> findResidentByName(String name){
        return residentRepository.findById(name);
    }

    public List<Resident> findAllResidents(){
        return residentRepository.findAll();
    }

    public Optional<Resident> updateResident(String name, Resident updatedData){
        Optional<Resident> resident = residentRepository.findById(name);
        if (resident.isPresent()){
            Resident updatedResident = resident.get();
            updatedResident.setName(updatedData.getName());
            updatedResident.setSurname(updatedData.getSurname());
            return Optional.of(residentRepository.save(updatedResident));
        }else{
            return Optional.empty();
        }
    }

    public void deleteResident(String name){
        Residence residence = residenceService.getResidenceByNameResident(name);
        Optional<Condominium> condominium = condominiumService.getCondominiumByResidence(residence);
        condominiumService.removeResidenceFromCondominium(residence, condominium.get());
        residentRepository.deleteById(name);
    }


}
