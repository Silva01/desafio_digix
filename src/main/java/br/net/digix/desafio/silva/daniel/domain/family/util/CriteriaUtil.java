package br.net.digix.desafio.silva.daniel.domain.family.util;

public class CriteriaUtil {

    public static boolean isMinorAndEqualsWithNull(final Integer value, final Integer criteriaValue) {
        return criteriaValue == null || value <= criteriaValue;
    }

    public static boolean isMajorAndEqualsWithNull(final Integer value, final Integer criteriaValue) {
        return criteriaValue == null || value >= criteriaValue;
    }
}
