import org.junit.*;
import static org.junit.Assert.*;

public class TamagotchiTest {

  @Test
  public void CreatesTamagotchi_istrue() { //change this name
  Tamagotchi myTamagotchi = new Tamagotchi("lil dragon");
  assertEquals(true, myTamagotchi.isAlive());
    }
  }
