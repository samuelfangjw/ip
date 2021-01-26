package duke.exceptions;

public class InvalidInputException extends DukeException {
    public InvalidInputException() {
        super("Please enter a valid index :(");
    }

    public InvalidInputException(int maxIdx) {
        super("Please enter a valid index between 1 and " + maxIdx + " :(");
    }
}