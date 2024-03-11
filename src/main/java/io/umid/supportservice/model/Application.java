package io.umid.supportservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "applications", schema = "public")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "text")
    private String text;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "status", nullable = false)
    private ApplicationStatus status;

    private Date createdAt;


    public Application(Integer id, String name, String text, String phoneNumber, User user, ApplicationStatus status) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.phoneNumber = phoneNumber;
        this.user = user;
        this.status = status;
        this.createdAt = Date.valueOf(LocalDate.now());
    }

    public Application() {
        this.createdAt = Date.valueOf(LocalDate.now());
    }
}
