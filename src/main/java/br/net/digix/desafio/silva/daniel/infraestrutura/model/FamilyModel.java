package br.net.digix.desafio.silva.daniel.infraestrutura.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "family")
public class FamilyModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "name")
    private String name;

    @Column(name = "income")
    private BigDecimal income;

    @Column(name = "points")
    private int points;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dependent_id")
    private List<DependentModel> dependents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<DependentModel> getDependents() {
        return dependents;
    }

    public void setDependents(List<DependentModel> dependents) {
        this.dependents = dependents;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
