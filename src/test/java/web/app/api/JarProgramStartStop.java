package web.app.api;

import java.io.IOException;

public class JarProgramStartStop {
    static ThreadLocal<Process> local = new ThreadLocal<>();

    public JarProgramStartStop() {
    }

    public static void startExtJarProgram() {
        if (local.get() == null) {
            try {
                String path = "C:\\Users\\ma_ka\\IdeaProjects\\src\\audio-streaming-service-1.0.0.jar";
                local.set(Runtime.getRuntime().exec("java -jar " + path));
            } catch (IOException e) {
                System.out.println("External Jar Start Failed.");
                throw new RuntimeException(e);
            }
        }
    }

    public static void stopExtJarProcess() {
        if (local.get() != null) {
            local.remove();
        }
    }
}
