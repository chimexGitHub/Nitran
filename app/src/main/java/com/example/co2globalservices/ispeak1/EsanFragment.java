package com.example.co2globalservices.ispeak1;


import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EsanFragment extends Fragment {


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
    public EsanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_about,menu);
        super.onCreateOptionsMenu(menu, inflater);
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about_nitran:
                //do something
                // Intent intent=new Intent(this, AboutActivity.class);
                //getActivity().startActivity(intent);

                Intent intent = new Intent(getActivity(),AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.credit_nitran:
                // call its activity
                Intent intent1 = new Intent(getActivity(),CreditActivity.class);
                startActivity(intent1);
                return true;
            case R.id.contribute_nitran:
                // call feedback
                //send message and attachment file
                Intent intentMail = new Intent(Intent.ACTION_SEND);
                intentMail.setData(Uri.parse("mailto:"));
                intentMail.putExtra(Intent.EXTRA_EMAIL,"talk2us@co2globalservices.com");
                intentMail.putExtra(Intent.EXTRA_SUBJECT,"Thank you for Contributing");
                if(intentMail.resolveActivity(getActivity().getPackageManager()) !=null) {
                    startActivity(intentMail);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop() {
        super.onStop();

        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);
        setHasOptionsMenu(true);


        // create and setup the {@link audioManager} to request audio focus
        mAudioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        // create a list of words

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("Man","Okpia",R.raw.esan_man));
        words.add(new Word("Woman","Okwoh",R.raw.esan_woman));
        words.add(new Word("Boy","Okpia",R.raw.esan_boy ));
        words.add(new Word("Girl","Omamen",R.raw.esan_girl));
        words.add(new Word("Brother","Obhio",R.raw.esan_brother));

        words.add(new Word("Sister","Obhio me no okwo",R.raw.esan_sister));
        words.add(new Word("Food","aeby",R.raw.esan_food));
        words.add(new Word("Come","vi e",R.raw.esan_come));
        words.add(new Word("Go/Leave","akhian",R.raw.esan_go));
        words.add(new Word("Chair","agha",R.raw.esan_chair));
       // words.add(new Word("Table","Okpokoro",R.raw.ibibio_table));
        words.add(new Word("Book","ebea",R.raw.esan_book));
       // words.add(new Word("Biro/Pen","nkpo uwet nwet",R.raw.ibibio_biro));
        words.add(new Word("Shirt","ewu",R.raw.esan_shirt));
       // words.add(new Word("Trouser","Afong ukot",R.raw.ibibio_trouser));
        words.add(new Word("School","uwa ebe",R.raw.esan_school));
        words.add(new Word("Good Morning","hisan",R.raw.esan_gud_mornin));
       // words.add(new Word("Student","eyeri ufok nwed",R.raw.ibibio_student));
        words.add(new Word("Market","eki",R.raw.esan_market));
        words.add(new Word("Church","otue",R.raw.esan_church));
        words.add(new Word("Walk","shamon",R.raw.esan_walk));
        words.add(new Word("Work","iwena",R.raw.esan_work));
        words.add(new Word("Rainy season","eghe amen",R.raw.esan_rainy_season));
        words.add(new Word("Harmattan season","okhea",R.raw.esan_harmattan));
        words.add(new Word("Water","amen",R.raw.esan_water));
        words.add(new Word("Shoe","ibata",R.raw.esan_shoe));
        words.add(new Word("Hand","obor",R.raw.esan_hand));
        words.add(new Word("Leg","ohe",R.raw.esan_leg));
        words.add(new Word("Head","uhumor",R.raw.esan_head));
        words.add(new Word("Time","agogo",R.raw.esan_time));
        //words.add(new Word("One",""));
        //words.add(new Word("Two",""));
        // words.add(new Word("Three",""));
        //words.add(new Word("Four",""));
        //words.add(new Word("Five",""));
        //words.add(new Word("Six",""));
        //words.add(new Word("Seven",""));
        //words.add(new Word("Eight",""));
        //words.add(new Word("Nine",""));
        //words.add(new Word("Ten",""));
        //words.add(new Word("House",""));
        // words.add(new Word("",""));
        // words.add(new Word("",""));
        // words.add(new Word("",""));
        // words.add(new Word("",""));
        // words.add(new Word("",""));
        // words.add(new Word("",""));
        // words.add(new Word("",""));
        // words.add(new Word("",""));
        // words.add(new Word("",""));
        // words.add(new Word("",""));
        // words.add(new Word("",""));
        // words.add(new Word("",""));
        // words.add(new Word("",""));
        // words.add(new Word("",""));
        // words.add(new Word("",""));


        //create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        //adapter knows how to create layouts for each item in the list, using the
        // simple_list_item.xml layout resource defined in the Android framework.
        // This item layout contains a single {@link TextView}, which the adapter will set
        //display a single word.

        WordAdapter adapter =
                new WordAdapter(getActivity(),words);

        ListView listView=(ListView)rootView.findViewById(R.id.list);

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


                    mMediaPlayer= MediaPlayer.create(getActivity(),word.getAudioResourceId());
                    mMediaPlayer.start();  // no need to call prepare(); create() does that for you

                    // Setup a listener on the media player, so that we can stop and release the
                    // media palyer once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
        return rootView;
    }

}