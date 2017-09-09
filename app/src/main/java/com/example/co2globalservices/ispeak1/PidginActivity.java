package com.example.co2globalservices.ispeak1;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by CO2 on 6/1/2017.
 */
public class PidginActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    // handles audio focus when playing a sound file
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);

                    }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        // The audioFocus_GAIN case means we have regained focus and can
                        // resume playback
                        mMediaPlayer.start();
                    }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        // The AUDIO_FOCUS_LOSS case means we ' ve lost audio focus and
                        // stop playback
                        // stop playback
                        releaseMediaPlayer();
                    }
                }
            };
    /**
     *This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener(){

        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // create and setup the {@link audioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        // create a list of words

        final ArrayList<Word> words = new ArrayList<Word>();


        words.add(new Word("forget about it","Fashi",R.raw.ibibio_man));
        words.add(new Word("chill out","Peche",R.raw.ibibio_woman));
        words.add(new Word("backbiter/rumor-monger","Amebo",R.raw.ibibio_boy ));
        words.add(new Word("backbiter/rumor-monger","Aproko",R.raw.ibibio_boy ));
        words.add(new Word("backbiter/rumor-monger","Tatafo",R.raw.ibibio_boy ));
        words.add(new Word("try to do things the bad way","Runs",R.raw.ibibio_girl));
        words.add(new Word("Booty","Ikebe/Bakassi",R.raw.ibibio_brother));

        words.add(new Word("All those overserious students. ITK stands for I Too Know","ITK",R.raw.ibibio_brother));
        words.add(new Word("UK","Jand",R.raw.ibibio_food));
        words.add(new Word("US","Yankee",R.raw.ibibio_come));
        words.add(new Word("Grich spoilt kid","Aje butter",R.raw.ibibio_go));
        words.add(new Word("Leave","Comot",R.raw.ibibio_chair));
        // words.add(new Word("Table","Okpokoro",R.raw.ibibio_table));
        words.add(new Word("Hit","Nak",R.raw.ibibio_book));
        // words.add(new Word("Biro/Pen","nkpo uwet nwet",R.raw.ibibio_biro));
        words.add(new Word("Entrance/Vicinity","Domot",R.raw.ibibio_shirt));
        // words.add(new Word("Trouser","Afong ukot",R.raw.ibibio_trouser));
        words.add(new Word("Sit","Kak",R.raw.ibibio_school));
        words.add(new Word("Butt","Baka",R.raw.ibibio_gud_mornin));
        // words.add(new Word("Student","eyeri ufok nwed",R.raw.ibibio_student));
        words.add(new Word("Chill out/Patient","Pam",R.raw.ibibio_market));
        words.add(new Word("Young woman","Kele",R.raw.ibibio_church));
        words.add(new Word("He/She/It","E/Im",R.raw.ibibio_walk));
        words.add(new Word("Him/Her/It","Am",R.raw.ibibio_work));
        words.add(new Word("Ghetto kid","Aje kpako",R.raw.ibibio_rainy_season));
        words.add(new Word("Slap you","Sand you",R.raw.ibibio_harmattan));
        words.add(new Word("Police","Olokpa/Ekelebe",R.raw.ibibio_water));
        words.add(new Word("illiterate","Ode",R.raw.ibibio_shoe));
        words.add(new Word("Fool","Mugu",R.raw.ibibio_hand));
        words.add(new Word("You are crazy","You dey kolo",R.raw.ibibio_leg));
        words.add(new Word("Do not let me slap you","No let me forget my hand for your face ",R.raw.ibibio_head));
        words.add(new Word("it has been long","E don tey",R.raw.ibibio_time));

        //create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        //adapter knows how to create layouts for each item in the list, using the
        // simple_list_item.xml layout resource defined in the Android framework.
        // This item layout contains a single {@link TextView}, which the adapter will set
        //display a single word.

        WordAdapter adapter =
                new WordAdapter(this,words);

        ListView listView=(ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);

                // Release the media player if it currently exists because we are about to
                // play a different sound file.
                releaseMediaPlayer();

                // request audio focus for playback
                int result=mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // use the music stream
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){


                    // we have audio focus now


                    mMediaPlayer= MediaPlayer.create(PidginActivity.this,word.getAudioResourceId());
                    mMediaPlayer.start();  // no need to call prepare(); create() does that for you

                    // Setup a listener on the media player, so that we can stop and release the
                    // media palyer once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }
    /**
     * clean up media player by releasing all its resources.
     */

    @Override
    protected void onStop() {
        super.onStop();
        // when the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        //if the media player is not null, then it may be currently playing a sound
        if(mMediaPlayer !=null){
            //Regardless of the current state of the media player, release its resources
            //because we no longer need it
            mMediaPlayer.release();

            //set the media player back to null. For your code, we 've decided that
            //setting the media player to null is an easy way to tell that the media player
            //is not configured to play an audio file at the moment.
            mMediaPlayer=null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
