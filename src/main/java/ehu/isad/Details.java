package ehu.isad;

import java.util.Arrays;

public class Details {

    private String[] publishers;
    private int number_of_pages;
    private String title;

    @Override
    public String toString() {
        return "Details{" +
                "publishers=" + Arrays.toString(publishers) +
                ", number_of_pages=" + number_of_pages +
                ", title='" + title + '\'' +
                '}';
    }

    public Details() {}

    public String[] getPublishers() {
        return publishers;
    }

    public int getNumber_of_pages() {
        return number_of_pages;
    }

    public String getTitle() {
        return title;
    }
}

