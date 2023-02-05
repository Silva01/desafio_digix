package br.net.digix.desafio.silva.daniel.domain.family.value_object.enuns;

public enum CriteriaTypeEnum {
    DEPENDENT_AGE,
    INCOME,
    IS_WORKING;

    public static CriteriaTypeEnum ofCriteria(String type) {
        for (CriteriaTypeEnum criteriaTypeEnum : CriteriaTypeEnum.values()) {
            if (criteriaTypeEnum.toString().equals(type)) {
                return criteriaTypeEnum;
            }
        }
        return null;
    }
}
