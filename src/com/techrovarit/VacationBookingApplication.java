package com.techrovarit;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VacationBookingApplication {

    public static void register(Scanner sc, String directoryPath) {
        User user = new User();
        int randomNumber = (int) (Math.random() * 10000); // You can adjust this range as needed

        // Name of the folder
        String folderName = "acc_" + randomNumber;


        // Create File object representing the folder
        File folder = new File(directoryPath, folderName);
        System.out.println(folder);
        // Check if the folder already exists
        if (!folder.exists()) {
            // Attempt to create the directory
            boolean folderCreated = folder.mkdirs(); // mkdirs() creates parent directories if they do not exist

            // Check if the directory creation was successful
            if (folderCreated) {
                System.out.println("Folder created successfully: " + folder.getAbsolutePath());
            } else {
                System.out.println("Failed to create the folder!");
                return;
            }
        } else {
            System.out.println("Folder already exists!");
        }

        System.out.println("Enter the Registration Details here");

        System.out.println("Enter the Name");
        user.setName(sc.nextLine());

        System.out.println("Enter the Age");
        user.setAge(sc.nextInt());
        sc.nextLine(); // Consume newline character

        System.out.println("Enter the Gender");
        user.setGender(sc.nextLine());

        System.out.println("Enter the Location");
        user.setLocation(sc.nextLine());

        System.out.println("Enter the phone number");
        user.setPhone_Number(sc.nextLong());
        sc.nextLine(); // Consume newline character

        JSONArray bookingDetails = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", user.getName());
        jsonObject.put("age", user.getAge());
        jsonObject.put("gender", user.getGender());
        jsonObject.put("location", user.getLocation());
        jsonObject.put("phone_Number", user.getPhone_Number());
        jsonObject.put("bookings", bookingDetails);

        String jsonFilePath = folder.getAbsolutePath() + File.separator + "acc_" + randomNumber + ".json";
        System.out.println(jsonFilePath);

        try {
            File dataFile = new File(jsonFilePath);
            JSONArray jsonArray;
            System.out.println(dataFile);
            System.out.println(dataFile.exists());
            System.out.println(dataFile.length());
            if (dataFile.exists() && dataFile.length() > 0) {
                String content = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
                jsonArray = new JSONArray(content);
                System.out.println(jsonArray);
            } else {
                jsonArray = new JSONArray();
            }
            jsonArray.put(jsonObject); // Append new registration to the array
            System.out.println(jsonArray);
            try (FileWriter fileWriter = new FileWriter(jsonFilePath)) {
                fileWriter.write(jsonArray.toString());
                System.out.println("User registration details have been saved successfully.");
            }
        } catch (IOException | JSONException e) {
            System.out.println("An error occurred while writing the JSON file: " + e.getMessage());
        }
    }

    public static void bookVacation(Scanner scanner, String directoryPath) {
        System.out.print("Enter your registration number: ");
        String regNumber = scanner.nextLine();
        String folderPath = directoryPath + File.separator + "acc_" + regNumber;
        String userFilePath = folderPath + File.separator + "acc_" + regNumber + ".json";

        if (!Files.exists(Paths.get(folderPath)) || !Files.exists(Paths.get(userFilePath))) {
            System.out.println("This is not a valid registration number. Please register.");
            return;
        }

        System.out.println("Booking type:");
        System.out.println("1. Hotel");
        System.out.println("2. Apartment");
        System.out.println("3. Villa");
        String bookingType = "";
        switch (scanner.nextLine()) {
            case "1":
                bookingType = "Hotel";
                break;

            case "2":
                bookingType = "Apartment";
                break;
            case "3":
                bookingType = "Villa";
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
                return;
        }

        System.out.print("Number of people: ");
        int numberOfPeople = Integer.parseInt(scanner.nextLine());
        System.out.print("Number of days stay: ");
        int numberOfDaysStay = Integer.parseInt(scanner.nextLine());

        Random random = new Random();
        String bookingId = "vcc_" + (1000 + random.nextInt(9000));

        BookingDetails bookingDetails = new BookingDetails(
                regNumber, bookingType, numberOfPeople, numberOfDaysStay, bookingId
        );

        String bookingFilePath = folderPath + File.separator + bookingId + ".json";

        try (FileWriter file = new FileWriter(bookingFilePath)) {
            file.write(new JSONObject(bookingDetails).toString(4));
            System.out.println("Booking details saved in '" + bookingFilePath + "'.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return;
        }

        try {
            String content = new String(Files.readAllBytes(Paths.get(userFilePath)));
            JSONArray userData = new JSONArray(content);
            JSONObject userObject = userData.getJSONObject(0); // Assuming single user registration per file
            JSONArray bookings = userObject.getJSONArray("bookings");
            bookings.put(bookingId);

            try (FileWriter file = new FileWriter(userFilePath)) {
                file.write(userData.toString(4));
                System.out.println("Booking ID added to user file.");
            }
        } catch (IOException e) {
            System.out.println("Error updating user file: " + e.getMessage());
        } catch (JSONException e) {
            System.out.println("Error parsing user file: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        String directoryPath = "C:\\Users\\VAMSI K\\Desktop\\myProject";

        do {
            System.out.println("1. register \n2. book vacation \n3. my total vacations \n4. pay bill \n5. close");
            System.out.println("If new user first register or you are already existing member please check the details");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    register(sc, directoryPath);
                    break;
                case 2:
                    bookVacation(sc, directoryPath);
                    break;

                case 5:
                    System.out.println("Thank You");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }
}