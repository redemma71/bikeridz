package edu.bu.projectportal;

import static android.util.Half.NaN;

/**
 * Created by danazh on 4/23/18.
 * Revised by Chad David Cover on 11/16/2018.
 */

public class Project {


    ///////////////////////////////////////////////////////////////////////////////////////////
    // member variables
    ///////////////////////////////////////////////////////////////////////////////////////////

    private int id;
    private String title;
    private String summary;
    private String[] authors;
    private String[] links;
    private boolean favorite;
    private String[] keywords;


    ///////////////////////////////////////////////////////////////////////////////////////////
    // sample data
    ///////////////////////////////////////////////////////////////////////////////////////////

    private static String[] sampleAuthors = {"Larry", "Curly", "Moe"};
    private static String[] sampleAuthors2 = {"Fred Flintstone", "Wilma Flintstone", "Barney Rubble", "Betty Rubble"};
    private static String[] sampleAuthors3 = {"Bill Haywood", "Emma Goldman", "Eugene V. Debs"};
    private static String[] sampleAuthors4 = {"John Jay", "James Madison", "Alexander Hamilton"};

    private static String[] sampleLinks = {"http://google.com", "http://bu.edu"};
    private static String[] sampleLinks2 = {"http://stackoverflow.com", "http://regexr.com"};
    private static String[] sampleLinks3 = {"http://github.com", "http://w3schools.com"};
    private static String[] sampleLinks4 = {"http://npmjs.com", "http://sass-lang.com"};

    private static String[] sampleKeywords = {"Java", "XML", "Android"};
    private static String[] sampleKeywords2 = {"Javascript", "Node", "Angular"};
    private static String[] sampleKeywords3 = {"PHP", "MySQL", "Apache"};
    private static String[] sampleKeywords4 = {"Perl", "XML", "DOM"};


    ///////////////////////////////////////////////////////////////////////////////////////////
    // constructors
    ///////////////////////////////////////////////////////////////////////////////////////////

    public Project(int id) {
        this.id = id;
        this.title = "Sample Project";
        this.summary = "Sample Project is an app...";
        this.authors = this.sampleAuthors;
        this.links = this.sampleLinks;
        this.favorite = false;
        this.keywords = sampleKeywords;
    }

    public Project(int id, String title, String summary) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.authors = this.sampleAuthors;
        this.links = this.sampleLinks;
        this.favorite = false;
        this.keywords = sampleKeywords;
    }

    public Project(int id, String title, String summary, String[] authors, String[] links,
                   boolean favorite, String[] keywords) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.authors = authors;
        this.links = links;
        this.favorite = favorite;
        this.keywords = keywords;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    // instantiation of sample product array
    ///////////////////////////////////////////////////////////////////////////////////////////

    public final static Project[] projects = {
            new Project(1, "Weather Forecast", "Weather Forecast is an app ...",
                    sampleAuthors, sampleLinks, false, sampleKeywords),
            new Project (2, "Connect Me", "Connect Me is an app ... ",
                    sampleAuthors2, sampleLinks2, false, sampleKeywords2),
            new Project(3, "What's Eat", "What's Eat is an app ...",
                    sampleAuthors3, sampleLinks3, false, sampleKeywords3),
            new Project (4, "Project Portal", "Project Portal is an app ...",
                    sampleAuthors4, sampleLinks4, true, sampleKeywords4),
            new Project(5),
    };


    ///////////////////////////////////////////////////////////////////////////////////////////
    // accessors & mutators
    ///////////////////////////////////////////////////////////////////////////////////////////

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

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String[] getLinks() {
        return links;
    }

    public void setLinks(String[] links) {
        this.links = links;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    // helper methods
    ///////////////////////////////////////////////////////////////////////////////////////////


    public String toString() {
        return "Project{title='" + this.title +
                '\'' + ", summary='" + this.summary + '\'' + '}';
    }

}