package com.produtosapi.produtosapi.controller;

import com.produtosapi.produtosapi.model.Produto;
import com.produtosapi.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
    // o repositorio(instancia da entidade)
    private ProdutoRepository produtoRepository;
    //  controlador
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        System.out.println("Produto recebido com sucesso: " + produto.toString());
       //cria uma vareavel para poder instanciar um recurso nativo que e o uniqueID, chamando o metodo e o transformando em uma string pois nossa entidade no banco e uma string
        var id = UUID.randomUUID().toString();
        // aqui ee setado o valor no produto no atributo id
        produto.setId(id);
        // aqui e salvo o valor recebido via URL playload do postman no repository para salvar no banco
        produtoRepository.save(produto);
        return produto;
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable("id") String id) {
        // se passar um id existente ele retornaria o id, se caso o contrario nao retornaria por isso do optional
        Optional<Produto> produto = produtoRepository.findById(id);
        return produtoRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable("id") String id) {
        produtoRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public void atualizar(@PathVariable("id")String id,@RequestBody Produto produto) {
        produto.setId(id);
        produtoRepository.save(produto);
    }

    @GetMapping
    public List<Produto> buscaPorNome(@RequestParam("nome") String nome) {
       return produtoRepository.findByNome(nome);
    }
}
