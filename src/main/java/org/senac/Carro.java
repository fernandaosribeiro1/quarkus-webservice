package org.senac;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Carro extends PanacheEntity {
    public String marca;
    public String modelo;
    public int anoFabricacao;
    public String tipoCombustivel;
    public double preco;

    public Carro() {
    }

    public Carro(String marca, String modelo, int anoFabricacao, String tipoCombustivel, double preco) {
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.tipoCombustivel = tipoCombustivel;
        this.preco = preco;
    }
}