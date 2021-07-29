package edu.labIV.validator;

import edu.labIV.entity.User;
import edu.labIV.exception.InvalidLastNameException;
import edu.labIV.exception.InvalidNameException;
import edu.labIV.exception.InvalidUserBirthDateException;
import edu.labIV.exception.UserException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {

    private final UserValidator userValidator = new UserValidator();

    @Test
    void validUser(){
        User user = new User("Mauro", "Pignatta", LocalDate.of(1994,10,22));
        assertDoesNotThrow(() -> userValidator.validateUser(user));
    }

    @Test
    void validName(){
        String name = "Mauro";
        assertDoesNotThrow(() -> userValidator.validateName(name));
    }

    @Test
    void validLastName(){
        String name = "Pignatta";
        assertDoesNotThrow(() -> userValidator.validateName(name));
    }

    @Test
    void validBirthdate(){
        LocalDate birthdate = LocalDate.of(2000, 1, 1);
        assertDoesNotThrow(() -> userValidator.validateBirthDate(birthdate));
    }

    @Test
    void invalidUserName(){
        User user = new User("Mauro?", "Pignatta", LocalDate.of(1994,10,22));
        assertThrows(InvalidNameException.class, () -> userValidator.validateUser(user));
    }

    @Test
    void invalidUserLastName(){
        User user = new User("Mauro", "Pigna-tta", LocalDate.of(1994,10,22));
        assertThrows(InvalidLastNameException.class, () -> userValidator.validateUser(user));
    }

    @Test
    void invalidUserBirthdate(){
        User user = new User("Mauro", "Pignatta", LocalDate.of(2021,10,22));
        assertThrows(InvalidUserBirthDateException.class, () -> userValidator.validateUser(user));
    }

    @Test
    void invalidNameWithNumbers(){
        String name = "mauro1234";
        assertThrows(InvalidNameException.class, () -> userValidator.validateName(name));
    }

    @Test
    void invalidNameEmpty(){
        String name = "";
        assertThrows(InvalidNameException.class, () -> userValidator.validateName(name));
    }

    @Test
    void invalidLastNameWithNumbers(){
        String lastname = "mauro1234";
        assertThrows(InvalidLastNameException.class, () -> userValidator.validateLastName(lastname));
    }

    @Test
    void invalidLastNameEmpty(){
        String lastname = "";
        assertThrows(InvalidLastNameException.class, () -> userValidator.validateLastName(lastname));
    }

    @Test
    void invalidBirthAfterNow(){
        LocalDate date = LocalDate.of(2021, 11, 24);
        assertThrows(InvalidUserBirthDateException.class, () -> userValidator.validateBirthDate(date));
    }

    @Test
    void invalidProfilePicturePath(){
        String path = ("pepe"); // No existe el directorio.
        assertThrows(UserException.class, () -> userValidator.validatePicturePath(path));
    }

    @Test
    void invalidProfilePicture(){
        String path = ("res/mail/activation.html"); // No es una imagen.
        assertThrows(UserException.class, () -> userValidator.validatePicturePath(path));
    }

    @Test
    void validProfilePictureNull(){
        String path = (null); // Null es un valor valido.
        assertDoesNotThrow(() -> userValidator.validatePicturePath(path));
    }

    @Test
    void validProfilePictureEmpty(){
        String path = (""); // es valida una cadena vacia.
        assertDoesNotThrow(() -> userValidator.validatePicturePath(path));
    }

    @Test
    void validProfilePicture(){
        String path = ("res/logo/logo.jpg"); // La imagen esta en un formato correcto.
        assertDoesNotThrow(() -> userValidator.validatePicturePath(path));
    }

}