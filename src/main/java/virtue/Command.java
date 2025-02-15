package virtue;

/** The Command that can be generated by inputs in the chatbot. */
public class Command {
    /** The available types of commands in Virtue. */
    public enum CommandType {
        BYE("bye", false, false),
        LIST("list", false, false),
        MARK("mark", true, false),
        UNMARK("unmark", true, false),
        TODO("todo", false, true),
        DEADLINE("deadline", false, true),
        EVENT("event", false, true),
        DELETE("delete", true, false),
        FIND("find", false, true);

        private final String string;
        private final boolean hasIndex;
        private final boolean hasDescription;

        CommandType(String string,
                    boolean hasIndex,
                    boolean hasDescription) {
            this.string = string;
            this.hasIndex = hasIndex;
            this.hasDescription = hasDescription;
        }

        @Override
        public String toString() {
            return this.string;
        }
    }

    protected CommandType type;
    protected Integer[] indices;
    protected String description;
    protected VirtueDateTime by;
    protected VirtueDateTime from;
    protected VirtueDateTime to;
    private String resultMessage;


    /**
     * Creates a command from the user input.
     *
     * @param input The string input by the user.
     * @throws VirtueException If the string cannot be parsed into a command.
     */
    public Command(String input) throws VirtueException {
        this.type = Parser.getCommandType(input);

        if (this.type.hasIndex) {
            this.indices = Parser.getIndices(input);
        }

        if (this.type.hasDescription) {
            this.description = Parser.getDescription(input);
        }

        if (this.type == CommandType.DEADLINE) {
            this.by = Parser.getBy(input);
        }

        if (this.type == CommandType.EVENT) {
            this.from = Parser.getFrom(input);
            this.to = Parser.getTo(input);
        }
    }

    /**
     * Checks if a command is of type "bye".
     *
     * @return True if the command is a "bye", false if not.
     */
    public boolean isBye() {
        return type == CommandType.BYE;
    }

    /**
     * Sets the result message of the command.
     *
     * @param message The result message generated by the command.
     */
    public void setResultMessage(String message) {
        resultMessage = message;
    }

    /**
     * Gets the result message of the command.
     *
     * @return The result message of the command.
     */
    public String getResultMessage() {
        return resultMessage;
    }
}
