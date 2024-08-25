package org.question4.service;

import org.question4.entity.MoradorEntity;
import org.question4.repository.MoradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoradorService {

    @Autowired
    private final MoradorRepository moradorRepository;

    public MoradorService(MoradorRepository moradorRepository) {
        this.moradorRepository = moradorRepository;
    }

    public Optional<MoradorEntity> criarNovoMorador(MoradorEntity morador){
        return Optional.of(moradorRepository.save(morador));
    }

    public Optional<MoradorEntity> buscarMorador(String nome){
        return moradorRepository.findById(nome);
    }

    public List<MoradorEntity> buscarTodosMoradores(){
        return moradorRepository.findAll();
    }

    public Optional<MoradorEntity> atualizarMorador(String nome, MoradorEntity dadosAtualizadosDoMorador){
        Optional<MoradorEntity> moradorPesquisado = moradorRepository.findById(nome);
        if (moradorPesquisado.isPresent()){
            MoradorEntity moradorAtualizado = moradorPesquisado.get();
            moradorAtualizado.setNome(dadosAtualizadosDoMorador.getNome());
            moradorAtualizado.setSobrenome(dadosAtualizadosDoMorador.getSobrenome());
            moradorAtualizado.setEndereco(dadosAtualizadosDoMorador.getEndereco());
            return Optional.of(moradorRepository.save(moradorAtualizado));
        }else{
            return Optional.empty();
        }
    }

    public void deletarMorador(String nome){
        moradorRepository.deleteById(nome);
    }


}
