package web.app.api;

import java.io.IOException;

public class JarProgramStart {


    Process proc;
    public JarProgramStart() {
        this.proc = startExtJarProgram();
    }

    public Process startExtJarProgram() {
        try {
            String path = "C:\\ path to the file \\audio-streaming-service-1.0.0.jar";
            proc = Runtime.getRuntime().exec("java -jar " + path);
        return proc;
        } catch (IOException e) {
            System.out.println("External Jar Start Failed.");
            throw new RuntimeException(e);
        }
    }

    public void stopExtJarProcess() {
        proc.destroy();
    }
}
