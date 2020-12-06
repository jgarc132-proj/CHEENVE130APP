package com.example.chen_enen130app.DataFiles;

import com.example.chen_enen130app.R;

import java.util.ArrayList;

/**
 * A class that holds information of a chapter corresponding to a literal textbook chapter.
 * This class is used in conjunction with the Model_Lesson class for management of lessons.
*/
public class Model_Chapter {
    private final String chapterName;
    private final int image;
    private ArrayList<Model_Lesson> lessonArrayList;

    Model_Chapter(String chapterName, int image) {
        this.chapterName = chapterName;
        this.image = image;
        lessonArrayList = new ArrayList<>();
    }

    public String getChapterName() {
        return chapterName;
    }

    public int getImage() {
        return image;
    }

    public void addLesson(Model_Lesson lesson) {
        lessonArrayList.add(lesson);
    }

    public ArrayList<Model_Lesson> getLessonArrayList() {
        return lessonArrayList;
    }

    /**
     * Class method to generate an ArrayList of <b>Model_Chapters</b>. The <u>chapterName</u> parameter is set to "Chapter <i>x</i>", where <i>x</i> is the chapter number.
     * The <u>lessonName</u> parameter of the <b>Model_Lesson</b> object is set to "Lesson <i>x</i>", where <i>x</i> is the lesson number.
     * <i>x</i> is always correspondent to the index of each respective parameter.
     * @param numChapters (<b>int</b>): The number of chapters desired.
     * @param numLessons (<b>ArrayList(Integer)</b>): The number of lessons per chapter, with the ArrayList size equal to numChapters.
     * @return (<b>ArrayList(Model_Chapter)</b>): An ArrayList of Model_Chapters
     */
    public static ArrayList<Model_Chapter> generateChapters(int numChapters, ArrayList<Integer> numLessons) {
        ArrayList<Model_Chapter> chapters = new ArrayList<>();

        for(int chapter = 0; chapter < numChapters; ++chapter) {

            chapters.add(new Model_Chapter("Chapter " + (chapter + 1), R.mipmap.ic_launcher_round));

            for(int lesson = 0; lesson < numLessons.get(chapter); ++lesson) {
                chapters.get(chapter).addLesson(new Model_Lesson("Lesson " + (lesson + 1), R.mipmap.ic_launcher_round));
            }
        }

        return chapters;
    }
}
