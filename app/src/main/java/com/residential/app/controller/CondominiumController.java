package com.residential.app.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.residential.app.entity.Condominium;
import com.residential.app.entity.embeddablesId.CondominiumId;
import com.residential.app.service.CondominiumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/condominium")
public class CondominiumController {

    private final CondominiumService condominiumService;;


    public CondominiumController(CondominiumService condominiumService){
        this.condominiumService = condominiumService;
    }

    @PostMapping()
    public ResponseEntity createNewCondominium(@RequestBody JsonNode requestData){

        if(!requestData.get("name").isNull() && !requestData.get("address").isNull()){
            Condominium newCondominium = new Condominium();
            CondominiumId newCondominiumId = new CondominiumId(requestData.get("name").asText(), requestData.get("address").asText());
            newCondominium.setExpenses(BigDecimal.ZERO);
            newCondominium.setId(newCondominiumId);

            Condominium response = condominiumService.createNewCondominium(newCondominium);

            if(response != null){
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing critical information to create a new condominium");
    }

    @GetMapping("/totalarea/{condominiumName}")
    public ResponseEntity getTotalAreaCondominum(@PathVariable String condominiumName){

        BigDecimal totalArea = condominiumService.getTotalAreaFromCondominium(condominiumName);

        if(totalArea != null){
            return ResponseEntity.status(HttpStatus.OK).body("\"totalAreaCondominium\":" + totalArea);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such condominium");
        }
    }

   @PostMapping("/add_expense/{expenseValue}")
    public ResponseEntity addExpenseCondominium(@PathVariable BigDecimal expenseValue, @RequestHeader String condominiumName){

        BigDecimal response = condominiumService.addExpenseToCondominium(expenseValue,condominiumName);

        if(response != null){
            return ResponseEntity.status(HttpStatus.OK).body("\"totalExpanse\":" + response);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

   }


}
