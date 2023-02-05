package br.net.digix.desafio.silva.daniel.domain.family.value_object;

import br.net.digix.desafio.silva.daniel.domain.family.value_object.enuns.DependentGenderEnum;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.enuns.DependentTypeEnum;

import java.time.LocalDate;

public class Dependent {
    private String name;
    private LocalDate birthDate;
    private boolean isWork;
    private DependentTypeEnum type;
    private DependentGenderEnum gender;

    public Dependent(String name, LocalDate birthDate, boolean isWork, DependentTypeEnum type, DependentGenderEnum gender) {
        this.name = name;
        this.birthDate = birthDate;
        this.isWork = isWork;
        this.type = type;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public boolean isWork() {
        return isWork;
    }

    public DependentTypeEnum getType() {
        return type;
    }

    public DependentGenderEnum getGender() {
        return gender;
    }
}
