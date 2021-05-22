package model;

import controller.GameLogic;
import view.panels.HighscorePanel;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Mahmoud Daabas
 * @author Annie Tran
 * This class that functions as a "list" for all highscores
 */
public class HighscoreList {

    private ArrayList<String> highscoreList;
    private GameLogic controller;
    private String highscorePath = "resources/saves/highscore.txt";

    /**
     * Constructor
     * @param controller GameLogic-object to set own variable.
     */
    public HighscoreList(GameLogic controller){
        highscoreList = new ArrayList<>();
        this.controller = controller;
    }

    /**
     * Adds a highscore to the list.
     * @param result highscore result.
     */
    public void addHighscore(String result) {
        highscoreList.add(result);
        saveHighscoreToTxt(result);
    }

    /**
     * Deletes a score from the list based on the index.
     * @param index
     */
    public void deleteHighscore(int index) {
        highscoreList.remove(index);
        controller.getMainFrame().getMainMenu().getPnlHighscore().updateHighscoreList(getHighscoreData());
    }

    /**
     * Adds a highscore to the list without saving to txt file.
     * @param result highscore result.
     */
    public void addHighscoreNoTxt(String result) {
        highscoreList.add(result);
    }

    /**
     * Deletes a highscore from the text file.
     * @param score
     */
    public void deleteHighscoreFromTxt(String score) {
        try {
            FileReader reader = new FileReader(highscorePath);
            BufferedReader br = new BufferedReader(reader);

            String data = br.readLine();
            LinkedList<String> highscores = new LinkedList<>();

            while (data != null) {
                if (!data.equals(score)) {
                    highscores.add(data);
                }
                data = br.readLine();
            }
            br.close();

            PrintWriter write = new PrintWriter(new FileWriter(highscorePath));
            BufferedWriter bw = new BufferedWriter(write);

            for (String addBackScore : highscores) {
                bw.write(addBackScore + "\n");
            }
            bw.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the highscore into a text file.
     * @param result saves highscore.
     */
    public void saveHighscoreToTxt(String result) {
        try {
            FileWriter writer = new FileWriter(highscorePath, true);
            writer.append(result).append("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads all the players into the text file to the profile list.
     */
    public void loadHighscoreList() {
        try {
            FileReader reader = new FileReader(highscorePath);
            BufferedReader br = new BufferedReader(reader);

            String data = br.readLine();
            while (data != null) {
                addHighscoreNoTxt(data);
                data = br.readLine();
            }
            br.close();

            controller.getMainFrame().getMainMenu().getPnlHighscore().updateHighscoreList(getHighscoreData());
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns an String array with the data inside the highscore list.
     * @return highscoreData
     */
    public String[] getHighscoreData() {
        String[] highscoreData = new String[highscoreList.size()];
        for (int i = 0; i < highscoreList.size(); i++) {
            highscoreData[i] = highscoreList.get(i);
        }
        return highscoreData;
    }
}
