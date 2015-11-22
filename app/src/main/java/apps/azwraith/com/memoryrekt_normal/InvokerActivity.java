package apps.azwraith.com.memoryrekt_normal;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class InvokerActivity extends AppCompatActivity {

    private ImageView quas;
    private ImageView wex;
    private ImageView exort;
    private ImageView invoke_button;
    private ImageView skills_img;
    private ImageView hourGlass;
    private ImageView banner;
    private ImageView play_Button;
    private ImageView easy_Button;
    private ImageView medium_Button;
    private ImageView hard_Button;
    private TextView banner_text;
    private TextView combo_view;
    private TextView skill_name;
    private ImageView close_button;
    private LinearLayout shadey;
    private CountDownTimer cdt;
    public int clockvar = 0;
    public int imageIndex = 0;
    public int score = 0;
    public int level_user = 0;
    public int time_level = 0;
    public int time_level_gap = 0;
    String storeS = "";


    int[] invoSkills_IDS = {R.drawable.snap, R.drawable.ghost, R.drawable.ice_wall, R.drawable.emp, R.drawable.tornado,
            R.drawable.alacrity, R.drawable.sun, R.drawable.forge, R.drawable.chaos_meter, R.drawable.deafening};
    String[] invoSkillS_NAMES = {"Cold Snap", "Ghost Walk", "Ice Wall", "EMP", "Tornado", "Alacrity", "Sunstrike", "Forge Spirits", "Chaos Meteor", "Deafening Blast"};

    String[] invoSkills_COMBO = {"QQQ", "QQW", "QQE", "WWW", "WWQ", "WWE", "EEE", "EEQ", "EEW", "QWE"};

    String storQ = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoker);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        quas = (ImageView) findViewById(R.id.quasButton);
        wex = (ImageView) findViewById(R.id.wexButton);
        exort = (ImageView) findViewById(R.id.exortButton);
        invoke_button = (ImageView) findViewById(R.id.invokeButton);
        skills_img = (ImageView) findViewById(R.id.invo_skills_image);
        hourGlass = (ImageView) findViewById(R.id.hour_glass);
        banner = (ImageView) findViewById(R.id.banner2);
        play_Button = (ImageView) findViewById(R.id.play_b);
        easy_Button = (ImageView) findViewById(R.id.easy_b);
        medium_Button = (ImageView) findViewById(R.id.medium_b);
        hard_Button = (ImageView) findViewById(R.id.hard_b);
        banner_text = (TextView) findViewById(R.id.score_display);
        close_button = (ImageView) findViewById(R.id.close_b);
        shadey = (LinearLayout) findViewById(R.id.shade);
        combo_view = (TextView) findViewById(R.id.COMBO_VIEW);
        skill_name= (TextView) findViewById(R.id.skillsName);

        easy_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level_user = 1;
                easy_Button.setBackgroundResource(R.drawable.touch_easy_button);
            }
        });

        medium_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level_user = 2;
                medium_Button.setBackgroundResource(R.drawable.touch_medium_button);
            }
        });

        hard_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level_user = 3;
                hard_Button.setBackgroundResource(R.drawable.touch_hard_button);
            }
        });

        play_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeGamemode();
                play_Button.setBackgroundResource(R.drawable.touch_play_button);
            }
        });

    }

    public void executeGamemode() {

        shadey.setVisibility(View.GONE);
        play_Button.setVisibility(View.GONE);
        easy_Button.setVisibility(View.GONE);
        medium_Button.setVisibility(View.GONE);
        hard_Button.setVisibility(View.GONE);
        storQ = "";
        storeS = "";
        switch (level_user) {
            case 1:
                time_level = 8000;
                time_level_gap = 1333;
                break;

            case 2:
                time_level = 6000;
                time_level_gap = 1000;
                break;

            case 3:
                time_level = 2000;
                time_level_gap = 330;
                break;

            default:
                time_level = 6000;
                time_level_gap = 1000;
        }


        combo_view.setText(storQ);
        quas.setBackgroundResource(R.drawable.quas_button_edit);
        wex.setBackgroundResource(R.drawable.wex_button_edit);
        exort.setBackgroundResource(R.drawable.exort_button_edit);
        invoke_button.setBackgroundResource(R.drawable.invoke_button_edit);


        clockvar = 0;

        cdt = new CountDownTimer(time_level, time_level_gap) {
            @Override
            public void onTick(long millisUntilFinished) {
                switch (clockvar) {
                    case 0:
                        hourGlass.setImageResource(R.drawable.icon_hourglass2);
                        break;
                    case 1:
                        hourGlass.setImageResource(R.drawable.icon_hourglass3);
                        break;
                    case 2:
                        hourGlass.setImageResource(R.drawable.icon_hourglass4);
                        break;
                    case 3:
                        hourGlass.setImageResource(R.drawable.icon_hourglass5);
                        break;
                    case 4:
                        hourGlass.setImageResource(R.drawable.icon_hourglass6);
                        break;
                    default:
                        hourGlass.setImageResource(R.drawable.icon_hourglass2);

                }
                clockvar++;
            }

            @Override
            public void onFinish() {
                hourGlass.setImageResource(R.drawable.icon_hourglass2);
                imageIndex++;
                executeGamemode();
            }
        }.start();

        skills_img.setImageResource(invoSkills_IDS[imageIndex % invoSkills_IDS.length]);

        String chrIndextot = "";

            String strIndex = invoSkillS_NAMES[imageIndex % invoSkillS_NAMES.length];
            for (int j = 0; j < strIndex.length(); j++) {

                char chrIndex = strIndex.charAt(j);
                    chrIndextot = chrIndextot + chrIndex;
            }

            skill_name.setText(chrIndextot);


            storQ = "";

            quas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String strl = "";
                    quas.setBackgroundResource(R.drawable.quas_button_edit_clicked);
                    storQ = storQ + "Q";
                    for (int starg = 0; starg < storQ.length(); starg++) {
                        char stars = storQ.charAt(starg);
                        strl = strl + stars + "\n";
                        combo_view.setText(strl);
                    }
                }


            });

            wex.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String strl = "";
                    wex.setBackgroundResource(R.drawable.wex_button_edit_clicked);
                    storQ = storQ + "W";
                    for (int starg = 0; starg < storQ.length(); starg++) {
                        char stars = storQ.charAt(starg);
                        strl = strl + stars + "\n";
                        combo_view.setText(strl);
                    }
                }
            });


            exort.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String strl = "";
                    exort.setBackgroundResource(R.drawable.exort_button_edit_clicked);
                    storQ = storQ + "E";
                    for (int starg = 0; starg < storQ.length(); starg++) {
                        char stars = storQ.charAt(starg);
                        strl = strl + stars + "\n";
                        combo_view.setText(strl);
                    }
                }
            });


            invoke_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    invoke_button.setBackgroundResource(R.drawable.invoke_button_edit_clicked);


                    if (imageIndex < 9) {

                        if (storQ.equals(invoSkills_COMBO[imageIndex])) {
                            Toast.makeText(getBaseContext(), "Correct!", Toast.LENGTH_SHORT).show();
                            score = score + 1;
                            imageIndex++;
                            cdt.cancel();
                            executeGamemode();

                        } else {
                            Toast.makeText(getBaseContext(), "Incorrect!", Toast.LENGTH_SHORT).show();
                            imageIndex++;
                            cdt.cancel();
                            executeGamemode();
                        }
                    } else {
                        if (imageIndex > 10) {
                            imageIndex = 10;
                        }
                        shadey.setVisibility(View.VISIBLE);
                        banner.setVisibility(View.VISIBLE);
                        banner_text.setVisibility(View.VISIBLE);
                        close_button.setVisibility(View.VISIBLE);
                        switch (score) {
                            case 0:
                                banner_text.setText("Try Again!");
                                break;

                            case 1:
                                banner_text.setText("Beginner");
                                break;

                            case 2:
                                banner_text.setText("Beginner");
                                break;

                            case 3:
                                banner_text.setText("Intermediate");
                                break;

                            case 4:
                                banner_text.setText("Intermediate");
                                break;

                            case 5:
                                banner_text.setText("Casual");
                                break;

                            case 6:
                                banner_text.setText("Casual");
                                break;

                            case 7:
                                banner_text.setText("Enthusiast");
                                break;

                            case 8:
                                banner_text.setText("Enthusiast");
                                break;

                            case 9:
                                banner_text.setText("Smart Fingers!");
                                break;

                            case 10:
                                banner_text.setText("GODLIKE Fingers!");
                                break;

                            default:
                                banner_text.setText("");


                        }
                    }


                }
            });

            close_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shadey.setVisibility(View.GONE);
                    banner.setVisibility(View.GONE);
                    banner_text.setVisibility(View.GONE);
                    close_button.setVisibility(View.GONE);
                    imageIndex = 0;
                    score = 0;
                    cdt.cancel();
                    executeGamemode();
                }
            });


        }
    }

