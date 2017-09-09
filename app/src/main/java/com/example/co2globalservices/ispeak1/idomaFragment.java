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
public class idomaFragment extends Fragment {


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
    public idomaFragment() {
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
                // call search feedback
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

        //words.add(new Word("Good morning","Nmaochi",R.raw.idoma_goodmorning));
        words.add(new Word("How are you?","Agbehii?",R.raw.idoma_how_are_you));
        words.add(new Word("Fine","Mgbehi -e",R.raw.idoma_fine ));
        words.add(new Word("Good afternoon","Nma eno",R.raw.idoma_goodafternoon ));
        words.add(new Word("Good evening","Nma one",R.raw.idoma_goodevening ));
        //words.add(new Word("Good night","Nmo gb'ochi",R.raw.idoma_goodnight));
        words.add(new Word("Goodbye","L'eyi kwaje",R.raw.idoma_goodbye));

        words.add(new Word("Safe journey","Owe ko gbu o",R.raw.idoma_safejourney));
        words.add(new Word("Amen","Olohi",R.raw.idoma_amen));
        words.add(new Word("Thank you","ahinya",R.raw.idoma_thakyou));
        words.add(new Word("One","Ei",R.raw.idoma_one));
        words.add(new Word("Two","Epa",R.raw.idoma_two));
        words.add(new Word("Three","Eta",R.raw.idoma_three));
        words.add(new Word("Four","Ene",R.raw.idoma_four));
        words.add(new Word("Five","Eho",R.raw.idoma_five));
        words.add(new Word("Six","Ehili",R.raw.idoma_six));
        words.add(new Word("Seven","Ahapa",R.raw.idoma_seven));
        words.add(new Word("Eight","Ahata",R.raw.idoma_eight));
        words.add(new Word("Nine","Ahane",R.raw.idoma_nine));
        words.add(new Word("Ten","Igwo",R.raw.idoma_ten));
        words.add(new Word("Eleven","Igwo le",R.raw.ibibio_eleven));
        words.add(new Word("Twelve","Igwepa",R.raw.idoma_twelve));
        words.add(new Word("Thirteen","Igweta",R.raw.idoma_thirteen));
        words.add(new Word("Fourteen","Igwene",R.raw.idoma_fourteen));
        words.add(new Word("fifteen","Igweho",R.raw.idoma_fifteen));
        words.add(new Word("sixteen","Igwehili",R.raw.idoma_sixteen));
        words.add(new Word("Seventeen","Igwahapa",R.raw.idoma_seventeen));
        words.add(new Word("Eighteen","Igwahata",R.raw.idoma_eighteen));
        words.add(new Word("Nineteen","Igwahane",R.raw.idoma_nineteen));
        words.add(new Word("Twenty","Ofu",R.raw.idoma_twenty));
        words.add(new Word("Chalk","Ichok",R.raw.idoma_chalk));
        words.add(new Word("Boy","Ochenyilo",R.raw.idoma_boy));
        words.add(new Word("School","Unokpa",R.raw.idoma_school));

        words.add(new Word("Student","Aipunokpa",R.raw.idoma_student));
        words.add(new Word("Teacher","Ochonwokpa",R.raw.idoma_teacher));
       // words.add(new Word("Boy","Ochenyilo",R.raw.ibibio_leg));
        words.add(new Word("Book","Okpa",R.raw.idoma_book));
        words.add(new Word("Girl","Onya",R.raw.idoma_girl));
        words.add(new Word("Woman","Onya",R.raw.idoma_woman));
        words.add(new Word("Man","Onyilo",R.raw.idoma_man));
        words.add(new Word("Father","Ada",R.raw.idoma_father));
        words.add(new Word("Mother","Ene",R.raw.idoma_mother));
        words.add(new Word("Brother","Oine ochenyilo",R.raw.idoma_brother));
        words.add(new Word("Sister","Oine ochenya",R.raw.idoma_sister));
        words.add(new Word("Wife","Onya",R.raw.idoma_wife));
        words.add(new Word("Husband","Oba",R.raw.idoma_husband));
        words.add(new Word("Goat","Ewu",R.raw.idoma_goat));
        words.add(new Word("Dog","Ewo",R.raw.idoma_dog));
        words.add(new Word("Seed","Ikpo",R.raw.idoma_seed));
        words.add(new Word("Cutlass","Ukpakwu",R.raw.idoma_cutlass));
        words.add(new Word("Fowl","Ugwu",R.raw.idoma_fowl));



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