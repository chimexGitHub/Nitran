package com.example.co2globalservices.ispeak1;


import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
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
public class PidginFragment extends Fragment {


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
    public PidginFragment() {
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

               // Intent intent = new Intent(getActivity(),AboutActivity.class);
               // startActivity(intent);

                startActivity(new Intent(getActivity(), AboutActivity.class));

                return true;
            case R.id.credit_nitran:
                // call its activity
                Intent intent1 = new Intent(getActivity(),CreditActivity.class);
                startActivity(intent1);
                return true;
            case R.id.contribute_nitran:
                // call feedback up
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

        words.add(new Word("forget about it","Fashi",R.raw.pidgin_fashi));
        words.add(new Word("chill out","Peche",R.raw.pidgin_peche));
        words.add(new Word("backbiter/rumor-monger","Amebo",R.raw.pidgin_amebo ));
        words.add(new Word("try to do things the bad way","Runs",R.raw.pidgin_runs));
        words.add(new Word("Booty","Ikebe/Bakassi",R.raw.pidgin_booty));

        words.add(new Word("Overserious students. ITK - I Too Know","ITK",R.raw.pidgin_itk));
        words.add(new Word("UK","Jand",R.raw.pidgin_jand));
        words.add(new Word("US","Yankee",R.raw.pidgin_yankee));
        words.add(new Word("Rich spoilt kid","Aje butter",R.raw.pidgin_ajebutter));
        words.add(new Word("Leave","Comot",R.raw.pidgin_comot));
        // words.add(new Word("Table","Okpokoro",R.raw.ibibio_table));
        words.add(new Word("Hit","Nak",R.raw.pidgin_nak));
        // words.add(new Word("Biro/Pen","nkpo uwet nwet",R.raw.ibibio_biro));
        words.add(new Word("Entrance/Vicinity","Domot",R.raw.pidgin_domot));
        // words.add(new Word("Trouser","Afong ukot",R.raw.ibibio_trouser));
        words.add(new Word("Sit","Kak",R.raw.pidgin_kak));
        words.add(new Word("Butt","Baka",R.raw.pidgin_baka));
        //words.add(new Word("Butt","yanch",R.raw.pidgin_yanch));
        words.add(new Word("Chill out/Patient","Pam",R.raw.pidgin_pam));
        words.add(new Word("Young woman","Kele",R.raw.pidgin_kele));
        words.add(new Word("He/She/It","E/Im",R.raw.pidgin_im));
        words.add(new Word("Him/Her/It","Am",R.raw.pidgin_am));
        words.add(new Word("Ghetto kid","Aje kpako",R.raw.pidgin_aje_kpako));
        words.add(new Word("Slap you","Sand you",R.raw.pidgin_sand_you));
        words.add(new Word("Police","Ekelebe",R.raw.pidgin_police));
        words.add(new Word("Police","Olokpa",R.raw.pidgin_olokpa));
       // words.add(new Word("illiterate","Ode",R.raw.pidgin_illiterate));
        words.add(new Word("Fool","Mugu",R.raw.pidgin_fool));
        words.add(new Word("You are crazy","You dey kolo",R.raw.pidgin_you_are_crazy));
        words.add(new Word("Do not let me slap you","No let me forget my hand for your face ",R.raw.pidgin_do_not_let_me_slap_u));
        words.add(new Word("it has been long","E don tey",R.raw.pidgin_it_has_been_long));
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
