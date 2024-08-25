package com.residential.app.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.residential.app.entity.Residence;
import com.residential.app.entity.Resident;
import com.residential.app.enums.ResidenceType;
import com.residential.app.service.CondominiumService;
import com.residential.app.service.ResidenceService;
import com.residential.app.service.ResidentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/residence")
public class ResidenceController {

    private final ResidenceService residenceService;
    private final ResidentService residentService;
    private final CondominiumService condominiumService;


    public ResidenceController(ResidenceService residenceService,
                               ResidentService residentService, CondominiumService condominiumService){
        this.residenceService = residenceService;
        this.residentService = residentService;
        this.condominiumService = condominiumService;
    }

    @PostMapping
    public ResponseEntity createNewResidence(@RequestBody JsonNode requestData) {


        // FIXME: Make more sense to create a the residence first and after that create and link the resident
        // TODO: Improve way to check with the request if completed

        if(!requestData.get("number").isNull() && !requestData.get("residentName").isNull()
                && !requestData.get("type").isNull() && !requestData.get("condominiumName").isNull()
                && !requestData.get("area").isNull()){

            // TODO: Remove the access to resident service here, the controller for residence shouldn't call other the residenceService

            Optional<Resident> resident = residentService.findResidentByName(requestData.get("residentName").asText());

            if(resident.isPresent()){
                Residence residence = new Residence();
                residence.setNumber(requestData.get("number").asLong());
                residence.setType(ResidenceType.valueOf(requestData.get("type").asText()));
                residence.setArea(requestData.get("area").decimalValue());
                residence.setResident(resident.get());
                resident.get().setResidence(residence);

                Residence response = residenceService.createNewResidence(residence);

                if(response == null){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Condominium doesn't exist to assign residence");
                }else {
                    Boolean condominiumExist =  condominiumService.addNewResidenceToCondominium(residence, requestData.get("condominiumName").asText());
                    if(!condominiumExist){
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Condominium doesn't exist to assign residence or residence already exist");
                    }
                }
                residentService.updateResident(resident.get().getName(),resident.get());
                residenceService.updateIdealFraction(residence);
                residenceService.updateCondominiumValue(residence);

                return ResponseEntity.created(null).build();
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resident not found, create before create a residence");
            }
        }

        return ResponseEntity.badRequest().build();
    }
}
