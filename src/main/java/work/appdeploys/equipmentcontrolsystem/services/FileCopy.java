package work.appdeploys.equipmentcontrolsystem.services;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

@Component
public class FileCopy {
    public void copyFile(File file, OutputStream outputStream) throws IOException {
        Files.copy(file.toPath(),outputStream);
    }
}
