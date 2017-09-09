package com.example.co2globalservices.ispeak1;

/**
 * Created by CO2 on 5/31/2017.
 *
 * {@link Word} represents a vocabularly word that the users want to learn.
 * it contains a default translation and igbo translation for the word
 */
public class Word {

    /** Default translation for the word */
    private  String iDefaultTranslation;

    /** Igbo default translation for the word */
    private String iIgboTranslation;

    /** Audio resource ID for the word */
    private int iAudioResourceId;

    public  Word(String defaultTranslation,String igboTranslation, int audioResourceId ){
        iDefaultTranslation=defaultTranslation;
        iIgboTranslation=igboTranslation;
        iAudioResourceId = audioResourceId;
    }

    /**
     * Get default translation for the word.
     */
    public String getDefaultTranslation(){
        return iDefaultTranslation;
    }

    /**
     * Get igbo translation for the word.
     */
    public String getIgboTranslation(){
        return iIgboTranslation;
    }

    /**
     * Get the audio resource ID of the word.
     */
    public int getAudioResourceId(){
        return iAudioResourceId;
    }
}
