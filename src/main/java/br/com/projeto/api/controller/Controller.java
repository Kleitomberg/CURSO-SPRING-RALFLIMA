package br.com.projeto.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import br.com.projeto.api.Model.Cliente;
import br.com.projeto.api.Model.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;
import br.com.projeto.api.service.Servico;
import jakarta.validation.Valid;

@RestController
public class Controller {

    @Autowired
    private Repositorio pessoaRepositorio;

    @Autowired
    private Servico servico;


    private ResponseEntity responseEntity;

    @GetMapping("/pessoas/contador")
    public long contador() {
        return pessoaRepositorio.count();
    }

    @PostMapping("/pessoas")
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa pessoa) {
        return servico.cadastrar(pessoa);
    }

    @GetMapping("/pessoas")
    public ResponseEntity<?> listar() {
        return servico.listar();
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable int id) {
        return servico.listarPorId(id);
    }

    @PutMapping("/pessoas/{id}")
    public ResponseEntity<?> updaPessoa(@RequestBody Pessoa pessoa, @PathVariable int id) {
        pessoa.setId(id);
        return servico.editar(pessoa);
    }

    @GetMapping("/pessoas/com/j")
    public List<Pessoa> pessoascomj(){
        return pessoaRepositorio.findByNomeContaining("j");
    }

    @GetMapping("/pessoas/terminaCom")
    public List<Pessoa> terminaCom(){
        return pessoaRepositorio.findByNomeEndsWith("s");
    }

    @GetMapping("/pessoas/comecaCom")
    public List<Pessoa> comecaCom(){
        return pessoaRepositorio.findByNomeStartsWith("m");
    }

    @GetMapping("/pessoas/somaIdades")
    public int  somaIdades(){
        return pessoaRepositorio.somarIdades();
    }

    @GetMapping("/pessoas/mediaIdades")
    public double mediaIdades(){
        return pessoaRepositorio.mediaIdades();
    }

    @GetMapping("/pessoas/idadeMaiorOuIgual/{idade}")
    public List<Pessoa> idadeMaiorOuIgual(@PathVariable int idade){
        return pessoaRepositorio.idadeMaiorOrIgual(idade);
    }

    @DeleteMapping("/pessoas/{id}")
    public ResponseEntity<?> remove (@PathVariable int id) {
        return servico.excluir(id);
    }

    @GetMapping("/pessoas/status")
    public ResponseEntity<?> status() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //ROTAS Cliente

    @PostMapping("/clientes")
    public void cadastrarCliente(@Valid @RequestBody Cliente cliente) {



    }



}
