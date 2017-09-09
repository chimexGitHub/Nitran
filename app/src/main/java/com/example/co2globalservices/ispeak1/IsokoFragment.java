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
public class IsokoFragment extends Fragment {


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
    public IsokoFragment() {
        // Required empty public constructor
    }

    public  void composeEmail(String addresses, String subject){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:co2globalservices.com"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity( getActivity().getPackageManager()) != null){
            startActivity(intent);
        }

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
            //case R.id.search_bar:
                // call search bar up
                // return true;
            case R.id.contribute_nitran:
                // call search send feedback... intent for email
                composeEmail("mailto:co2globalservices.com","Send Feedback");

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

        words.add(new Word("Man","Oza",R.raw.isoko_man));
        words.add(new Word("Woman","Aye",R.raw.isoko_woman));
        words.add(new Word("Boy","Omo-oza",R.raw.isoko_boy ));
        words.add(new Word("Girl","Omote",R.raw.isoko_girl));
        words.add(new Word("Brother","Onovo omo-za me",R.raw.isoko_brother));

       // words.add(new Word("Sister","Onovo Omote me",R.raw.isoko_brother));

       // words.add(new Word("Food","Emu",R.raw.isoko_food));

        words.add(new Word("Come","Yanze",R.raw.isoko_come));

        //words.add(new Word("Go/Leave","Na ze",R.raw.isoko_go));

        words.add(new Word("Chair","Akpala",R.raw.isoko_chair));
        words.add(new Word("Table","Emeje",R.raw.isoko_table));
        words.add(new Word("Book","Ebe",R.raw.isoko_book));
        words.add(new Word("Biro/Pen","Ibiro",R.raw.isoko_biro));
        words.add(new Word("Shirt","Ewu",R.raw.isoko_shirt));
        words.add(new Word("Trouser","Ojolojo",R.raw.isoko_trouser));
        words.add(new Word("School","isukulu",R.raw.isoko_school));
        words.add(new Word("Good Morning","Omamo Oyoye",R.raw.isoko_gud_mornin));
        words.add(new Word("Student","Omo - isikulu",R.raw.isoko_student));
        words.add(new Word("Market","Eki",R.raw.isoko_market));
        words.add(new Word("Church","Uwo-Egago",R.raw.isoko_church));
        words.add(new Word("Walk","Onaya",R.raw.isoko_walk));
        words.add(new Word("Work","Iruo",R.raw.isoko_work));
        words.add(new Word("Rainy season","Oke-Osio",R.raw.isoko_rainy_season));
        words.add(new Word("Harmattan season","Oke-Uriri",R.raw.isoko_harmattan));
        words.add(new Word("Water","Ame",R.raw.isoko_water));
        words.add(new Word("Shoe","Eve",R.raw.isoko_shoe));
        words.add(new Word("Hand","Abo",R.raw.isoko_hand));
        words.add(new Word("Leg","Uzo",R.raw.isoko_leg));
        //words.add(new Word("Head","Owot",R.raw.ibibio_head));
        words.add(new Word("Time","Oke",R.raw.isoko_time));
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