package com.example.chen_enen130app.DataFiles;

/**
 * A class that holds information of a lesson's structure.
 * This class should be used in conjunction with Model_Chapter to manage a long list of Model_Lessons through the use of an ArrayList.
 */
public class Model_Lesson {
    private final String lessonName;
    private final int image;

    Model_Lesson(String lessonName, int image) {
        this.lessonName = lessonName;
        this.image = image;
    }

    public String getLessonName() {
        return lessonName;
    }

    public int getImage() {
        return image;
    }
}
