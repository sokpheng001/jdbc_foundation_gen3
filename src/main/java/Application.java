import controller.UserController;


public class Application {
    public static void main(String[] args) {
        new UserController().getAllUsers()
                .forEach(System.out::println);
    }
}
