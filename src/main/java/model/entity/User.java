package model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Integer id;
    private String uuid;
    private String userName;
    private String email;
    private String password;
    private String profile;
    private Boolean isDeleted;
    private Date createdDate;
}
