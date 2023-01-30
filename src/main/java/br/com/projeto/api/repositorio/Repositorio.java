package br.com.projeto.api.repositorio;

import java.util.List;

import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.api.Model.Pessoa;

@Repository
public interface Repositorio extends CrudRepository<Pessoa, Integer> {


    List<Pessoa> findByOrderByNomeDesc();
    Pessoa findById(int id);
    Pessoa findByNome(String nome);
    List<Pessoa> findByIdade(int idade);
    List<Pessoa> findByNomeContaining(String nome);
    List<Pessoa> findByNomeStartsWith(String nome);
    List<Pessoa> findByNomeEndsWith(String nome);

    @Query(value = "SELECT SUM(idade) FROM pessoas WHERE nome = 'Maria' OR nome = 'joão'", nativeQuery =true)
    int somarIdades();

    @Query(value = "SELECT AVG(idade) FROM pessoas", nativeQuery =true)
    double mediaIdades();

    @Query(value = "SELECT * FROM pessoas WHERE idade >= ?1", nativeQuery =true)
    List<Pessoa> idadeMaiorOrIgual(int idade);

}
