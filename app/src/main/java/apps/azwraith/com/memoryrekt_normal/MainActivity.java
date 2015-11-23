package apps.azwraith.com.memoryrekt_normal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.provider.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    public int currentimage2index=0;
    public int currentimage2index2=0;
    public int lives_counter = 3;
    public int dimen_count = 0;
    public String num_s;
    ImageView border_img;
    ImageView hero_p;
    TextView skill;
    ImageView circle;
    TextView countd;
    public int test=0;
    public int test2 = 0;
    public String hero_name="";
    CountDownTimer ct;
    CountDownTimer ct2;
    ScrollView scroll;
    private RelativeLayout main_layout;
    private LinearLayout verticalOuterLayout2;
    private ScrollView verticalScrollView2;
    private ScrollView modeScrollView;
    private LinearLayout verticalOuterLayout;
    private ScrollView verticalScrollview;
    private TableLayout table_mode;
    private int verticalScrollMax;
    private Timer scrollTimer		=	null;
    private TimerTask clickSchedule;
    private TimerTask scrollerSchedule;
    private TimerTask faceAnimationSchedule;
    private int scrollPos =	0;
    private Boolean isFaceDown      =   true;
    private Timer clickTimer		=	null;
    private Timer faceTimer         =   null;
    private ImageView clickedButton    =   null;
    private ImageView clickedButton2 = null;
    private ImageView lives_count;
    private TextView lives_text;
    private TextView dimension123;
    private TextView about;
    private ImageView level2;




    TextView[] views =  new TextView[3];







    String[] levels = {"1.Beginner", "2.Casual", "3.Intermediate", "4.Enthusiast", "5.Expert", "6.Professional"};
    String[] SKILLS1_NAME = {"Storm Hammer", "Fissure", "X Marks the Spot", "Avalanche", "Toss", "Power Cogs", "Rocket Flare",
            "Hookshot", "Purification", "Repel", "Acid Spray", "Greevil's Greed", "Burning Spear", "Tether",
            "Hoofstomp", "Stampede", "Ice shards", "Snowball", "Walrus PUNCH!", "Duel", "Insatiable Hunger", "Culling Blade",
            "Meat Hook", "Rot", "Burrowstrike", "Sand storm", "Epicenter", "Amplify Damage", "Ravage",
            "Reincarnation", "Charge of Darkness", "Doom", "Tombstone", "Reverse Polarity", "Borrowed Time",
            "Blink", "Frost Arrows", "Healing ward", "Omnislash", "Smoke screen", "Permanent Invisibility",
            "Shrapnel", "Headshot", "Take Aim", "Assasinate", "Eclipse", "Shuriken toss",
            "Track", "Song of the siren", "Thirst", "Rupture", "Shadowraze", "Necromastery",
            "Backtrack", "Chronosphere", "Stifling dagger", "Blur", "Poof", "Pounce", "Frostbite",
            "Dream coil", "Ball lightning", "Shackleshot", "Powershot", "Thundergod's wrath",
            "Laguna blade", "Hex", "Mass serpent ward", "Shackles", "Laser", "March of the machines",
            "Rearm", "Sprout", "Teleportation", "Global silence", "Multicast", "Spell steal",
            "Glimpse", "Chakra magic", "Ice armor", "Sacrifice", "Finger of death", "Hex", "Mana drain", "Death ward",
            "Black hole", "Heartstopper Aura", "Blink", "Exorcism", "Shallow grave", "Ion shell", "Vacuum",
            "Cold feet", "Cold snap", "Exort", "Ghost walk", "Invoke", "Quas", "Sunstrike", "Wex"};

    String[] SKILLS2_NAME = {"Great Cleave", "God's Strength", "Aftershock", "Echo Slam", "Torrent", "Tidebringer", "Ghostship", "Craggy Exterior",
            "Grow", "Breathe Fire", "Dragon Tail", "Elder Dragon Form", "Battery Assault", "Guardian Angel", "Unstable Concoction", "Chemical Rage",
            "Inner Vitality", "Life Break", "Leech Seed", "Living Armor", "Overgrowth", "Spirits", "Relocate", "Double Edge", "Viscous Nasal Goo",
            "Quill Spray", "Bristleback", "Chakram", "Echo Stomp", "Astral Spirit", "Frozen Sigil", "Icarus Dive", "Supernova", "Berserker's Call",
            "Counter Helix", "Flesh Heap", "Dismember", "Sprint", "Slithereen Crush", "Gush", "Kraken Shell", "Anchor Smash", "Wraithfire Blast",
            "Vampiric Aura", "Rage", "Open Wounds", "Infest", "Empowering Haste", "Nether Strike", "Void", "Darkness", "Devour", "Scorched Earth",
            "LVL? Death", "Decay", "Soul Rip", "Summon Wolves", "Howl", "Shapeshift", "Phantasm", "Empower", "Skewer", "Mist Coil", "Aphotic Shield",
            "Mana Break", "Spell Sheild", "Mana Void", "Gust", "Blade Fury", "Star Storm", "Sacred Arrow", "Leap", "Moonlight Shadow", "Waveform",
            "Morph(Agility)", "Morph(Strength)", "Spirit Lance", "Doppleganger", "Magic missile", "Wave of Terror", "Nether swap", "Backstab",
            "Blink Strike", "Refraction", "Meld", "PSI Blade", "Lucent Beam", "Moon glaive", "Shadow Walk", "Earthshock", "Overpower", "Fury Swipes",
            "Rocket Barrage", "Homing Missile", "Summon Spirit Bear", "Mirror Image", "Ensnare", "Rip Tide", "Song of the Siren End", "Whirling Axe(Ranged)",
            "Whirling Axe(Melee)", "Battle Trance", "Searing Chains", "Sleight of fist", "Fire remnant", "Bloodrage", "Blood Rite", "Presence of Dark Lord",
            "Requiem of Souls", "Plasma feild", "Static link", "Venomous Gale", "Poison Nova", "Time Walk", "Time lock", "Phantom Strike", "Coup de grace",
            "Corrosive Skin", "Viper Strike", "Searing Arrow", "Skeleton Walk", "Spawn Spiderlings", "Spin Web", "Shukuchi", "Time lapse", "Spectral dagger",
            "Desolate", "Haunt", "Divided We Stand", "Spiked Carapace", "Vendetta", "Dark Pact", "Essence Shift", "Shadow Dance", "Split Shot", "Mystic Snake",
            "Reflection", "Metamorphosis", "Crystal Nova", "Arcane Aura", "Freezing Field", "Illusory Orb", "Static remnant", "Electric vortex", "Focus Fire",
            "Windrun", "Arc Lightning", "Lightning Bolt", "Fiery Soul", "Light Strike Array", "Heat Seeking Missile", "Nature's Call", "Wrath of Nature",
            "Enchant", "Nature's Attendants", "Ice Path", "Liquid Fire", "Hand Of God", "Curse of the Silent", "Glaives of Wisdom", "Last Word", "Bloodlust",
            "Fireblast", "Ignite", "Fade Bolt", "Kinetic Field", "Static Storm", "Illuminate", "Mana Leak", "Recall", "Spirit Form", "Mystic Flare", "Purifying Flames",
            "Land Mines", "Minefield Sign", "Remote Mines", "Suicide Squad, Attack", "Brain Sap", "Fiend's Grip", "Chain Frost", "Frost Blast", "Earth Spike",
            "Paralyzing Cask", "Demonic Conversion", "Midnight Pulse", "Death Pulse", "Reaper's Scythe", "Choatic Offering", "Fatal Bonds", "Shadow Words",
            "Scream of Pain", "Sonic Wave", "Crypt Swarm", "Life Drain", "Nether Blast", "Nether Ward", "Nether Blast", "Poison Touch", "Shadow Wave", "Lightning Storm",
            "Split Earth", "Surge", "Wall of Replica", "Flamming Lasso", "Sticky Napalm", "Ice Blast", "Alacrity", "Chaos Meter", "EMP", "Forge Spirit", "Ice Wall", "Tornado",
            "Arcane Orb", "Astral Imprisonment", "Sanity's Eclipse", "Summon Familiars", "Cold Embrace", "Winter's Curse"};


    int[] SKILLS1_IDS = {R.drawable.hammer, R.drawable.fissure, R.drawable.xmark, R.drawable.avalanche, R.drawable.toss, R.drawable.cogs, R.drawable.flare,
            R.drawable.hookshot, R.drawable.purification, R.drawable.repel, R.drawable.spray, R.drawable.greed, R.drawable.spear, R.drawable.tether,
            R.drawable.stomp, R.drawable.stampede, R.drawable.shards, R.drawable.snowball, R.drawable.punch, R.drawable.duel, R.drawable.hunger, R.drawable.culling,
            R.drawable.hook, R.drawable.rot, R.drawable.burrow, R.drawable.sandstorm, R.drawable.epicenter, R.drawable.amplify, R.drawable.ravage,
            R.drawable.reincarnation, R.drawable.charge, R.drawable.doom_, R.drawable.tomb, R.drawable.reverse, R.drawable.borrowed,
            R.drawable.blink, R.drawable.frost, R.drawable.healing, R.drawable.slash, R.drawable.smoke, R.drawable.inv,
            R.drawable.shrapnel, R.drawable.headshot, R.drawable.take, R.drawable.assas, R.drawable.eclipse, R.drawable.shuriken,
            R.drawable.track, R.drawable.song, R.drawable.thirst, R.drawable.rupture, R.drawable.shadowrage, R.drawable.necromastery,
            R.drawable.backtrack, R.drawable.chrono, R.drawable.dagger, R.drawable.blur, R.drawable.poof, R.drawable.pounce, R.drawable.frostbite,
            R.drawable.coil, R.drawable.ball, R.drawable.shackleshot, R.drawable.powershot, R.drawable.wrath,
            R.drawable.laguna, R.drawable.hex, R.drawable.mass, R.drawable.shackles, R.drawable.laser, R.drawable.march,
            R.drawable.rearm, R.drawable.sprout, R.drawable.teleportation, R.drawable.silence, R.drawable.multicast, R.drawable.steal,
            R.drawable.glimpse, R.drawable.magic, R.drawable.armor_lich, R.drawable.sacrifice, R.drawable.finger, R.drawable.hex_lion, R.drawable.drain, R.drawable.death_w,
            R.drawable.black, R.drawable.heartstopper, R.drawable.blink_qop, R.drawable.exorcism, R.drawable.grave, R.drawable.ion, R.drawable.vacuum,
            R.drawable.cold, R.drawable.snap, R.drawable.exort, R.drawable.ghost, R.drawable.invoke, R.drawable.quas, R.drawable.sun, R.drawable.wex};


    int[] SKILLS2_IDS = {R.drawable.cleave, R.drawable.strength, R.drawable.aftershock, R.drawable.echo_slam, R.drawable.torrent, R.drawable.tidebringer, R.drawable.ghostship, R.drawable.exterior,
                         R.drawable.grow, R.drawable.fire, R.drawable.tail, R.drawable.elder, R.drawable.assault, R.drawable.angel, R.drawable.unstable, R.drawable.rage, R.drawable.inner, R.drawable.life, R.drawable.leech,
                         R.drawable.armor, R.drawable.overgrowth, R.drawable.spirits, R.drawable.relocate, R.drawable.double2, R.drawable.goo, R.drawable.quill, R.drawable.bristle, R.drawable.chakram,
                         R.drawable.echo, R.drawable.astral, R.drawable.frozen, R.drawable.icarus, R.drawable.supernova, R.drawable.call, R.drawable.helix, R.drawable.heap, R.drawable.dismember, R.drawable.sprint,
                         R.drawable.crush, R.drawable.shell, R.drawable.anchor, R.drawable.hellfire, R.drawable.vampiric, R.drawable.rage_life, R.drawable.open, R.drawable.infest, R.drawable.haste, R.drawable.nether,
                         R.drawable.void2, R.drawable.darkness, R.drawable.devour, R.drawable.scorched, R.drawable.lvl, R.drawable.decay, R.drawable.soul, R.drawable.summon, R.drawable.howl, R.drawable.shapeshift, R.drawable.phantasm,
                         R.drawable.empower, R.drawable.skewer, R.drawable.mist, R.drawable.shield, R.drawable.mbreak, R.drawable.spell, R.drawable.mana, R.drawable.wave, R.drawable.fury, R.drawable.starfall, R.drawable.sacred, R.drawable.leap,
                         R.drawable.shadow, R.drawable.waveform, R.drawable.morph_agi, R.drawable.morph_str, R.drawable.lance, R.drawable.dopple, R.drawable.missile, R.drawable.terror,
                         R.drawable.swap, R.drawable.backstab, R.drawable.blinkstr, R.drawable.refraction, R.drawable.meld, R.drawable.psi, R.drawable.beam, R.drawable.moon, R.drawable.earthshock,
                         R.drawable.overpower, R.drawable.swipes, R.drawable.barrage, R.drawable.homing, R.drawable.summon_bear, R.drawable.mirror, R.drawable.ensnare, R.drawable.riptide, R.drawable.cancel,
                         R.drawable.whirling_r, R.drawable.whirling_m, R.drawable.trance, R.drawable.searing, R.drawable.sleight, R.drawable.fire_r, R.drawable.bloodrage, R.drawable.blood, R.drawable.presence,
                         R.drawable.requiem, R.drawable.plasma, R.drawable.link, R.drawable.gale, R.drawable.nova, R.drawable.walk, R.drawable.lock, R.drawable.phantom, R.drawable.coup, R.drawable.corossive,
                         R.drawable.viper_s, R.drawable.searing_clinkz, R.drawable.skeleton, R.drawable.spiderlings, R.drawable.web, R.drawable.shukuchi, R.drawable.lapse, R.drawable.spectral, R.drawable.desolate,
                         R.drawable.haunt, R.drawable.divided, R.drawable.spiked, R.drawable.vendetta, R.drawable.dark, R.drawable.essence, R.drawable.shadowd, R.drawable.split, R.drawable.mystic, R.drawable.reflection,
                         R.drawable.metamorphis, R.drawable.crystal, R.drawable.arcane, R.drawable.freezing, R.drawable.orb, R.drawable.static2, R.drawable.vortex, R.drawable.focus, R.drawable.windrun, R.drawable.arc,
                         R.drawable.bolt_zeus, R.drawable.fiery, R.drawable.light, R.drawable.hs_missile, R.drawable.nature, R.drawable.enchant, R.drawable.attendants, R.drawable.ice, R.drawable.liquid, R.drawable.hand,
                         R.drawable.hand, R.drawable.curse_silencer, R.drawable.wisdom, R.drawable.last, R.drawable.bloodlust, R.drawable.fireblast, R.drawable.ignite, R.drawable.fade, R.drawable.kinetic, R.drawable.storm,
                         R.drawable.illuminate, R.drawable.leak, R.drawable.recall, R.drawable.form, R.drawable.mystic, R.drawable.flames, R.drawable.mines, R.drawable.sign, R.drawable.remote, R.drawable.suicide,
                         R.drawable.sap, R.drawable.grip, R.drawable.chain, R.drawable.frost, R.drawable.frost_lich, R.drawable.spike, R.drawable.cask, R.drawable.demonic, R.drawable.pulse, R.drawable.pulse_necro,
                         R.drawable.scythe, R.drawable.chaotic, R.drawable.fatal, R.drawable.word, R.drawable.scream, R.drawable.sonic, R.drawable.crypt, R.drawable.life, R.drawable.blast, R.drawable.nether_w, R.drawable.touch,
                         R.drawable.wave_dazzle, R.drawable.lightning, R.drawable.split_earth, R.drawable.surge, R.drawable.wall, R.drawable.flaming, R.drawable.napalm, R.drawable.blast, R.drawable.alacrity, R.drawable.chaos,
                         R.drawable.emp, R.drawable.forge, R.drawable.ice_wall, R.drawable.tornado, R.drawable.arcane_o, R.drawable.astral_i, R.drawable.sanity, R.drawable.familiars, R.drawable.embrace, R.drawable.curse_winter};

    int[] IMAGE_IDS = {R.drawable.earthshaker, R.drawable.sven, R.drawable.tiny,
            R.drawable.kunkka, R.drawable.beastmaster, R.drawable.dragon_knight, R.drawable.clockwerk, R.drawable.omniknight, R.drawable.huskar, R.drawable.alchemist, R.drawable.brewmaster, R.drawable.treant_protecter, R.drawable.wisp, R.drawable.centaur_warrunner, R.drawable.timbersaw, R.drawable.bristleback, R.drawable.tusk, R.drawable.elder_titan};

    String[] IMAGE_NAMES = {"Earthshaker", "Sven", "Tiny", "Kunkka", "Beastmaster", "Dragon_Knight", "Clockwerk", "Omniknight", "Huskar", "Alchemist", "Brewmaster", "Treant_Protector", "Wisp", "Centaur_Warrunner", "Timbersaw", "Bristleback", "Tusk", "Elder_Titan"};

    int[] IMAGE_IDS2 = {R.drawable.legion_commander, R.drawable.earth_spirit, R.drawable.phoenix,
            R.drawable.axe, R.drawable.pudge, R.drawable.sand_king, R.drawable.slardar, R.drawable.tidehunter, R.drawable.wraith_king, R.drawable.lifestealer, R.drawable.night_stalker, R.drawable.doom, R.drawable.spirit_breaker, R.drawable.lycan, R.drawable.chaos_knight, R.drawable.undying, R.drawable.magnus, R.drawable.abaddon};

    String[] IMAGE_NAMES2 = {"Legion_Commander", "Earth_Spirit", "Phoenix", "Axe", "Pudge", "Sand_king", "Slardar", "Tidehunter", "Wraith_King", "Lifestealer", "Night_Stalker", "Doom", "Spirit_Breaker", "Lycan", "Chaos_Knight", "Undying", "Magnus", "Abaddon"};

    int[] IMAGE_IDS3 = {R.drawable.anti_mage, R.drawable.drow_ranger, R.drawable.juggernaut,
            R.drawable.mirana, R.drawable.morphling, R.drawable.phantom_lancer, R.drawable.vengeful_spirit, R.drawable.riki, R.drawable.sniper, R.drawable.templar_assassin, R.drawable.luna, R.drawable.bounty_hunter, R.drawable.ursa, R.drawable.gyrocopter, R.drawable.lone_druid, R.drawable.naga_siren, R.drawable.troll_warlord, R.drawable.ember_spirit};

    String[] IMAGE_NAMES3 = {"Anti_Mage", "Drow_Ranger", "Juggernaut", "Mirana", "Morphling", "Phantom_Lancer", "Vengeful_Spirit", "Riki", "Sniper", "Templar_Assassin", "Luna", "Bounty_Hunter", "Ursa", "Gyrocopter", "Lone_Druid", "Naga_Siren", "Troll_Warlord", "Ember_Spirit"};

    int[] IMAGE_IDS4 = {R.drawable.bloodseeker, R.drawable.shadow_fiend, R.drawable.razor,
            R.drawable.venomancer, R.drawable.faceless_void, R.drawable.phantom_assassin, R.drawable.viper, R.drawable.clinkz, R.drawable.broodmother, R.drawable.weaver, R.drawable.spectre, R.drawable.meepo, R.drawable.nyx_assassin, R.drawable.slark, R.drawable.medusa, R.drawable.terrorblade, R.drawable.crystal_maiden, R.drawable.puck};

    String[] IMAGE_NAMES4 = {"Bloodseeker", "Shadow_Fiend", "Razor", "Venomancer", "Faceless_Void", "Phantom_Assasin", "Viper", "Clinkz", "Broodmother", "Weaver", "Spectre", "Meepo", "Nyx_Assassin", "Slark", "Medusa", "Terrorblade", "Crystal_Maiden", "Puck"};

    int[] IMAGE_IDS5 = {R.drawable.storm_spirit, R.drawable.windranger, R.drawable.zeus,
            R.drawable.lina, R.drawable.shadow_shaman, R.drawable.tinker, R.drawable.enchantress, R.drawable.jakiro, R.drawable.chen, R.drawable.silencer, R.drawable.ogre_magi, R.drawable.rubick, R.drawable.disruptor, R.drawable.keeper_of_the_light, R.drawable.skywrath_mage, R.drawable.oracle, R.drawable.techies};

    String[] IMAGE_NAMES5 = {"Storm_Spirit", "Windranger", "Zeus", "Lina", "Shadow_Shaman", "Tinker", "Enchantress", "Jakiro", "Chen", "Silencer", "Ogre_Magi", "Rubick", "Disruptor", "Keeper_of_the_Light", "Skywrath_mage", "Oracle", "Techies"};

    int[] IMAGE_IDS6 = {R.drawable.bane, R.drawable.lich, R.drawable.lion,
            R.drawable.witch_doctor, R.drawable.enigma, R.drawable.necrophos, R.drawable.warlock, R.drawable.queen_of_pain, R.drawable.death_prophet, R.drawable.pugna, R.drawable.dazzle, R.drawable.leshrac, R.drawable.dark_seer, R.drawable.batrider, R.drawable.ancient_apparition, R.drawable.invoker, R.drawable.obsidian_destroyer, R.drawable.shadow_demon, R.drawable.visage, R.drawable.winter_wyvern};

    String[] IMAGE_NAMES6 = {"Bane", "Lich", "Lion", "Witch_Doctor", "Enigma", "Necrophos", "Warlock", "Queen_of_Pain", "Death_Prophet", "Pugna", "Dazzle", "Leshrac", "Dark_Seer", "Batrider", "Ancient_Apparition", "Invoker", "Morphling", "Outworld_Devourer", "Shadow_Demon", "Visage", "Winter_Wyvern"};

    private ImageView img2;


    @Override @TargetApi(13)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dimension123 = (TextView)findViewById(R.id.dimensions);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        if(width >= 1000 && height >= 720)
        {
            dimen_count = 1;
            setContentView(R.layout.activity_main_hd);
        }

        dimension123.setText(String.valueOf(width) + " " + String.valueOf(height));


        main_layout = (RelativeLayout)findViewById(R.id.main_l);
        img2 = (ImageView) findViewById(R.id.imageView);
        img2.setBackgroundResource(R.drawable.dota_play);
        border_img = (ImageView) findViewById(R.id.intro);
        hero_p = (ImageView) findViewById(R.id.hero_portrait);
        skill = (TextView) findViewById(R.id.what_skill);
        countd = (TextView) findViewById(R.id.timer);
        circle = (ImageView) findViewById(R.id.countdown);
        scroll = (ScrollView) findViewById(R.id.scrollv);
        verticalOuterLayout = (LinearLayout)findViewById(R.id.vertical_outer_layout_id);
        verticalScrollview = (ScrollView) findViewById(R.id.vertical_scrollview_id);
        verticalOuterLayout2 = (LinearLayout)findViewById(R.id.vertical_outer_layout_id2);
        verticalScrollView2 = (ScrollView)findViewById(R.id.vertical_scrollview_id2);
        views[0] = (TextView)findViewById(R.id.ability1);
        views[1] = (TextView)findViewById(R.id.ability2);
        views[2] = (TextView)findViewById(R.id.ability3);
        modeScrollView = (ScrollView)findViewById(R.id.modescroll);
        table_mode = (TableLayout)findViewById(R.id.tablemode);
        lives_count = (ImageView)findViewById(R.id.lives);
        lives_text = (TextView)findViewById(R.id.lives_text);
        level2 = (ImageView)findViewById(R.id.level2);

        level2.setClickable(false);






        int ic_limit = Integer.parseInt(getFromSP2("slide"));

        for(int ic = 1; ic <=ic_limit; ic++)
        {
            addImagesToView();
        }


        ViewTreeObserver vto = verticalOuterLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                modeScrollView.scrollTo(0, modeScrollView.getBottom()/2);
                if (android.os.Build.VERSION.SDK_INT < 16) {
                    verticalOuterLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    verticalOuterLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                getScrollMaxAmount();
                startAutoScrolling();
            }
        });



    }





    public void onClick2(View v)
    {
        setContentView(R.layout.settings);
        CheckBox cb1, cb2;
        final EditText numslide;

        cb1 = (CheckBox)findViewById(R.id.checkBox);
        cb1.setChecked(getFromSP("cb1"));
        cb1.setOnCheckedChangeListener(this);
        cb2 = (CheckBox)findViewById(R.id.checkBox2);
        cb2.setChecked(getFromSP("cb2"));
        cb2.setOnCheckedChangeListener(this);
        about = (TextView)findViewById(R.id.about);
        numslide = (EditText)findViewById(R.id.numberslide);
        numslide.setText(getFromSP2("slide"));

        numslide.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                num_s = numslide.getText().toString();
                saveInSp2("slide", num_s);
            }
        });












        about.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        about.setBackgroundColor(Color.GRAY);
                        break;
                    case MotionEvent.ACTION_UP:

                        about.setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
        }

    private boolean getFromSP(String key) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("Memory Rekt!", Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

    private String getFromSP2(String key2)
    {
        SharedPreferences slide_num = getApplicationContext().getSharedPreferences("Memory Rekt!2", Context.MODE_PRIVATE);
        return slide_num.getString(key2, "5");
    }

    private void saveInSp2(String key2, String value2)
    {
        SharedPreferences slide_num = getApplicationContext().getSharedPreferences("Memory Rekt!2", android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = slide_num.edit();
        editor.putString(key2, value2);
        editor.commit();

    }

    private void saveInSp(String key,boolean value) {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("Memory Rekt!", android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    private void saveInSplevel(String keylvl, int valuelvl)
    {
        SharedPreferences lvlstat = getApplicationContext().getSharedPreferences("Memory Rekt!3", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = lvlstat.edit();
        editor.putInt(keylvl, valuelvl);
        editor.commit();
    }

    private int getFromSplevel(String keylvl)
    {
        SharedPreferences lvlstat = getApplicationContext().getSharedPreferences("Memory Rekt!2", Context.MODE_PRIVATE);
        return lvlstat.getInt(keylvl, 1);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView,
                                 boolean isChecked) {
        // TODO Auto-generated method stub
        switch(buttonView.getId()){
            case R.id.checkBox:
                saveInSp("cb1",isChecked);
                break;
            case R.id.checkBox2:
                saveInSp("cb2",isChecked);
                break;


        }

    }


    public void onClick3(View v)
    {
        Intent quitIntent = new Intent(Intent.ACTION_MAIN);
        quitIntent.addCategory(Intent.CATEGORY_HOME);
        quitIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(quitIntent);

    }






    public void onClick(View v) {

        main_layout.setBackgroundResource(R.drawable.sf);
        img2.setBackgroundResource(R.drawable.playc);
        modeScrollView.setVisibility(View.GONE);
        scroll.setVisibility(View.VISIBLE);





    }

    public void onClickLevel(View v)
    {
        main_layout.setBackgroundResource(R.drawable.axe_w);
        scroll.setVisibility(View.GONE);
        hero_p.setVisibility(View.VISIBLE);
        skill.setVisibility(View.VISIBLE);
        countd.setVisibility(View.VISIBLE);
        circle.setVisibility(View.VISIBLE);
        views[0].setVisibility(View.VISIBLE);
        views[1].setVisibility(View.VISIBLE);
        views[2].setVisibility(View.VISIBLE);
        lives_count.setVisibility(View.VISIBLE);
        lives_text.setVisibility(View.VISIBLE);
        portrait();

    }

    public void onClickLevel2(View v)
    {
        main_layout.setBackgroundResource(R.drawable.axe_w);
        scroll.setVisibility(View.GONE);
        hero_p.setVisibility(View.VISIBLE);
        skill.setVisibility(View.VISIBLE);
        countd.setVisibility(View.VISIBLE);
        circle.setVisibility(View.VISIBLE);
        views[0].setVisibility(View.VISIBLE);
        views[1].setVisibility(View.VISIBLE);
        views[2].setVisibility(View.VISIBLE);
        lives_count.setVisibility(View.VISIBLE);
        lives_text.setVisibility(View.VISIBLE);
        portrait2();

    }



    public void portrait() {


        views[0].setBackgroundResource(R.drawable.choice_box);
        views[0].setTextColor(Color.BLACK);
        views[1].setBackgroundResource(R.drawable.choice_box);
        views[1].setTextColor(Color.BLACK);
        views[2].setBackgroundResource(R.drawable.choice_box);
        views[2].setTextColor(Color.BLACK);



        ct = new CountDownTimer(50000, 1000) {

            public void onTick(long millisUntilFinished) {
                countd.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                countd.setText("");
                lives_counter--;
                if(lives_counter==0) {
                    currentimage2index=0;
                    levelReset();
                }
                else
                {
                    portrait();
                }
            }
        }.start();

        hero_p.setImageResource(SKILLS1_IDS[currentimage2index % SKILLS1_IDS.length]);

        test = 1;
        Random r = new Random();
        final int random = r.nextInt(3);
        Random r2 = new Random();

        if(lives_counter==3)
        {
            lives_count.setImageResource(R.drawable.lives3);
        }
        else if(lives_counter==2)
        {
            lives_count.setImageResource(R.drawable.lives2);
        }
        else if(lives_counter==1)
        {
            lives_count.setImageResource(R.drawable.lives1);
        }
        else
        {
            lives_count.setVisibility(View.GONE);
            lives_text.setVisibility(View.GONE);

        }


        views[random].setVisibility(View.VISIBLE);
        views[random].setText(SKILLS1_NAME[currentimage2index % SKILLS1_NAME.length]);

        views[random].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                views[random].setBackgroundResource(R.drawable.choice_box_clicked);
                views[random].setTextColor(Color.WHITE);
                Toast.makeText(getBaseContext(), "Correct!", Toast.LENGTH_SHORT).show();
                ct.cancel();
                if(SKILLS1_NAME[currentimage2index % SKILLS1_NAME.length].equals("Wex"))
                {
                    saveInSplevel("level", 2);
                    level2.setClickable(true);
                    levelReset();
                }
                else
                {
                    portrait();
                }
            }
        });


        for(TextView vz : views)
        {
            if(vz != views[random])
            {


                int random2 = r2.nextInt(SKILLS1_NAME.length);
                if(SKILLS1_NAME[random2] != SKILLS1_NAME[currentimage2index % SKILLS1_NAME.length])
                {
                    vz.setVisibility(View.VISIBLE);
                    vz.setText(SKILLS1_NAME[random2]);

                }
            }
        }



        for(int itest=0; itest<=2; itest++)
        {
            final int jtest = itest;

          if(views[itest] != views[random])
          {
              views[itest].setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      views[jtest].setBackgroundResource(R.drawable.choice_box_clicked);
                      views[jtest].setTextColor(Color.WHITE);
                      Toast.makeText(getBaseContext(), "Try Again!", Toast.LENGTH_SHORT).show();
                      lives_counter--;
                      if (lives_counter == 3) {
                          lives_count.setImageResource(R.drawable.lives3);
                      } else if (lives_counter == 2) {
                          lives_count.setImageResource(R.drawable.lives2);
                      } else if (lives_counter == 1) {
                          lives_count.setImageResource(R.drawable.lives1);
                      }
                      else
                      {
                          ct.cancel();
                          currentimage2index=0;
                          levelReset();


                      }
                  }
              });
          }
        }



        currentimage2index++;


    }

    public void onClick5(View v) {

        stopAutoScrolling();
        clearTimerTaks(clickSchedule);
        clearTimerTaks(scrollerSchedule);
        clearTimerTaks(faceAnimationSchedule);
        clearTimers(scrollTimer);
        clearTimers(clickTimer);
        clearTimers(faceTimer);

        clickSchedule         = null;
        scrollerSchedule      = null;
        faceAnimationSchedule = null;
        scrollTimer           = null;
        clickTimer            = null;
        faceTimer             = null;

        Intent intentInvo = new Intent(getBaseContext(), InvokerActivity.class);
        startActivity(intentInvo);

    }


    public void levelReset()
    {
        int lvls = getFromSplevel("level");
        if(lvls == 2)
        {
            level2.setBackgroundResource(R.drawable.level2);
        }
        lives_counter = 3;
        scroll.setVisibility(View.VISIBLE);
        hero_p.setVisibility(View.GONE);
        skill.setVisibility(View.GONE);
        countd.setVisibility(View.GONE);
        circle.setVisibility(View.GONE);
        views[0].setVisibility(View.GONE);
        views[1].setVisibility(View.GONE);
        views[2].setVisibility(View.GONE);
        lives_text.setVisibility(View.GONE);
        lives_count.setVisibility(View.GONE);
    }

    public void addImagesToView(){



        int i =0;
        while(i<IMAGE_IDS.length){
            final ImageView img = new ImageView(this);
            img.setImageResource(IMAGE_IDS[i]);
            final int j = i;
            img.setTag(i);
            img.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View arg0) {
                    if(isFaceDown){
                        if(clickTimer!= null){
                            clickTimer.cancel();
                            clickTimer			=	null;
                        }
                        clickedButton			=	(ImageView)arg0;
                        hero_name = IMAGE_NAMES[j];
                        stopAutoScrolling();
                        clickedButton.startAnimation(scaleFaceUpAnimation());
                        clickedButton.setSelected(true);
                        clickTimer				=	new Timer();

                        if(clickSchedule != null) {
                            clickSchedule.cancel();
                            clickSchedule = null;
                        }

                        clickSchedule = new TimerTask(){
                            public void run() {
                                startAutoScrolling();
                            }
                        };

                        clickTimer.schedule( clickSchedule, 1500);
                    }
                }
            });

            if(dimen_count==0) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
                img.setLayoutParams(params);
                verticalOuterLayout.addView(img);
            }
            else
            {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(250,250);
                img.setLayoutParams(params);
                verticalOuterLayout.addView(img);
            }
            i++;
        }
            i=0;

        while(i<IMAGE_IDS2.length){
            final ImageView img = new ImageView(this);
            img.setImageResource(IMAGE_IDS2[i]);
            final int j=i;
            img.setTag(i);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    if (isFaceDown) {
                        if (clickTimer != null) {
                            clickTimer.cancel();
                            clickTimer = null;
                        }
                        clickedButton = (ImageView) arg0;
                        hero_name = IMAGE_NAMES2[j];
                        stopAutoScrolling();
                        clickedButton.startAnimation(scaleFaceUpAnimation());
                        clickedButton.setSelected(true);
                        clickTimer = new Timer();

                        if (clickSchedule != null) {
                            clickSchedule.cancel();
                            clickSchedule = null;
                        }

                        clickSchedule = new TimerTask() {
                            public void run() {
                                startAutoScrolling();
                            }
                        };

                        clickTimer.schedule(clickSchedule, 1500);
                    }
                }
            });

            if(dimen_count==0) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
                img.setLayoutParams(params);
                verticalOuterLayout.addView(img);
            }
            else
            {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300,300);
                img.setLayoutParams(params);
                verticalOuterLayout.addView(img);
            }
            i++;
        }
            i=0;

        while(i<IMAGE_IDS3.length){
            final ImageView img = new ImageView(this);
            img.setImageResource(IMAGE_IDS3[i]);
            final int j=i;
            img.setTag(i);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    if (isFaceDown) {
                        if (clickTimer != null) {
                            clickTimer.cancel();
                            clickTimer = null;
                        }
                        clickedButton = (ImageView) arg0;
                        hero_name = IMAGE_NAMES3[j];
                        stopAutoScrolling();
                        clickedButton.startAnimation(scaleFaceUpAnimation());
                        clickedButton.setSelected(true);
                        clickTimer = new Timer();

                        if (clickSchedule != null) {
                            clickSchedule.cancel();
                            clickSchedule = null;
                        }

                        clickSchedule = new TimerTask() {
                            public void run() {
                                startAutoScrolling();
                            }
                        };

                        clickTimer.schedule(clickSchedule, 1500);
                    }
                }
            });

            if(dimen_count==0) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
                img.setLayoutParams(params);
                verticalOuterLayout.addView(img);
            }
            else
            {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(250,250);
                img.setLayoutParams(params);
                verticalOuterLayout.addView(img);
            }
            i++;
        }

        i=0;

        while(i<IMAGE_IDS4.length){
            test2 = 1;
            final ImageView img = new ImageView(this);
            img.setImageResource(IMAGE_IDS4[i]);
            final int j=i;
            img.setTag(i);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    if (isFaceDown) {
                        if (clickTimer != null) {
                            clickTimer.cancel();
                            clickTimer = null;
                        }
                        clickedButton = (ImageView) arg0;
                        hero_name = IMAGE_NAMES4[j];
                        stopAutoScrolling();
                        clickedButton.startAnimation(scaleFaceUpAnimation());
                        clickedButton.setSelected(true);
                        clickTimer = new Timer();

                        if (clickSchedule != null) {
                            clickSchedule.cancel();
                            clickSchedule = null;
                        }

                        clickSchedule = new TimerTask() {
                            public void run() {
                                startAutoScrolling();
                            }
                        };

                        clickTimer.schedule(clickSchedule, 1500);
                    }
                }
            });

            if(dimen_count==0) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
                img.setLayoutParams(params);
                verticalOuterLayout2.addView(img);
            }
            else
            {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(250, 250);
                img.setLayoutParams(params);
                verticalOuterLayout2.addView(img);
            }
            i++;
        }

            i =0;

        while(i<IMAGE_IDS5.length){
            test2 = 1;
            final ImageView img = new ImageView(this);
            img.setImageResource(IMAGE_IDS5[i]);
            final int j=i;
            img.setTag(i);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    if (isFaceDown) {
                        if (clickTimer != null) {
                            clickTimer.cancel();
                            clickTimer = null;
                        }
                        clickedButton = (ImageView) arg0;
                        hero_name = IMAGE_NAMES5[j];
                        stopAutoScrolling();
                        clickedButton.startAnimation(scaleFaceUpAnimation());
                        clickedButton.setSelected(true);
                        clickTimer = new Timer();

                        if (clickSchedule != null) {
                            clickSchedule.cancel();
                            clickSchedule = null;
                        }

                        clickSchedule = new TimerTask() {
                            public void run() {
                                startAutoScrolling();
                            }
                        };

                        clickTimer.schedule(clickSchedule, 1500);
                    }
                }
            });

            if(dimen_count==0) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
                img.setLayoutParams(params);
                verticalOuterLayout2.addView(img);
            }
            else
            {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(250, 250);
                img.setLayoutParams(params);
                verticalOuterLayout2.addView(img);
            }
            i++;
        }

        i=0;

        while(i<IMAGE_IDS6.length){
            test2=1;
            final ImageView img = new ImageView(this);
            img.setImageResource(IMAGE_IDS6[i]);
            final int j=i;
            img.setTag(i);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    if (isFaceDown) {
                        if (clickTimer != null) {
                            clickTimer.cancel();
                            clickTimer = null;
                        }
                        clickedButton2 = (ImageView) arg0;
                        hero_name = IMAGE_NAMES6[j];
                        stopAutoScrolling();
                        clickedButton2.startAnimation(scaleFaceUpAnimation());
                        clickedButton2.setSelected(true);
                        clickTimer = new Timer();

                        if (clickSchedule != null) {
                            clickSchedule.cancel();
                            clickSchedule = null;
                        }

                        clickSchedule = new TimerTask() {
                            public void run() {
                                startAutoScrolling();
                            }
                        };

                        clickTimer.schedule(clickSchedule, 1500);
                    }
                }
            });

            if(dimen_count==0) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
                img.setLayoutParams(params);
                verticalOuterLayout2.addView(img);
            }
            else
            {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(250, 250);
                img.setLayoutParams(params);
                verticalOuterLayout2.addView(img);
            }
            i++;
        }

    }

    public void getScrollMaxAmount(){
        int actualWidth = (verticalOuterLayout.getMeasuredHeight()-(256*3));
        verticalScrollMax   = actualWidth;
    }


    public void startAutoScrolling(){
        if (scrollTimer == null) {
            scrollTimer					=	new Timer();
            final Runnable Timer_Tick 	= 	new Runnable() {
                public void run() {
                    moveScrollView();
                }
            };

            if(scrollerSchedule != null){
                scrollerSchedule.cancel();
                scrollerSchedule = null;
            }
            scrollerSchedule = new TimerTask(){
                @Override
                public void run(){
                    runOnUiThread(Timer_Tick);
                }
            };

            scrollTimer.schedule(scrollerSchedule, 30, 30);
        }
    }


    public void moveScrollView(){
            scrollPos = (int) (verticalScrollview.getScrollY() + 1.0);
            verticalScrollview.scrollTo(0, scrollPos);
            Log.e("moveScrollView", "moveScrollView");

            scrollPos = (int) (verticalScrollView2.getScrollY() + 1.0);
            verticalScrollView2.scrollTo(0, scrollPos);
            Log.e("moveScrollView", "moveScrollView");

    }


    public void stopAutoScrolling(){
        if (scrollTimer != null) {
            scrollTimer.cancel();
            scrollTimer	=	null;
        }
    }

    public Animation scaleFaceUpAnimation(){
        Animation scaleFace = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleFace.setDuration(500);
        scaleFace.setFillAfter(true);
        scaleFace.setInterpolator(new AccelerateInterpolator());
        Animation.AnimationListener	scaleFaceAnimationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                isFaceDown = false;

            }
            @Override
            public void onAnimationRepeat(Animation arg0) {}
            @Override
            public void onAnimationEnd(Animation arg0) {
                Intent in = new Intent(getBaseContext(), heroesWebview.class);
                in.putExtra("hero_name", hero_name);
                startActivity(in);
                if(faceTimer != null){
                    faceTimer.cancel();
                    faceTimer = null;
                }

                faceTimer = new Timer();
                if(faceAnimationSchedule != null){
                    faceAnimationSchedule.cancel();
                    faceAnimationSchedule = null;
                }
                faceAnimationSchedule = new TimerTask() {
                    @Override
                    public void run() {
                        faceScaleHandler.sendEmptyMessage(0);
                    }
                };

                faceTimer.schedule(faceAnimationSchedule, 750);
            }
        };
        scaleFace.setAnimationListener(scaleFaceAnimationListener);
        return scaleFace;
    }

    private Handler faceScaleHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(clickedButton.isSelected() == true)
                clickedButton.startAnimation(scaleFaceDownAnimation(500));
        }
    };

    public Animation scaleFaceDownAnimation(int duration){
        Animation scaleFace = new ScaleAnimation(1.2f, 1.0f, 1.2f, 1.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleFace.setDuration(duration);
        scaleFace.setFillAfter(true);
        scaleFace.setInterpolator(new AccelerateInterpolator());
        Animation.AnimationListener	scaleFaceAnimationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {}
            @Override
            public void onAnimationRepeat(Animation arg0) {}
            @Override
            public void onAnimationEnd(Animation arg0)
            {
                isFaceDown = true;
            }
        };
        scaleFace.setAnimationListener(scaleFaceAnimationListener);
        return scaleFace;
    }

    public void onBackPressed(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        stopAutoScrolling();


    }

    public void onPause() {
        super.onPause();
    }

    public void onDestroy(){
        clearTimerTaks(clickSchedule);
        clearTimerTaks(scrollerSchedule);
        clearTimerTaks(faceAnimationSchedule);
        clearTimers(scrollTimer);
        clearTimers(clickTimer);
        clearTimers(faceTimer);

        clickSchedule         = null;
        scrollerSchedule      = null;
        faceAnimationSchedule = null;
        scrollTimer           = null;
        clickTimer            = null;
        faceTimer             = null;

        super.onDestroy();
    }

    private void clearTimers(Timer timer){
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void clearTimerTaks(TimerTask timerTask){
        if(timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    public void portrait2() {

        lives_text.setVisibility(View.VISIBLE);
        lives_count.setVisibility(View.VISIBLE);

        ct2 = new CountDownTimer(50000, 1000) {

            public void onTick(long millisUntilFinished) {
                countd.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                countd.setText("");
                lives_counter--;
                portrait2();
            }
        }.start();

        hero_p.setImageResource(SKILLS2_IDS[currentimage2index2 % SKILLS2_IDS.length]);

        test = 1;
        Random r = new Random();
        int random = r.nextInt(3);
        Random r2 = new Random();

        if(lives_counter==3)
        {
            lives_count.setImageResource(R.drawable.lives3);
        }
        else if(lives_counter==2)
        {
            lives_count.setImageResource(R.drawable.lives2);
        }
        else if(lives_counter==1)
        {
            lives_count.setImageResource(R.drawable.lives1);
        }

        else
        {
            lives_count.setVisibility(View.GONE);
            lives_text.setVisibility(View.GONE);

        }


        views[random].setVisibility(View.VISIBLE);
        views[random].setText(SKILLS2_NAME[currentimage2index2 % SKILLS2_NAME.length]);

        views[random].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getBaseContext(), "Correct!", Toast.LENGTH_SHORT).show();
                ct2.cancel();
                portrait2();
            }
        });


        for(TextView vz : views)
        {
            if(vz != views[random])
            {


                int random2 = r2.nextInt(SKILLS2_NAME.length);
                if(SKILLS2_NAME[random2] != SKILLS2_NAME[currentimage2index2 % SKILLS2_NAME.length])
                {
                    vz.setVisibility(View.VISIBLE);
                    vz.setText(SKILLS2_NAME[random2]);

                }
            }
        }

        for(int itest=0; itest<=2; itest++)
        {
            if(views[itest] != views[random])
            {
                views[itest].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), "Try Again!", Toast.LENGTH_SHORT).show();
                        lives_counter--;
                        if (lives_counter == 3) {
                            lives_count.setImageResource(R.drawable.lives3);
                        } else if (lives_counter == 2) {
                            lives_count.setImageResource(R.drawable.lives2);
                        } else if (lives_counter == 1) {
                            lives_count.setImageResource(R.drawable.lives1);
                        }
                        else
                        {
                            lives_count.setVisibility(View.GONE);
                            lives_text.setVisibility(View.GONE);
                            ct2.cancel();
                            currentimage2index2=0;
                            levelReset();
                        }
                    }
                });
            }
        }



        currentimage2index2++;


    }


}




