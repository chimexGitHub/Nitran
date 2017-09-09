package com.example.co2globalservices.ispeak1;


import android.app.SearchManager;
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
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HausaFragment extends Fragment {


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
    public HausaFragment() {
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

        words.add(new Word("Man","Namiji",R.raw.record000));
        words.add(new Word("Woman","matha",R.raw.record001));
        words.add(new Word("Boy","Yàro",R.raw.record002));
        words.add(new Word("Girl","N' matha",R.raw.record003));
        words.add(new Word("My Brother","yayana",R.raw.record004));
        words.add(new Word("My Sister","yayata",R.raw.record005));
        words.add(new Word("Food","Abunshi",R.raw.record006));
        words.add(new Word("Come","Kazo",R.raw.record007));
        words.add(new Word("Chair","Kujera",R.raw.record008));
        words.add(new Word("Table","Banba Kujera",R.raw.record009));
        words.add(new Word("Book","Takada",R.raw.record010));
        words.add(new Word("Biro/Pen","Al Kalami",R.raw.record011));
        words.add(new Word("Shirt","Jegere Riga",R.raw.record012));
        words.add(new Word("Trouser","wondo",R.raw.record013));
        words.add(new Word("School","Mankaranta",R.raw.record014));
        words.add(new Word("Good Morning","lnna kuana",R.raw.record015));
        words.add(new Word("Student","Yaro mankaranta",R.raw.record016));
        words.add(new Word("Teacher","Malamin",R.raw.record017));
        words.add(new Word("Market","Kasua",R.raw.record018));
        words.add(new Word("Church","Shurshe",R.raw.record019));
        words.add(new Word("Walk","Jewa",R.raw.record020));
        words.add(new Word("Work","Aiki",R.raw.record021));
        words.add(new Word("Onion","Alubosa",R.raw.record022));
        words.add(new Word("Rainy season","Lokoshi Ana ruwa",R.raw.record023));
        words.add(new Word("Harmattan season","Lokoshi nkarupi",R.raw.record024));
        words.add(new Word("Water","Ruwa",R.raw.record025));
        words.add(new Word("Shoe","Takalumi",R.raw.record026));
        words.add(new Word("Hand","Anu",R.raw.record027));
        words.add(new Word("Leg","Kapa",R.raw.record028));
        words.add(new Word("Time","Lokoshi",R.raw.record029));

        words.add(new Word("One","Daya",R.raw.record030)); //start here to verify
        words.add(new Word("Two","Biwu",R.raw.record031));
        words.add(new Word("Three","Huku",R.raw.record032));
        words.add(new Word("Five","Biyar",R.raw.record033));
        words.add(new Word("Six","Shida",R.raw.record034));
        words.add(new Word("Seven","Boquoy",R.raw.record035));
        words.add(new Word("Eight","Toquose",R.raw.record036));

       // words.add(new Word("Nine","Tara",R.raw.record037));  // look out for where the mistakeis, from one to nine

        words.add(new Word("Ten","Goma",R.raw.record037));
        words.add(new Word("House","Gida",R.raw.record038));
        words.add(new Word("My friend","Aboki na",R.raw.record039));
        words.add(new Word("Friend","Aboki",R.raw.record040));
        words.add(new Word("Mosque","Masalasi",R.raw.record041));
        words.add(new Word("#5( five naira)","Naira biyar",R.raw.record042));
        words.add(new Word("#10( ten naira)","Naira doma",R.raw.record043));
        words.add(new Word("#20(twenty naire)","Naira ishinrin",R.raw.record044));
        words.add(new Word("#50(fifty naira)","Naira amsin",R.raw.record045));
        words.add(new Word("#100(one hundred naira)","Naira dari",R.raw.record046));
        words.add(new Word("#200(two hundred naira)","Naira deri biwu",R.raw.record047));
        words.add(new Word("#500(five hundred naira)","Naira deri biyar",R.raw.record048));
        words.add(new Word("#1000(0ne thousand naira)","Naira dubu",R.raw.record049));
        words.add(new Word("Thank you","Nagode",R.raw.record050));  //remember to look it out, 50 appears twice




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