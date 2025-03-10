package model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private Integer id;
    private String uuid;
    private String orderName;
    private Date orderedDate;
    //manyTO one relationship
    private User user;
    // manyTo many
    private List<Product> products;
}
