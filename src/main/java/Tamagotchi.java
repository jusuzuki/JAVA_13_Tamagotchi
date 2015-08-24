import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Tamagotchi {
  private String mName;
  public static Integer mFoodLevel; //this should ideally not be public
  public final Integer MAX_FOOD = 10;
  private Integer mSleepLevel;
  public final Integer MAX_SLEEP = 10;
  private Integer mActivityLevel;
  public final Integer MAX_ACTIVITY = 10;
  private Integer mTime = 60;


  public Tamagotchi(String name){
    mName = name;
    mFoodLevel = MAX_FOOD;
    mSleepLevel = MAX_SLEEP;
    mActivityLevel = MAX_ACTIVITY;
  }

  // start playing
  public static String startPlaying(){

  Date startDate = new Date();
  String startDateStr = startDate.toString();
  System.out.println(startDateStr);
  return startDateStr;
  }

  public static Integer getFoodLevel(){
    return mFoodLevel;
  }

}
