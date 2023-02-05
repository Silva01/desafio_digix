package br.net.digix.desafio.silva.daniel.domain.family.value_object.enuns;

public enum DependentTypeEnum {
    FILHO,
    CONJUGE,
    PAI,
    MAE;

    public static DependentTypeEnum ofType(final String type) {
        for (DependentTypeEnum genderEnum : DependentTypeEnum.values()) {
            if (genderEnum.toString().equals(type)) {
                return genderEnum;
            }
        }
        return null;
    }
}
