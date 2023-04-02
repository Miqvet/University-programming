package transmitted;

import java.io.Serializable;

public class Request implements Serializable {
    private String command;
    private String commandStrArgument;
    private MarinBuffer commandObjArgument;
    public Request(String command, String commandStrArgument, MarinBuffer commandObjArgument){
        this.commandObjArgument = commandObjArgument;
        this.commandStrArgument = commandStrArgument;
        this.command = command;
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
        return command.isEmpty() && commandStrArgument.isEmpty() && commandObjArgument == null;
    }
}
