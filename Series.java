/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Steeze
 */
public class Series {
    public static class SeriesModel {
        public String seriesId;
        public String seriesName;
        public int seriesAge;
        public int seriesNumberOfEpisodes;

        // Constructor to create a new Series object
        public SeriesModel(String id, String name, int age, int episodes) {
            this.seriesId = id;
            this.seriesName = name;
            this.seriesAge = age;
            this.seriesNumberOfEpisodes = episodes;
        }
    }
}
