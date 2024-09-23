package com.softuni.client.domain.entity;

import com.softuni.client.domain.entity.enums.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseEntity{
    @Column
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}
