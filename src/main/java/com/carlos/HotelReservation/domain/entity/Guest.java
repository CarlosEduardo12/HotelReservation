package com.carlos.HotelReservation.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Getter @Setter
public class Guest {

    @Id
    @GeneratedValue(generator = "uuid4")
    private UUID id;

    @Email @NotBlank
    private String email;

    @NotBlank
    private String password;

    @Deprecated
    public Guest() {
    }

    public Guest(String email, String password) {
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
    }
}
