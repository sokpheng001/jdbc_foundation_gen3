import model.entity.User;
import model.repository.UserRepository;
import utils.PasswordManager;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

public class Application {
    public static void main(String[] args) {
//        User user = User.builder()
//                .id(new Random().nextInt(99999))
//                .uuid(UUID.randomUUID().toString())
//                .userName("Daron")
//                .email("sanom123@gmail.com")
//                .password(PasswordManager.hashing("123"))
//                .createdDate(Date.valueOf(LocalDate.now()))
//                .isDeleted(false)
//                .build();
//        new UserRepository().save(user);
        System.out.println(PasswordManager.hashing("123"));
        System.out.println(PasswordManager.hashing("123"));
    }
}
