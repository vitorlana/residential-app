package com.residential.app.service;

import com.residential.app.entity.Condominium;
import com.residential.app.entity.Residence;
import com.residential.app.entity.embeddablesId.CondominiumId;
import com.residential.app.repository.CondominiumRepository;
import com.residential.app.repository.ResidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CondominiumService {

    @Autowired
    private CondominiumRepository condominiumRepository;

    @Autowired
    ResidenceRepository residenceRepository;

    public CondominiumService(CondominiumRepository CondominiumRepository) {
        this.condominiumRepository = CondominiumRepository;
    }

    public Condominium removeResidenceFromCondominium(Residence residence, Condominium condominium){

        condominium.removeResidence(residence);

        return condominiumRepository.save(condominium);
    }

    public Condominium createNewCondominium(Condominium Condominium){
        return condominiumRepository.save(Condominium);
    }

    public BigDecimal getTotalAreaFromCondominiumByResidence(Residence residence){

        Optional<Condominium> condominiumEntity = condominiumRepository.findByResidencesId(residence.getId());

        return condominiumEntity.map(Condominium::getTotalArea).orElse(null);

    }

    public BigDecimal getTotalAreaFromCondominium(String condominiumName){

        Optional<Condominium> condominiumEntity = condominiumRepository.findByIdName(condominiumName);

        return condominiumEntity.map(Condominium::getTotalArea).orElse(null);


    }

    private void updateTotalArea(Condominium condominium){
        condominium.setTotalArea(condominium.getResidences().stream().map(Residence::getArea).reduce(BigDecimal.ZERO, BigDecimal::add));
    }


    public Boolean addNewResidenceToCondominium(Residence residence, String condominiumName) {

        Optional<Condominium> residenceExist  = condominiumRepository.findByResidencesId(residence.getId());
        if(residenceExist.isPresent()){
            return false;
        }

        Optional<Condominium> condominium =  condominiumRepository.findByIdName(condominiumName);

        if(condominium.isPresent()){
            Condominium condominiumEntity = condominium.get();
            condominiumEntity.getResidences().add(residence);
            updateTotalArea(condominiumEntity);
            condominiumRepository.save(condominiumEntity);
            return true;
        }else{
            return false;
        }

    }

    public Optional<Condominium> getCondominiumByResidence(Residence residence) {

        return condominiumRepository.findByResidencesId(residence.getId());
    }

    public BigDecimal addExpenseToCondominium(BigDecimal expenseValue, String condominiumName) {

        Optional<Condominium> condominium =  condominiumRepository.findByIdName(condominiumName);

        if(condominium.isPresent()){
            Condominium condominiumEntity = condominium.get();
            BigDecimal newExpense = condominiumEntity.getExpenses().add(expenseValue);
            condominiumEntity.setExpenses(newExpense);
            condominiumRepository.save(condominiumEntity);
            updateCondominiumValue(condominiumEntity);
            return newExpense;
        }else{
            return null;
        }

    }

    public void updateCondominiumValue(Condominium condominium){

        condominium.getResidences().forEach(
                residenceStream -> {
                    residenceStream.setCondominiumValue(residenceStream.getIdealFraction().multiply(condominium.getExpenses()));
                    residenceRepository.save(residenceStream);
                }
        );

    }

}
