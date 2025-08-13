package demo_dao_jdbc.application.model.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    @Override
    public String toString() {
        return "Department [id=" + id + ", name='" + name + " ' ]";
    }
}
