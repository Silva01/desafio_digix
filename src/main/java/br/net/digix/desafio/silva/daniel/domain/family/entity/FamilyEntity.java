package br.net.digix.desafio.silva.daniel.domain.family.entity;

import br.net.digix.desafio.silva.daniel.domain.family.value_object.Dependent;

import java.math.BigDecimal;
import java.util.List;

public class FamilyEntity {
    private String id;
    private String name;
    private BigDecimal income;
    private List<Dependent> dependents;
    private int points;

    public FamilyEntity(BigDecimal income, String name, List<Dependent> dependents) {
        this.id = "123";
        this.income = income;
        this.name = name;
        this.dependents = dependents;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public List<Dependent> getDependents() {
        return dependents;
    }

    public void addPoints(int points) {
        this.points += points;
    }
    public int getPoints() {
        return points;
    }
}
