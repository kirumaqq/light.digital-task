package io.umid.supportservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@EqualsAndHashCode(of = "name")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles", schema = "public")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "name", nullable = false, unique = true)
    private Roles name;
    @Override
    public String getAuthority() {
        return name.withRolePrefix();
    }

    @Override
    public String toString() {
        return name.name();
    }
}
