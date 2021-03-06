package chip.utils;

import chip.exceptions.UnknownCommandException;
import chip.parser.Parser;

/**
 * Formats help messages.
 */
public class HelpMessages {
    private static final String[] AVAILABLE_COMMANDS =
        {"\tbye", "\tdeadline", "\tdelete", "\tdone", "\tevent", "\thelp", "\tfind", "\tlist", "\ttodo"};
    private static final String BYE_MESSAGE =
        "Type \"bye\" to exit the application. Don't worry I'll remember your tasks!";
    private static final String DEADLINE_MESSAGE =
        "Keep track of your deadlines by typing the following:\n"
            + "\"deadline DESCRIPTION /by DATE (TIME)\"\n"
            + "DATE should be in yyyy-mm-dd format\n"
            + "TIME is optional and should be in hh:mm format";
    private static final String DELETE_MESSAGE =
        "Type \"delete TASK_NUMBER\" to delete a task forever. "
            + "If you are not sure of the task number, try listing out your tasks.";
    private static final String DONE_MESSAGE =
        "Type \"done TASK_NUMBER\" and I'll mark your task as done. "
            + "If you are not sure of the task number, try listing out your tasks.";
    private static final String EVENT_MESSAGE =
        "Keep track of your events by typing the following:\n"
            + "\"event DESCRIPTION /at DATE (TIME)\"\n"
            + "DATE should be in yyyy-mm-dd format\n"
            + "TIME is optional and should be in hh:mm format";
    private static final String HELP_MESSAGE =
        "Type \"help (COMMAND)\" to see get more information about a command. \nAvailable commands:\n";
    private static final String FIND_MESSAGE =
        "Let me know which tasks you are looking for by typing the following:\n"
            + "\"find SEARCH_PARAMETERS...\"\n"
            + "You may specify more than one space separated search parameters to search for.\n"
            + "I'll look through the tasks and see if there are any matches.";
    private static final String LIST_MESSAGE = "Type \"list\" to see a list of tasks you currently have.";
    private static final String TODO_MESSAGE =
        "Keep track of your todos by typing the following:\n"
            + "\"todo DESCRIPTION\"";
    private static final String UNKNOWN_MESSAGE = "I do not understand that command. Try one of these instead:\n";

    /**
     * Returns a help message based on the command that is requested.
     *
     * @param inputString command that the user has requested help on.
     * @return message detailing help for the command requested.
     */
    public static String getMessage(String inputString) {
        if (inputString.equals("")) {
            return HELP_MESSAGE + String.join("\n", AVAILABLE_COMMANDS);
        }

        Command command;

        try {
            command = Parser.parseCommand(inputString.trim().toUpperCase());
        } catch (UnknownCommandException e) {
            return UNKNOWN_MESSAGE + String.join("\n", AVAILABLE_COMMANDS);
        }

        String message = null;

        switch (command) {
        case BYE:
            message = BYE_MESSAGE;
            break;
        case DONE:
            message = DONE_MESSAGE;
            break;
        case DELETE:
            message = DELETE_MESSAGE;
            break;
        case FIND:
            message = FIND_MESSAGE;
            break;
        case LIST:
            message = LIST_MESSAGE;
            break;
        case HELP:
            message = HELP_MESSAGE + String.join("\n", AVAILABLE_COMMANDS);
            break;
        case TODO:
            message = TODO_MESSAGE;
            break;
        case DEADLINE:
            message = DEADLINE_MESSAGE;
            break;
        case EVENT:
            message = EVENT_MESSAGE;
            break;
        default:
            assert false : "command not recognised";
        }

        return message;
    }
}
