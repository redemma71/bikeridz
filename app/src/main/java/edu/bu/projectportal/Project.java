package edu.bu.projectportal;

/**
 * Created by danazh on 4/23/18.
 */

public class Project {
    private int id;
    private String title;
    private String summary;
    public  static final Project[] projects = {
            new Project("Weather Forecast", "Weather Forcast is an app ..."),
            new Project("Connect Me", "Connect Me is an app ... "),
            new Project("What's Eat", "What's Eat is an app ..."),
            new Project("Project Portal", "Project Portal is an app ...")};

    public final int getId() {
        return this.id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String title) {
        this.title = title;
    }

    public final String getSummary() {
        return this.summary;
    }

    public final void setSummary(String summary) {
        this.summary = summary;
    }

    public String toString() {
        return "Project{title='" + this.title +
                '\'' + ", summary='" + this.summary + '\'' + '}';
    }

    public Project(String title, String summary) {
        this.title = title;
        this.summary = summary;
    }

    public Project(int id, String title, String summary) {
        this.id = id;
        this.title = title;
        this.summary = summary;
    }
}