/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicalassignment1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all operations (menus and CRUD) for series.
 */
public class SeriesManager extends Series {

    // Stores all series objects
    private ArrayList<Series.SeriesModel> seriesList = new ArrayList<>();

    // Getter for testing
    public ArrayList<Series.SeriesModel> getSeriesList() {
        return seriesList;
    }

    // ------------------ MENU SECTION ------------------

    // First menu that asks user if they want to continue
    public void SampleMenu() {
        Scanner console = new Scanner(System.in);
        System.out.println("**************************************************");
        System.out.println("Enter (1) to launch menu or any other key to exit");
        System.out.println("**************************************************");
        System.out.print("Enter>>>>>>>>>");

        String choice = console.nextLine();

        if (choice.equals("1")) {
            Menu();
        } else {
            System.out.println("Exiting the application");
        }
    }

    // Main menu
    public void Menu() {
        Scanner console = new Scanner(System.in);
        int menuOption = 0;

        do {
            System.out.println(
                    "Please Select one of the following menu items: \n"
                    + "(1) Capture a new series\n"
                    + "(2) Search for a series\n"
                    + "(3) Update series age restriction\n"
                    + "(4) Delete a series\n"
                    + "(5) Print series report - 2025\n"
                    + "(6) Exit Application"
            );
            System.out.print("Enter your choice: ");

            try {
                menuOption = console.nextInt();
                console.nextLine(); // Consume newline
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                console.nextLine();
                continue;
            }

            switch (menuOption) {
                case 1:
                    CaptureNewSeries(console);
                    break;
                case 2:
                    System.out.print("Enter the series ID to search: ");
                    String searchId = console.nextLine();
                    SeriesModel found = SearchSeriesById(searchId);
                    if (found != null) {
                        System.out.println("Series found: " + found.seriesName);
                    } else {
                        System.out.println("Series not found: " + searchId);
                    }
                    break;
                case 3:
                    UpdateSeries(console);
                    break;
                case 4:
                    System.out.print("Enter the series ID to delete: ");
                    String deleteId = console.nextLine();
                    if (DeleteSeries(deleteId)) {
                        System.out.println("Series deleted: " + deleteId);
                    } else {
                        System.out.println("Series not found: " + deleteId);
                    }
                    break;
                case 5:
                    PrintSeriesReport();
                    break;
                case 6:
                    System.out.println("Exiting the application...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (menuOption != 6);
    }

    // ------------------ METHODS SECTION ------------------

    // Capture a new series
    private void CaptureNewSeries(Scanner console) {
        System.out.print("Enter the series ID: ");
        String seriesId = console.nextLine();
        System.out.print("Enter the series name: ");
        String seriesName = console.nextLine();

        int seriesAge = 0;
        boolean validAge = false;
        do {
            try {
                System.out.print("Enter the series age restriction (between 2 and 18): ");
                seriesAge = console.nextInt();
                console.nextLine(); // Consume newline
                if (seriesAge < 2 || seriesAge > 18) {
                    System.out.println("Invalid age restriction. Please enter a value between 2 and 18.");
                } else {
                    validAge = true;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                console.nextLine(); // Clear bad input
            }
        } while (!validAge);

        System.out.print("Enter the number of episodes: ");
        int seriesEpisodes = console.nextInt();
        console.nextLine();

        AddSeries(seriesId, seriesName, seriesAge, seriesEpisodes);
        System.out.println("Series added successfully.");
    }

    // Update a series age restriction
    private void UpdateSeries(Scanner console) {
        System.out.print("Enter the series ID to update age restriction: ");
        String updateId = console.nextLine();
        int newAge = 0;
        boolean validNewAge = false;
        do {
            try {
                System.out.print("Enter the new age restriction (between 2 and 18): ");
                newAge = console.nextInt();
                console.nextLine();
                if (newAge < 2 || newAge > 18) {
                    System.out.println("Invalid age restriction. Please enter a value between 2 and 18.");
                } else {
                    validNewAge = true;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                console.nextLine();
            }
        } while (!validNewAge);

        if (UpdateSeriesAge(updateId, newAge)) {
            System.out.println("Series age updated successfully.");
        } else {
            System.out.println("Series not found.");
        }
    }

    // Add a series
    public void AddSeries(String id, String name, int age, int episodes) {
        Series.SeriesModel newSeries = new Series.SeriesModel(id, name, age, episodes);
        seriesList.add(newSeries);
    }

    // Search for a series by ID
    public Series.SeriesModel SearchSeriesById(String id) {
        for (Series.SeriesModel series : seriesList) {
            if (series.seriesId.equals(id)) {
                return series;
            }
        }
        return null;
    }

    // Update series age restriction
    public boolean UpdateSeriesAge(String id, int newAge) {
        for (Series.SeriesModel series : seriesList) {
            if (series.seriesId.equals(id)) {
                series.seriesAge = newAge;
                return true;
            }
        }
        return false;
    }

    // Delete a series
    public boolean DeleteSeries(String id) {
        for (int i = 0; i < seriesList.size(); i++) {
            if (seriesList.get(i).seriesId.equals(id)) {
                seriesList.remove(i);
                return true;
            }
        }
        return false;
    }

    // Print a full report of all series
    public void PrintSeriesReport() {
        if (seriesList.isEmpty()) {
            System.out.println("No series to display.");
            return;
        }
        System.out.println("Series Report - 2025");
        System.out.println("------------------");
        for (Series.SeriesModel series : seriesList) {
            System.out.println("Series ID: " + series.seriesId);
            System.out.println("Series Name: " + series.seriesName);
            System.out.println("Series Age: " + series.seriesAge);
            System.out.println("Number of Episodes: " + series.seriesNumberOfEpisodes);
            System.out.println("------------------");
        }
    }
}
