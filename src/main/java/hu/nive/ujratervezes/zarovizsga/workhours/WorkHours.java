package hu.nive.ujratervezes.zarovizsga.workhours;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkHours {

  public String minWork(String file) {
    try (BufferedReader reader = Files.newBufferedReader(Path.of(file))) {
      String result = null;
      result = getMinWorkedDay(reader, result);
      return result;
    } catch (IOException ioe) {
      throw new IllegalStateException("Can not read file!", ioe);
    }
  }

  private String getMinWorkedDay(BufferedReader reader, String result) throws IOException {
    int minWorkedHours = 24;
    String line;
    while ((line = reader.readLine()) != null) {
      String[] temp = line.split(",");
      if (Integer.parseInt(temp[1]) < minWorkedHours) {
        result = temp[0] + ": " + temp[2];
        minWorkedHours = Integer.parseInt(temp[1]);
      }
    }
    return result;
  }
}
