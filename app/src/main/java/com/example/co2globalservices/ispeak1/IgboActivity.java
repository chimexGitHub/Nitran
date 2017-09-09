package com.example.co2globalservices.ispeak1;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class IgboActivity extends AppCompatActivity {

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

        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new IgboFragment())
                .commit();

        // create and setup the {@link audioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // create a list of words

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("Another","özö",R.raw.anotherr));
        words.add(new Word("Beans","agwa",R.raw.beans));
        words.add(new Word("Blackman","onye ojii",R.raw.blakman));
        words.add(new Word("Boat","ügbö mmiri",R.raw.boat));
        words.add(new Word("Broom","azïza",R.raw.broom));
        words.add(new Word("Bush meat","anü öhïa",R.raw.bush_meat));
        words.add(new Word("Buy","gota",R.raw.buy));
        words.add(new Word("Buy Cloth","zuö akwa",R.raw.buy_cloth)); //gota akwa
        words.add(new Word("Buyer","özü ahïa",R.raw.buyerr));
        words.add(new Word("Car","ügbö ala",R.raw.car));
        words.add(new Word("Carry","buru",R.raw.carry));
        words.add(new Word("Centre","etiti",R.raw.centre));
        words.add(new Word("Chair","oche",R.raw.chair));
        words.add(new Word("chicken instead of meat","achö im ökukö kamanu",R.raw.chicken_intead_meat));
        words.add(new Word("school rymes","egwu umuakwukwo",R.raw.ryhm));
        words.add(new Word("Church day(sunday)","üböchï üka",R.raw.churchday));
        words.add(new Word("Class","clasï",R.raw.classss));
        words.add(new Word("Hold","jide",R.raw.possess_hold));
        words.add(new Word("Coconut","aku oyibo",R.raw.coconut));
        words.add(new Word("Cocoyam","ede",R.raw.cocoyam));
        words.add(new Word("Cold","oyi",R.raw.cold));
        words.add(new Word("Corn","öka",R.raw.corn));
        words.add(new Word("Because its good, I bought it","maka n'iya dimma kam ji goroya",R.raw.cos_its_good_i_bought_it));
        words.add(new Word("Cow","ehi",R.raw.cow));
        words.add(new Word("Customer(Buyer)","onye ahïa",R.raw.customer));
        words.add(new Word("Day break","ï bölachï",R.raw.day_break));
        words.add(new Word("Do not eat lest I go","erikwana nri tupu n'mu agaba",R.raw.do_not_eat_lest));
        words.add(new Word("Don't eat","erina",R.raw.dont_eat));
        words.add(new Word("Don't sweep","aza la",R.raw.dont_sweep));
        words.add(new Word("Door mouth (Entrance)","önüzö",R.raw.door_mouth));
        words.add(new Word("Please","biko m",R.raw.please));
        words.add(new Word("Earth (ground)","ala",R.raw.earth));
        words.add(new Word("Egg","akwa",R.raw.egg));
        words.add(new Word("Eight","asatö",R.raw.eight));
        words.add(new Word("Eighty","iri asatö",R.raw.eighty));
        words.add(new Word("Eleven","iri n'otu",R.raw.eleven));
        words.add(new Word("First","mbu",R.raw.first));
        words.add(new Word("Fish","azü",R.raw.fish));
        words.add(new Word("Five","ise",R.raw.five));
        words.add(new Word("Five house","ulö ise",R.raw.five_house));
        words.add(new Word("Food","nri",R.raw.food));
        words.add(new Word("Food house(box)","akpatï nrï",R.raw.food_house_box));
        words.add(new Word("Food house (Hotel)","ülö oriri",R.raw.food_house_hotel));
        words.add(new Word("Four","anö",R.raw.four));
        words.add(new Word("Fourth","nka anö",R.raw.fourth));
        words.add(new Word("Fowl eggs","akwa ökukö",R.raw.fowls_egg));
        words.add(new Word("Give","nye",R.raw.give));
        words.add(new Word("Give me money","nye m'ego",R.raw.give_me_money));
        words.add(new Word("Go","gaba",R.raw.go));
        words.add(new Word("Goat","ewu",R.raw.goat));
        words.add(new Word("Good","öma",R.raw.good));
        words.add(new Word("Good as its cheap","ödima etu osiwedi olu'ana",R.raw.good_as_its_cheap));
        words.add(new Word("Good but cost","iya marama m'na ödi önu",R.raw.good_but_cost));
        words.add(new Word("Guinea fowl","öbögwü",R.raw.guinea_fowl));
        words.add(new Word("Half","ökara",R.raw.half));
        words.add(new Word("Half naira","ökara naira",R.raw.half_naira));
        words.add(new Word("Harmattan period","oge uguru",R.raw.harmattan_period));
        words.add(new Word("He lives","o bi",R.raw.he_lives));
        words.add(new Word("Head ache","isi mgbu",R.raw.headache));
        words.add(new Word("Height (Up)","elü",R.raw.height_up));
        words.add(new Word("Her Husband","di ya",R.raw.her_husband));
        words.add(new Word("House","ulö",R.raw.house));
        words.add(new Word("How","ole",R.raw.how));
        words.add(new Word("To think","iche uche",R.raw.to_think));
        words.add(new Word("Husband","di",R.raw.husband));
        words.add(new Word("I live","ebi m",R.raw.i_live));
        words.add(new Word("I am first","abu mbu",R.raw.i_am_first));
        words.add(new Word("Igbo man","onye igbo",R.raw.igboman));
        words.add(new Word("Inside house","nime ülö",R.raw.inside_house));
        words.add(new Word("Pot","ite",R.raw.ite_pot));
        words.add(new Word("Jesus Christ","jesu kristi",R.raw.jesus_christ));
        words.add(new Word("Key","igodo",R.raw.key));
        words.add(new Word("Let them go home","ka' ha ga",R.raw.let_dem_go));
        words.add(new Word("Lets go home","kanyi la",R.raw.lets_go_home));
        words.add(new Word("Let us go","kanyi ga",R.raw.letz_us_go));
        words.add(new Word("Light","öku",R.raw.light));
        words.add(new Word("Look","lee",R.raw.look));
        words.add(new Word("Market","ahia",R.raw.market));
        words.add(new Word("You and I are here","munagi nö eba",R.raw.me_and_you_are_here));
        words.add(new Word("You and I carried him","munagi buruya",R.raw.me_and_you_carrid_him));
        words.add(new Word("Meat","anu",R.raw.meat));
        words.add(new Word("Money","ego",R.raw.money));
        words.add(new Word("Moon","önwa",R.raw.moon));
        words.add(new Word("Morning","ütütü",R.raw.morning));
        words.add(new Word("Motorcycle","ögba tumtum",R.raw.motor_cycle));
        words.add(new Word("Motorpark","ödu ugbö ala",R.raw.motor_park));
        words.add(new Word("My friend","eyim",R.raw.my_friend));
        words.add(new Word("My brother","nwanne m",R.raw.my_brother));
        words.add(new Word("My child","nwa m",R.raw.my_child));
        words.add(new Word("My husband","di m",R.raw.my_husband));
        words.add(new Word("Name","aha",R.raw.name));
        words.add(new Word("Need(find)","chörö",R.raw.need_find));
        words.add(new Word("Nine","itolu",R.raw.nine));
        words.add(new Word("Ninety","iri itolu",R.raw.ninety));
        words.add(new Word("Nothing","onwerözi",R.raw.nothing));
        words.add(new Word("Now","ügbua",R.raw.now));
        words.add(new Word("One billion","ijeri",R.raw.one_billion));
        words.add(new Word("One house","otu ulör",R.raw.one_house));
        words.add(new Word("One hundred","narï",R.raw.one_hundred));
        words.add(new Word("One million","nde",R.raw.one_million));
        words.add(new Word("One naira","otu aira",R.raw.one_naira));
        words.add(new Word("One thousand","puku",R.raw.one_thousand));
        words.add(new Word("Onion","alïbasa",R.raw.onion));
        words.add(new Word("Our child","nwa anyi",R.raw.our_child));
        words.add(new Word("Palm oil","nmanu",R.raw.palm_oil));
        words.add(new Word("Pear","ube",R.raw.pear));
        words.add(new Word("Pepper","ose",R.raw.pepper));
        words.add(new Word("Pig","ezi",R.raw.pig));
        words.add(new Word("Plane (kite)","egbe igwe",R.raw.plane_kite));
        words.add(new Word("Plate","efere",R.raw.plate));
        words.add(new Word("Near","nso",R.raw.close_nearby));
       // words.add(new Word("Pay debt(make payment)","kwö ugwö",R.raw.));
        words.add(new Word("Pumpkin leaf","ugu",R.raw.pumpkin));
        words.add(new Word("Put","tinye",R.raw.put));
        words.add(new Word("Rain","nmiri ozizo",R.raw.rain));
        words.add(new Word("Right hand","aka nri",R.raw.right_hand));
        words.add(new Word("Say it","kwo ya",R.raw.say_it));
        words.add(new Word("Second","n' kabuö",R.raw.second));
        words.add(new Word("Seller","ore ihe",R.raw.seller));
        words.add(new Word("Senior man","onye isi",R.raw.senior_man));
        words.add(new Word("Seven","asaa",R.raw.seven));
        words.add(new Word("Sister","nwanne nwayi",R.raw.sister));
        words.add(new Word("Six","isi",R.raw.six));
        words.add(new Word("Snake","agwö",R.raw.snake));
        words.add(new Word("So that","ka",R.raw.so_that));
        words.add(new Word("Sombody","onye",R.raw.sombody));
        words.add(new Word("Soup","ofe",R.raw.soup));
        words.add(new Word("Spoon","ngazi",R.raw.spoon));
        words.add(new Word("Stockfish","okporoko",R.raw.stock_fish));
        words.add(new Word("Tell","gwa",R.raw.tell));
        words.add(new Word("Tell me","gwa mu",R.raw.tell_me));
        words.add(new Word("Ten","iri",R.raw.ten));
        words.add(new Word("The first house","ulö mbu",R.raw.the_first_house));
        words.add(new Word("The second house","ulö nk'abuö",R.raw.the_second_huz));
        words.add(new Word("Third","n katö",R.raw.third));
        words.add(new Word("Thirteen","iri natö",R.raw.thirteen));
        words.add(new Word("Thirty","iri atö",R.raw.thirty));
        words.add(new Word("Thirty one","iri atö n'otu",R.raw.thirty_one));
        words.add(new Word("those people","ndi",R.raw.those_people));
        words.add(new Word("Three","atö",R.raw.three));
        words.add(new Word("Three thousand,two ten(3,210)","puku atö na iri abüö na iri",R.raw.three_thousand_two_ten));
        words.add(new Word("To back","ikwö",R.raw.to_back));
        words.add(new Word("To do","ime",R.raw.to_do));
        words.add(new Word("To eat","ita",R.raw.to_eat));
        words.add(new Word("Thought(idea)","echiche",R.raw.tought_idea));
        words.add(new Word("Today","tata",R.raw.today));
        words.add(new Word("Toilet","ülö mposi",R.raw.toilet));
        words.add(new Word("Tomorrow","echi",R.raw.tomorrow));
        words.add(new Word("Early","n' oge",R.raw.early));
        words.add(new Word("Travel","nje m",R.raw.travel));
        words.add(new Word("Turkey","tolotolo",R.raw.turkey));
        words.add(new Word("Twelve","iri abuö",R.raw.twelve));
        words.add(new Word("Twenty one","iri abuö n'otu",R.raw.twenty_one));
        words.add(new Word("Two","abuö",R.raw.two));
        words.add(new Word("Two hundred","n'anri abuö",R.raw.two_hundred));
        words.add(new Word("Two naira(#2)","naira abuö",R.raw.two_naira));
        words.add(new Word("How","udi",R.raw.how));
        words.add(new Word("You people should not sweep the ground","unu azana ala",R.raw.youppl_shud_nt_swip_d_grand));
        words.add(new Word("Wait","chere",R.raw.wait));
        words.add(new Word("Water","mmiri",R.raw.water));
        words.add(new Word("Waterleaf","mgbörödi",R.raw.water_leaf));
        words.add(new Word("We carried you","anyi buru gi",R.raw.we_carried_you));
        words.add(new Word("Welcome","nnö",R.raw.welcome));
        words.add(new Word("Welldone","daalu",R.raw.weldone));
        words.add(new Word("When am done","nmesia",R.raw.when_am_done));
        words.add(new Word("Where","ebe",R.raw.where));
        words.add(new Word("White","öcha",R.raw.white));
        words.add(new Word("Whiteman","onye öcha",R.raw.whyteman));
        words.add(new Word("Wood","osisi",R.raw.wood));
        words.add(new Word("Work","ölu",R.raw.work));
        words.add(new Word("Worker","onye ölu",R.raw.worker));
        words.add(new Word("Workers(group)","ndi ölu",R.raw.workers_group));
        words.add(new Word("Writer","onye ode akwukwö",R.raw.writer));
        words.add(new Word("Yam tuber","mba ji",R.raw.yam_tuber));
        words.add(new Word("You live","i bi",R.raw.you_live));
        words.add(new Word("You carried me","unu buru mu",R.raw.you_carried_me));
        words.add(new Word("Young boy","nwawoke",R.raw.young_boy));
        words.add(new Word("Young girl","nwatakiri nwanyi",R.raw.young_girl));
        words.add(new Word("Your husband","di gi",R.raw.your_usband));
        words.add(new Word("You should sweep the ground","unu za ala",R.raw.you_should_sweep_d_grand));



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


                    mMediaPlayer= MediaPlayer.create(IgboActivity.this,word.getAudioResourceId());
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
