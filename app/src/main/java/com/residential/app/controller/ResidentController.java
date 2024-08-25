package com.residential.app.controller;

import com.residential.app.service.ResidentService;
import com.residential.app.entity.Resident;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/resident")
public class ResidentController {

    private final ResidentService residentService;

    public ResidentController(ResidentService residentService){
        this.residentService = residentService;
    }



//    Request body
//    {
//        "name": "",
//        "surname": "",
//        "type":" "
//    }
    @PostMapping()
    public ResponseEntity createResident(@RequestBody Resident resident){
        Optional<Resident> respondeDB = residentService.createNewResident(resident);

        if (respondeDB.isPresent())
            return ResponseEntity.created(null).build();
        else
            return ResponseEntity.badRequest().build();
    }


    @GetMapping("/{nameResident}")
    public ResponseEntity findResidentByName(@PathVariable String nameResident){
        Optional<Resident> residentFounded = residentService.findResidentByName(nameResident);

        if(residentFounded.isPresent())
            return ResponseEntity.ok().body(residentFounded.get());
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity listAllResidents(){
        List<Resident> residents = residentService.findAllResidents();

        if(!residents.isEmpty())
            return ResponseEntity.ok().body(residents.toArray());
        else
            return ResponseEntity.notFound().build();
    }

    //    Request body
//    {
//        "name": "",
//        "surname": "",
//        "type":" "
//    }
    @PutMapping("/{nameResident}")
    public ResponseEntity updateResident(@PathVariable String nameResident, @RequestBody Resident updatedData){
        Optional<Resident> residentDBResponse = residentService.updateResident(nameResident,updatedData);

        if (residentDBResponse.isPresent())
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{nameResident}")
    public ResponseEntity deleleResident(@PathVariable String nameResident){
        residentService.deleteResident(nameResident);

        return ResponseEntity.ok().build();
    }
}
