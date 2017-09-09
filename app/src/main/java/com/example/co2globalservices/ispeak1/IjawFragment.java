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
public class IjawFragment extends Fragment {

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
    public IjawFragment() {
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

      /*  words.add(new Word("she","abada",R.raw.ijaw_she));
        words.add(new Word("a kind of cloth","abadi",R.raw.ijaw_clothh));
        words.add(new Word("the sea","abadi",R.raw.ijaw_sea));
        words.add(new Word("ocean","abadi-aru",R.raw.ijaw_ocean));
        words.add(new Word("sea-going vessel","abadi-kẹri",R.raw.ijaw_seagoing_ves));

        words.add(new Word("sea-sickness","abadi-tukpa",R.raw.ijaw_sea_sicknex));
        words.add(new Word("light-house","abala",R.raw.ijaw_light_huz));
        words.add(new Word("sail","Abalaba",R.raw.ijaw_sail));
        words.add(new Word("Flat utensil for stirring food while cooking","abalabala",R.raw.ijaw_flat_utensil));
        words.add(new Word("Butterfly ","Abalabala",R.raw.ijaw_butterfly));
        words.add(new Word("Wedge","Abamu",R.raw.ijaw_wedge));
        words.add(new Word("Ceiling","abanmaaban",R.raw.ijaw_ceiling));
        words.add(new Word("blindman's buff","abẹẹrẹ",R.raw.ijaw_blindmanbuf));
        words.add(new Word("dagger","abẹrẹ",R.raw.ijaw_dagger));
        words.add(new Word("the sores in between the toes usually caused by wet feet","abei",R.raw.ijaw_the_sores));
        words.add(new Word("you (man). used to address a man","abein",R.raw.ijaw_man));
        words.add(new Word("tonguesole (fish)","abi-dein",R.raw.ijaw_tonguesole));
        words.add(new Word("dark night","abiala",R.raw.ijaw_dark_night));
        words.add(new Word("yesterday","abibai",R.raw.ijaw_yesterday));
        words.add(new Word("the day before yesterday","Abidi",R.raw.ijaw_day_before_yesta));
        words.add(new Word("Aligator","abo",R.raw.ijaw_aligator));
        words.add(new Word("a kind of bag woven from raffia or the leaves of screwpine","abobo",R.raw.ijaw_kind_ofbag));
        words.add(new Word("Mouth","Beke",R.raw.ijaw_mouth));
        words.add(new Word("a mark or marks made at the opposite ends of ones mouth for various reasons","Beke-na",R.raw.ijaw_marks));
        words.add(new Word("English","beke-wuru",R.raw.ijaw_english));
        words.add(new Word("Understands English","bele",R.raw.ijaw_understands_english));
        words.add(new Word("gin, ogogoro","Bele",R.raw.ijaw_gin));
        words.add(new Word("pot; cooking pot","beni",R.raw.ijaw_cooking_pot));
        words.add(new Word("Today","berenanaghe",R.raw.ijaw_today));
        words.add(new Word("plantain","beri",R.raw.ijaw_plantain));
        words.add(new Word("have no case","beriba",R.raw.ijaw_have_no_case));
        words.add(new Word("ear","bibe",R.raw.ijaw_ear));
        words.add(new Word("forever","bibi",R.raw.ijaw_forever));
        words.add(new Word("evening","Bidi",R.raw.ijaw_evening));
        words.add(new Word("Cloth","bie",R.raw.ijaw_cloth));
        words.add(new Word("Ask First","Bile",R.raw.ijaw_ask_first));
        words.add(new Word("the mind; the thought; the heart","Bina",R.raw.ijaw_the_mind));
        // words.add(new Word("Submerge in water; capsize; drown","Bina-owei",R.raw.ijaw_));
        words.add(new Word("Relation","Bina-rai",R.raw.ijaw_relation));
        words.add(new Word("Brother","bini",R.raw.ijaw_brother));
        words.add(new Word("Sister","",R.raw.ijaw_sister));  // look out for its translation in ijaw

      */


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