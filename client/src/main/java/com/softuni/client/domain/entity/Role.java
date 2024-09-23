package com.softuni.client.domain.entity;

import com.softuni.client.domain.entity.enums.RoleType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseEntity{
    private RoleType roleType;
}
