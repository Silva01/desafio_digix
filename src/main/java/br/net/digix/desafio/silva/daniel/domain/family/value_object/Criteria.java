package br.net.digix.desafio.silva.daniel.domain.family.value_object;

import br.net.digix.desafio.silva.daniel.domain.family.value_object.enuns.CriteriaTypeEnum;

public class Criteria {
    private final CriteriaTypeEnum type;
    private final Integer initialCriteria;
    private final Integer finalCriteria;

    private final boolean isActived;

    private final String description;
    private final Integer points;
    private final Integer repeatQuantityMinimumValue;
    private final Integer repeatQuantityMaximumValue;

    public Criteria(
            final CriteriaTypeEnum type,
            final Integer initialCriteria,
            final Integer finalCriteria,
            final boolean isActived,
            final String description,
            final Integer points,
            final Integer repeatQuantityMinimumValue,
            final Integer repeatQuantityMaximumValue
    ) {
        this.type = type;
        this.initialCriteria = initialCriteria;
        this.finalCriteria = finalCriteria;
        this.isActived = isActived;
        this.description = description;
        this.points = points;
        this.repeatQuantityMinimumValue = repeatQuantityMinimumValue;
        this.repeatQuantityMaximumValue = repeatQuantityMaximumValue;
    }

    public CriteriaTypeEnum getType() {
        return type;
    }

    public Integer getInitialCriteria() {
        return initialCriteria;
    }

    public Integer getFinalCriteria() {
        return finalCriteria;
    }

    public boolean isActived() {
        return isActived;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPoints() {
        return points;
    }

    public Integer getRepeatQuantityMinimumValue() {
        return repeatQuantityMinimumValue;
    }

    public Integer getRepeatQuantityMaximumValue() {
        return repeatQuantityMaximumValue;
    }
}
