package dataLoader;

import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TestData {
    private static List<String> csvHeader;
    public static String currentTestMethodName;
    public static String currentDirectory = System.getProperty("user.dir");
    public static String modifiedDirectory = Paths.get(currentDirectory)
            .resolveSibling("DataSource")
            .toString();
    public static String dataFilePath =  modifiedDirectory + File.separator + "src" + File.separator +  "main"+ File.separator + "resources"+ File.separator + "DataManagement" + File.separator + "DataCSV"+ File.separator;
    public static String fieldsFilePath = modifiedDirectory + File.separator + "src" + File.separator +  "main"+ File.separator + "resources"+ File.separator + "DataManagement" + File.separator + "FieldsCSV"+ File.separator;

    @DataProvider(name = "TestDataProviderFunction")
    public static Iterator<Object[]> TestDataProviderFunction(ITestNGMethod method) {
        currentTestMethodName = method.getMethodName();
        System.out.println("Method NAME IS : " + method.getMethodName());

        return parseCsvData(dataFilePath + currentTestMethodName + ".csv");
    }

    public static void getCsvHeader() {
        List<String> array = new ArrayList<>();
        String fileName = dataFilePath + currentTestMethodName + ".csv";
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Files.newInputStream(Paths.get(fileName)), StandardCharsets.UTF_8))) {
            String[] line = br.readLine().trim().split(",");
            array.addAll(Arrays.asList(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
        csvHeader = array;
    }
    public static int getHeaderColumnIndex(String fieldName) {
        getCsvHeader();
        return csvHeader.indexOf(fieldName);
    }
    public static Iterator<Object[]> parseCsvData(String fileName) {
        ArrayList<Object[]> data = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(Files.newInputStream(Paths.get(fileName)), StandardCharsets.UTF_8))) {
            String line;
            input.readLine(); // This is to ignore the Header in csv.
            while ((line = input.readLine()) != null) {
                String in = line.trim();
                List<Object> array = new ArrayList<>();
                array.add(in);
                data.add(array.toArray());
            }
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return data.iterator();
    }
}
