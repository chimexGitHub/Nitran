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
public class UrhoboFragment extends Fragment {


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
    public UrhoboFragment() {
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

        words.add(new Word("Announcement","aghwoghwo",R.raw.urh_announcement));
        words.add(new Word("Baldness/bald head","akpan",R.raw.urh_baldness));
        words.add(new Word("Bean cake","akara",R.raw.urh_bean_cake));
        words.add(new Word("Bell","ágógó",R.raw.urh_bell));
        words.add(new Word("Brain","afọrhe",R.raw.urh_brain));
        words.add(new Word("Bush","ághwá",R.raw.urh_bush));
        words.add(new Word("Chair","agbara",R.raw.urh_chair));
        words.add(new Word("Crotch","agada",R.raw.urh_crotch));
        words.add(new Word("Echo","agboro",R.raw.urh_echo));
        words.add(new Word("Enjoyment","akpériọ",R.raw.urh_enjoyment));
        words.add(new Word("Equal","abavo",R.raw.urh_equal));
        words.add(new Word("Exclamation","án",R.raw.urh_exclamatn));
        words.add(new Word("Fan","adjudju",R.raw.urh_fan));
        words.add(new Word("Forked stick","áda",R.raw.urh_forkd_stick));
        words.add(new Word("Groundnut","amerísaguẹ",R.raw.urh_grandnut));
        words.add(new Word("Grasshopper","abaka",R.raw.urh_grasshoper));
        words.add(new Word("Guilt","abé",R.raw.urh_guilt));
        words.add(new Word("Ink","ameóbe",R.raw.urh_ink));
        words.add(new Word("Joy","aghọghọ",R.raw.urh_joy));
        words.add(new Word("Life/World","akpọ",R.raw.urh_lifeworld));
        words.add(new Word("Masquerade playground","áfiédjọ",R.raw.urh_masqu_playgrand));
        words.add(new Word("Matches","agbuna",R.raw.urh_matches));
        words.add(new Word("Office","akoríruo",R.raw.urh_office));
        words.add(new Word("Local gin","ekaikai",R.raw.urh_ogogoro));
        words.add(new Word("Oil bean seed","ogba",R.raw.urh_oil_bean_seed));
        words.add(new Word("Padlock","akpérusiavwrẹ",R.raw.urh_padlock));
        words.add(new Word("Playground","áfiéha",R.raw.urh_playground));
        words.add(new Word("Rabbit","afiotọ",R.raw.urh_rabbit));
        words.add(new Word("Sword","abẹrẹn",R.raw.urh_sword));
        words.add(new Word("Three way junction","adérha",R.raw.urh_threeway_junc));
        words.add(new Word("Thrice","abérha",R.raw.urh_thrice));
        words.add(new Word("Twice","abívẹ",R.raw.urh_twice));
        words.add(new Word("Water","ame",R.raw.urh_water));
        words.add(new Word("Who","amóno",R.raw.urh_who));
        words.add(new Word("Wrestling","abémuó",R.raw.urh_wrestling));

        //words.add(new Word("One","ovo",R.raw.urh_one));
        // words.add(new Word("Two","ive",R.raw.urh_two));
        //words.add(new Word("Three","era",R.raw.urh_three));
        //words.add(new Word("Four","eneh",R.raw.urh_four));
        //words.add(new Word("Five","iyori",R.raw.urh_five));
        //words.add(new Word("Six","esan",R.raw.urh_six));
        //words.add(new Word("Seven","igwreh",R.raw.urh_seven));
        //words.add(new Word("Eight","erere",R.raw.urh_eight));
        //words.add(new Word("Nine","iriri",R.raw.urh_nine));
        //words.add(new Word("Ten","ikwe",R.raw.urh_ten));
        // words.add(new Word("",""));
        //words.add(new Word("",""));
        // words.add(new Word("",""));
        //words.add(new Word("",""));
        // words.add(new Word("",""));
        // words.add(new Word("",""));
        //words.add(new Word("",""));
        // words.add(new Word("",""));
        // words.add(new Word("",""));
        //words.add(new Word("",""));
        //words.add(new Word("",""));


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