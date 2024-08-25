package org.question4.controller;

import org.question4.entity.MoradorEntity;
import org.question4.service.MoradorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class MoradorController {

    private final MoradorService moradorService;

    public MoradorController(MoradorService moradorService){
        this.moradorService = moradorService;
    }



//    Request body
//    {
//        "nome": "",
//        "sobrenome": "",
//        "endereco": ""
//    }
    @PostMapping("/novo_morador")
    public ResponseEntity criarMorador(@RequestBody MoradorEntity morador){
        Optional<MoradorEntity> moradorResponse = moradorService.criarNovoMorador(morador);

        if (moradorResponse.isPresent())
            return ResponseEntity.created(null).build();
        else
            return ResponseEntity.badRequest().build();
    }


    @GetMapping("/buscarMorador/{nomeMorador}")
    public ResponseEntity procurarMorador(@RequestParam String nomeMorador){
        Optional<MoradorEntity> moradorBuscado = moradorService.buscarMorador(nomeMorador);

        if(moradorBuscado.isPresent())
            return ResponseEntity.ok().body(moradorBuscado.toString());
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/listarMoradores")
    public ResponseEntity listarMoradores(){
        List<MoradorEntity> moradores = moradorService.buscarTodosMoradores();

        if(!moradores.isEmpty())
            return ResponseEntity.ok().body(moradores.toArray());
        else
            return ResponseEntity.notFound().build();
    }

    //    Request body
    //    {
    //        "nome": "",
    //        "sobrenome": "",
    //        "endereco": ""
    //    }
    @PutMapping("/atualizarMorador/{nomeAntigo}")
    public ResponseEntity atualizarMorador(@RequestParam String nomeMorador, @RequestBody MoradorEntity dadosAtualizados){
        Optional<MoradorEntity> moradorResponse = moradorService.atualizarMorador(nomeMorador,dadosAtualizados);

        if (moradorResponse.isPresent())
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/deletarMorador/{nomeMOrador}")
    public ResponseEntity deletarMorador(@RequestParam String nomeMorador){
        moradorService.deletarMorador(nomeMorador);

        return ResponseEntity.ok().build();
    }





}
