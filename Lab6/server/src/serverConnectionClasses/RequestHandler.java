package serverConnectionClasses;


import serverSystemClasses.CollectionManager;
import serverSystemClasses.CommandManager;
import transmitted.Request;
import transmitted.Response;

public class RequestHandler {
    /**
     * Handles requests.
     *
     * @param request Request to be processed.
     * @return Response to request.
     */
    public static Response convertRequest(Request request, CollectionManager collectionManager) {
        Response response = CommandManager.getCommands().get(request.getCommand()).
                execute(request.getCommandStrArgument(), request.getObjArgument(), collectionManager);
        return response;
    }
}
