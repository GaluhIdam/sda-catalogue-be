package sda.catalogue.sdacataloguerestapi.modules.WebApp.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.UuidGenerator;
import sda.catalogue.sdacataloguerestapi.modules.TypeDatabase.Entities.TypeDatabaseEntity;

import java.util.UUID;

@Entity
@Immutable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tb_database")
public class DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_database")
    private long idWebapp;

    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "api_name")
    private String apiName;

    @Column(name = "ip_address")
    private String apiAddress;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_webapp")
    @JsonIgnore
    private WebAppEntity webAppEntity;

    @ManyToOne
    @JoinColumn(name = "id_type_database")
    @JsonIgnore
    private TypeDatabaseEntity typeDatabaseEntity;
}
