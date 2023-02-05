package br.net.digix.desafio.silva.daniel.domain.family.value_object.enuns;

public enum DependentGenderEnum {
    MASCULINO,
    FEMININO;

    public static DependentGenderEnum ofGender(final String gender) {
        for (DependentGenderEnum genderEnum : DependentGenderEnum.values()) {
            if (genderEnum.toString().equals(gender)) {
                return genderEnum;
            }
        }
        return null;
    }
}
