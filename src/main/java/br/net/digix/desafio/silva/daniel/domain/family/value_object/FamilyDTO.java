package br.net.digix.desafio.silva.daniel.domain.family.value_object;

import java.math.BigDecimal;
import java.util.List;

public class FamilyDTO {
    private String name;
    private BigDecimal income;
    private List<Dependent> dependents;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public List<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }
}
