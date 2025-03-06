package model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private String uuid;
    private String orderName;
    private Date orderedDate;
}
