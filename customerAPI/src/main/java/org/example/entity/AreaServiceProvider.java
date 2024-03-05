package org.example.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Setter
@Getter
@Entity
@Table(name = "area_service_provider")
public class AreaServiceProvider implements Serializable {
    @Id
    private long zipcode;
    private String name;
    private String logo;
}
