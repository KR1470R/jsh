package kript.jsh.main.commands;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;

public class CommandDispatcher {
    private final HashMap<String, Method> commands = new HashMap<>();

    public void add(Class<?> handler) {
        Method[] methods = handler.getMethods();
        for(Method method : methods) {
            if(method.isAnnotationPresent(Command.class)) {
                Parameter[] parameters = method.getParameters();
                if(parameters.length != 1 || parameters[0].getType() != List.class) {
                    continue;
                }

                Command annotation = method.getAnnotation(Command.class);
                commands.put(annotation.name(), method);

                for(String alias : annotation.alias()) {
                    commands.put(alias, method);
                }
            }
        }
    }

    public void dispatch(List<String> argv) throws Exception {
        if(argv.size() < 1) {
            throw new Exception("Empty command");
        } else {
            String commandName = argv.get(0);
            Method handler = commands.get(commandName);
            if(handler == null) {
                throw new Exception("Unknown command");
            } else {
                handler.invoke(null, argv);
            }
        }
    }
}
