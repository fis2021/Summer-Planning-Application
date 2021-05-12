package Validators;

import Exceptions.InvalidEventDetailsException;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ValidateEventDetails {

    public void validateAll(String name, String imagePath, String price, LocalDate date) throws InvalidEventDetailsException {
        validateName(name);
        validateDate(date);
        validatePrice(price);
        validateImagePath(imagePath);
    }

    private void validatePrice(String price) throws InvalidEventDetailsException {
        try {
            Double doub = Double.parseDouble(price);
        }catch (NumberFormatException ex) {
            throw new InvalidEventDetailsException("Invalid Price");
        }
    }

    private void validateImagePath(String imagePath) throws InvalidEventDetailsException {
        try {
            File file = new File(imagePath);
            if (!file.exists()) throw new InvalidEventDetailsException("Invalid Image");
        }
        catch (NullPointerException e){
            throw new InvalidEventDetailsException("Invalid Image");
        }
    }

    private void validateDate(LocalDate date) throws InvalidEventDetailsException {
        try{
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            String dateString = date.format(dateFormatter);
        }
        catch(java.time.DateTimeException e){
            throw new InvalidEventDetailsException("Invalid Date");
        }
        catch (NullPointerException e) {
            throw new InvalidEventDetailsException("Invalid Date");
        }
    }

    private void validateName(String name) throws InvalidEventDetailsException{
        if(name.length() < 1) throw new InvalidEventDetailsException("Invalid Name");
    }
}
