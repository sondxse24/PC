package com.pc.model.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    @Nationalized
    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "star", nullable = false)
    @Min(1)
    @Max(5)
    private int star;
}
