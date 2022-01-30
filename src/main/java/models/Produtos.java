package models;

import com.google.gson.JsonObject;

public class Produtos {

    public String nome;
    public Integer preco;
    public String descricao;
    public Integer quantidade;
    public String _id;

    public Produtos(String nome, Integer preco, String descricao, Integer quantidade, String _id) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this._id = _id;
    }

    public void setProdutosId(String produto_id){
        this._id = produto_id;
    }
    public  String getProdutosCredentials(){
        JsonObject produtoJsonRepresentation = new JsonObject();
        produtoJsonRepresentation.addProperty("nome",this.nome);
        produtoJsonRepresentation.addProperty("preco", this.preco);
        produtoJsonRepresentation.addProperty("descricao",this.descricao);
        produtoJsonRepresentation.addProperty("quantidade", this.quantidade);
        produtoJsonRepresentation.addProperty("_id", this._id);
        return produtoJsonRepresentation.toString();

    }
}
