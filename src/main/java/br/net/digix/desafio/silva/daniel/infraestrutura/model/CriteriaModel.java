package br.net.digix.desafio.silva.daniel.infraestrutura.model;

import javax.persistence.*;

@Entity
@Table(name = "criteria")
public class CriteriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "initial_criteria")
    private Integer initialCriteria;

    @Column(name = "final_criteria")
    private Integer finalCriteria;

    @Column(name = "is_actived")
    private boolean isActived;

    @Column(name = "description")
    private String description;

    @Column(name = "points")
    private int points;

    @Column(name = "minimum_repeat_criteria")
    private Integer minimumRepeatCriteria;

    @Column(name = "maximum_repeat_criteria")
    private Integer maximumRepeatCriteria;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getInitialCriteria() {
        return initialCriteria;
    }

    public void setInitialCriteria(Integer initialCriteria) {
        this.initialCriteria = initialCriteria;
    }

    public Integer getFinalCriteria() {
        return finalCriteria;
    }

    public void setFinalCriteria(Integer finalCriteria) {
        this.finalCriteria = finalCriteria;
    }

    public boolean isActived() {
        return isActived;
    }

    public void setActived(boolean actived) {
        isActived = actived;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Integer getMinimumRepeatCriteria() {
        return minimumRepeatCriteria;
    }

    public void setMinimumRepeatCriteria(Integer minimumRepeatCriteria) {
        this.minimumRepeatCriteria = minimumRepeatCriteria;
    }

    public Integer getMaximumRepeatCriteria() {
        return maximumRepeatCriteria;
    }

    public void setMaximumRepeatCriteria(Integer maximumRepeatCriteria) {
        this.maximumRepeatCriteria = maximumRepeatCriteria;
    }
}
