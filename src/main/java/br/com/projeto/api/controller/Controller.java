package br.com.projeto.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import br.com.projeto.api.Model.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;

@RestController
public class Controller {

    @Autowired
    private Repositorio pessoaRepositorio;

    @GetMapping("/pessoas/contador")
    public long contador() {
        return pessoaRepositorio.count();
    }

    @PostMapping("/pessoas")
    public Pessoa cadastrar(@RequestBody Pessoa pessoa) {
        return pessoaRepositorio.save(pessoa);
    }

    @GetMapping("/pessoas")
    public List<Pessoa> listar() {
        return pessoaRepositorio.findByOrderByNomeDesc();
    }

    @GetMapping("/pessoas/{id}")
    public Pessoa listarPorId(@PathVariable int id) {
        return pessoaRepositorio.findById(id);
    }

    @PutMapping("/pessoas/{id}")
    public Pessoa updaPessoa(@RequestBody Pessoa pessoa, @PathVariable int id) {
        pessoa.setId(id);
        return pessoaRepositorio.save(pessoa);
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
    public Void remove (@PathVariable int id) {
        Pessoa pessoa = pessoaRepositorio.findById(id);
        pessoaRepositorio.delete(pessoa);
        return null;
    }

    @GetMapping("/pessoas/status")
    public String status() {
        return "API funcionando";
    }



}
