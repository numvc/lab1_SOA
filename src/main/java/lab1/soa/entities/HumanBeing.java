package lab1.soa.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "human_being")
public class HumanBeing {
    @Id
    @NotNull
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @NotNull(message = "Name cant be null")
    @NotBlank(message = "Name cant be empty string")
    private String name; //Поле не может быть null, Строка не может быть пустой


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates", nullable = false)
    private Coordinates coordinates; //Поле не может быть null


    @Column(name = "creation_date", nullable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Column(name = "real_hero")
    private boolean realHero;

    @Column(name = "has_tooth_pick", nullable = false)
    private Boolean hasToothpick; //Поле может быть null

    @NotNull(message = "Impact Speed cant be null")
    @DecimalMin(value = "-675", message = "Impact Speed cant be less than -675")
    @Column(name = "impact_speed", nullable = false)
    private Double impactSpeed; //Значение поля должно быть больше -675, Поле не может быть null

    @Column(name = "soundtrack_name", nullable = false)
    private String soundtrackName; //Поле не может быть null

    @Column(name = "minutes_of_waiting")
    private double minutesOfWaiting;

    @Column(name = "weapon_type", nullable = false)
    private WeaponType weaponType; //Поле может быть null

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car")
    private Car car; //Поле может быть null
}