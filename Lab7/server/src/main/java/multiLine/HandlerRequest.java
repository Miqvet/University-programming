package multiLine;

import serverConnectionClasses.Server;
import serverSystemClasses.CollectionManager;
import serverSystemClasses.CommandManager;
import transmitted.Request;
import transmitted.Response;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayDeque;
import java.util.Deque;

import static serverConnectionClasses.Server.locker;

public class HandlerRequest implements Runnable{
    private Deque<ServerBufferResponse> responseStack;
    private ServerBufferRequest request;
    private CollectionManager collectionManager;

    public HandlerRequest(Deque<ServerBufferResponse> responseStack, ServerBufferRequest serverBuffer, CollectionManager collectionManager) {
        this.responseStack = responseStack;
        this.request = serverBuffer;
        this.collectionManager = collectionManager;
    }

    @Override
    public void run() {
        locker.lock();
        Response response = CommandManager.getInstance().getCommands().
                get(request.getRequest().getCommand()).
                execute(request.getRequest().getCommandStrArgument(),
                        request.getRequest().getObjArgument(),
                        collectionManager, request.getRequest().getUser());
        ServerBufferResponse serverBufferResponse = new ServerBufferResponse(request.getWriter(),response);
        locker.unlock();
        responseStack.push(serverBufferResponse);
    }
}
