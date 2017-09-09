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
public class YorubaFragment extends Fragment {


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
    public YorubaFragment() {
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
//        switch (item.getItemId()){
//            case R.id.about_nitran:
//                //do something
//                return true;
//            case R.id.credit_nitran:
//                // call its activity
//                return true;
//            case R.id.search_bar:
//                // call search bar up
//                return true;
//            case R.id.contribute_nitran:
//                //send message and attachment file
//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.setData(Uri.parse("mailto:"));
//                intent.putExtra(Intent.EXTRA_EMAIL,"talk2us@co2globalservices.com");
//                intent.putExtra(Intent.EXTRA_SUBJECT,"Thank you for Contributing");
////                if(intent.resolveActivity(getPackageManager()) !=null){
////                startActivity(intent);
////            }
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
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

        words.add(new Word("Abandon","kọ̀ sílẹ̀",R.raw.yoru_abandon));
        words.add(new Word("Abduct","gbé sálọ",R.raw.yoru_abduct));
        words.add(new Word("Abide","bá gbé",R.raw.yoru_abide));
        words.add(new Word("Ablaze","gbiná",R.raw.yoru_ablaze));
        words.add(new Word("Abortion","ṣíṣẹ́ oyún",R.raw.yoru_abortion));

        words.add(new Word("About","nípa ti",R.raw.yoru_about));
        words.add(new Word("Absent","kò wá",R.raw.yoru_absent));
        words.add(new Word("Absolutely","pátápátá",R.raw.yoru_absolutely));
        words.add(new Word("Absorb","fàmu",R.raw.yoru_absorb));
        words.add(new Word("Abstain","fà sëhìn",R.raw.yoru_abstain));
        words.add(new Word("Abundant","löpõl ọpõ",R.raw.yoru_abundant));
        words.add(new Word("Accept","tẹ́wögbà",R.raw.yoru_accept));
        words.add(new Word("Accomodate","fún láyè",R.raw.yoru_accomodate));
        words.add(new Word("Accuracy","ìṣegëgë",R.raw.yoru_accuracy));
        words.add(new Word("Accurate","dédé",R.raw.yoru_accurate));
        words.add(new Word("Acceptable","títẹ́wögbà",R.raw.yoru_accept));
        words.add(new Word("Accidentally","láìròtëlẹ̀",R.raw.yoru_acidentally));
        words.add(new Word("Come","wa",R.raw.yoru_come));
        words.add(new Word("Go","Nòò",R.raw.yoru_go));
        words.add(new Word("Good Afternoon","Eka sòòn",R.raw.yoru_gud_afternun));
        words.add(new Word("Good Evening","Eka lẹ̀",R.raw.yoru_gud_evnin));
        words.add(new Word("Good morning","Eka ròò",R.raw.yoru_gud_mornin));
        words.add(new Word("Old person","Agbalagba",R.raw.yoru_old_person));
        words.add(new Word("Sit down","Jo moo",R.raw.yoru_sit_down));
        words.add(new Word("Small person","Omòde",R.raw.yoru_small_person));
        words.add(new Word("Stand up","ide",R.raw.yoru_stand_up));
        words.add(new Word("Stomach","ikùn",R.raw.yoru_stomach));
        words.add(new Word("Till tomorrow","oda ròo",R.raw.yoru_till_2moro));



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