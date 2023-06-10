package com.icesi.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;


@Entity
@Data
@Builder
@Table(name = "user_permission")
@NoArgsConstructor
@AllArgsConstructor
public class PermissionUser {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID permissionId;

    private String uri;

    private String permissionKey;

    @Column(name = "permission_method")
    private String method;


}
