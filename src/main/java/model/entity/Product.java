package model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String uuid;
    private String productName;
    private String description;
    private Date expiredDate;
    private Double price;
    private String image;
}
