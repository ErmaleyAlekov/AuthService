package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {

    public static void addLog(String msg) {
        try {
            Files.createDirectories(Path.of("logs"));
            String date = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
            String time = new SimpleDateFormat("|HH:mm:ss| ").format(Calendar.getInstance().getTime());
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("logs"+File.separator+date+".txt",true))))
            {
                out.println(time+msg);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
