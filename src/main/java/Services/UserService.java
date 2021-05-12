package Services;

import Exceptions.UsernameAlreadyExistsException;
import Model.Event;
import Model.User;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import Services.FileSystemService;
import static Services.FileSystemService.getPathToFile;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class UserService {
    private static int maxUID;

    private static User mainUser;
    private static ObjectRepository<User> userRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("Summer-Planing-Aplication-Users.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
        maxUID = setMaxUID();
    }

    private static int setMaxUID(){
        int m = 0;
        for(User user : userRepository.find()){
            if(user.getUserID() != null)
                if(m < Integer.parseInt(user.getUserID())) m = Integer.parseInt(user.getUserID());
        }
        return m;
    }

    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        User user = new User(username, encodePassword(username, password), role);
        user.setUserID(String.valueOf(++maxUID));
        userRepository.insert(user);
    }

    public static boolean checkUserCredentials(String username, String password, String role){
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()) && Objects.equals(encodePassword(username, password), user.getPassword()) && Objects.equals(role, user.getRole()))
                return true;
        }
        return false;
    }

    public static User getUser(String username, String password, String role){
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()) && Objects.equals(encodePassword(username, password), user.getPassword()) && Objects.equals(role, user.getRole()))
                return user;
        }
        return null;
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public static void setMainUser(User user){mainUser = user;}
    public static User getMainUser(){return mainUser;}
}

