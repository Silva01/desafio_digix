package br.net.digix.desafio.silva.daniel.domain.family.entity;

import br.net.digix.desafio.silva.daniel.domain.family.exception.CpfJaCadastradoException;
import br.net.digix.desafio.silva.daniel.domain.family.exception.FamilyConjugeNotValidException;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.Dependent;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.FamilyDTO;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.enuns.DependentTypeEnum;

import java.math.BigDecimal;
import java.util.List;

public class FamilyEntity {
    private String cpf;
    private String name;
    private BigDecimal income;
    private List<Dependent> dependents;
    private int points;

    public FamilyEntity(String cpf, BigDecimal income, String name, List<Dependent> dependents) {
        this.income = income;
        this.name = name;
        this.dependents = dependents;
        this.cpf = cpf;
        this.validate();
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

    public String getCpf() {
        return cpf;
    }

    public void addPoints(int points) {
        this.points += points;
    }
    public int getPoints() {
        return points;
    }

    public FamilyDTO toDTO() {
        FamilyDTO dto = new FamilyDTO();
        dto.setName(this.name);
        dto.setIncome(this.income);
        dto.setDependents(this.dependents);
        dto.setPoints(this.points);
        dto.setCpf(this.cpf);
        return dto;
    }

    public void validate() {
        if (this.cpf == null) {
            throw new CpfJaCadastradoException();
        }

        if (this.dependents.stream().filter(d -> d.getType().equals(DependentTypeEnum.CONJUGE)).count() > 1) {
            throw new FamilyConjugeNotValidException("Familia com mais de um conjuge");
        }

        if (this.dependents.stream().filter(d -> d.getType().equals(DependentTypeEnum.CONJUGE)).count() == 0) {
            throw new FamilyConjugeNotValidException("Familia sem conjuge");
        }
    }
}
