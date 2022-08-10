package Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "expressions")
@Data
public class Expression {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String expression;
    private double result;

}
