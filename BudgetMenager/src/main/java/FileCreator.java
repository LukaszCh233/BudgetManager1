import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class FileCreator {

    LocalDate lastWriteDate;
    LocalDate currentDate;
    String fileName;
    ProfExpenList profExpenList;

    public FileCreator() {
        this.lastWriteDate = LocalDate.now().withDayOfMonth(1);
        this.currentDate = LocalDate.now();
        this.fileName = lastWriteDate.getMonth().toString() + "_" + lastWriteDate.getYear() + ".txt";
        profExpenList = new ProfExpenList();
        createNewFile();
        loadFile();
    }


    public void nextMonth () {

        ProfExpenList profExpenList = new ProfExpenList();
        if (currentDate.getMonthValue() != lastWriteDate.getMonthValue()) {
            lastWriteDate = currentDate.plusMonths(1);
            createNewFile();
        }
        try {
            FileWriter fileWriter = new FileWriter(fileName,true);
            for (ProfExpens pe : profExpenList.list) {
                fileWriter.write(pe.getType() + "," + pe.getName() + "," + pe.getAmount() + "\n");
            }
            fileWriter.close();
            System.out.println("Lista zapisana do pliku.");
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisu do pliku.");
            e.printStackTrace();
        }
    }

    public void createNewFile () {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("Utworzono plik " + fileName);
            } else {
                System.out.println("Plik " + fileName + " już istnieje.");
                loadFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            }
        }
    public void loadFile () {
        ProfExpenList profExpenList = new ProfExpenList();
        try {
            FileReader reader = new FileReader(fileName);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                if (line.length == 3) {
                    ProfitExpensType type = ProfitExpensType.valueOf(line[0]);
                    String name = line[1];
                    double amount = Double.parseDouble(line[2]);
                    profExpenList.list.add(new ProfExpens(name, amount, type));
                }
            }
            scanner.close();
            reader.close();
            System.out.println("Lista wczytana z pliku.");
        } catch (IOException e) {
            System.out.println("Błąd podczas odczytu z pliku.");
            e.printStackTrace();
        }
    }

}




