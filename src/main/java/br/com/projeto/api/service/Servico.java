package br.com.projeto.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.Model.Mensagem;
import br.com.projeto.api.Model.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;

@Service
public class Servico {

    @Autowired
    private Mensagem mensagem;


    @Autowired
    private Repositorio pessoaRepositorio;

    //POST pessoas

    public ResponseEntity<?> cadastrar(Pessoa p){

        if (p.getNome().equals("")){
            mensagem.setMensagem("Nome não pode ser vazio");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (p.getIdade() <= 0){
            mensagem.setMensagem("Idade não pode ser menor ou igual a zero");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        else {
            mensagem.setMensagem("Pessoa cadastrada com sucesso");
            return new ResponseEntity<>(pessoaRepositorio.save(p), HttpStatus.CREATED);
        }
    }

    //GET pessoas

    public ResponseEntity<?> listar(){
        return new ResponseEntity<>(pessoaRepositorio.findAll(), HttpStatus.OK);
    }


    //GET pessoas/{id}

    public ResponseEntity<?> listarPorId(int id){

        if (pessoaRepositorio.findById(id) == null){
            mensagem.setMensagem("Pessoa não encontrada com o id: " + id);
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(pessoaRepositorio.findById(id), HttpStatus.OK);
        }
    }

    // PUT pessoas/{id}

    public ResponseEntity<?> editar(Pessoa pessoa){

        if (pessoaRepositorio.findById(pessoa.getId()) == null){
            mensagem.setMensagem("Pessoa não encontrada com o id: " + pessoa.getId());
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);

        } else if (pessoa.getNome().equals("")) {
            mensagem.setMensagem("Nome não pode ser vazio");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);

        } else if (pessoa.getIdade() <= 0){
            mensagem.setMensagem("Idade não pode ser menor ou igual a zero");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);

        }

        else {
            return new ResponseEntity<>(pessoaRepositorio.save(pessoa), HttpStatus.OK);
        }

    }

    // DELETE pessoas/{id}

    public ResponseEntity<?> excluir (int  id){

            if (pessoaRepositorio.findById(id) == null){
                mensagem.setMensagem("Pessoa não encontrada com o id: " + id);
                return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
            } else {
                Pessoa pessoa = pessoaRepositorio.findById(id);
                pessoaRepositorio.delete(pessoa);
                mensagem.setMensagem("Pessoa excluída com sucesso");
                return new ResponseEntity<>(mensagem, HttpStatus.OK);
            }
    }

}

