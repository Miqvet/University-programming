package transmitted;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    private final User user;
    private final String command;
    private final String commandStrArgument;
    private final MarinBuffer commandObjArgument;
    public Request(String command, String commandStrArgument, MarinBuffer commandObjArgument, User user){
        this.commandObjArgument = commandObjArgument;
        this.commandStrArgument = commandStrArgument;
        this.command = command;
        this.user = user;
    }

    public String getCommand() {
        return command;
    }

    public String getCommandStrArgument() {
        return commandStrArgument;
    }

    public MarinBuffer getObjArgument() {
        return commandObjArgument;
    }
    public boolean isEmpty() {
        return this.command == null;
    }

    public User getUser() {
        return user;
    }

}
