package com.residential.app.entity.embeddablesId;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;


@Embeddable
public record CondominiumId(String name, String address){}
