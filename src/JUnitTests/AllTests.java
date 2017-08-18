package JUnitTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import toy.WoolBall;


@RunWith(Suite.class)
@SuiteClasses({BandageTest.class, BoneTest.class, EpiPenTest.class, FishTest.class, FlyTest.class,
				FruitTest.class, HamBallTest.class, PetMedicineTest.class, PlayerTest.class, PoolTest.class,
				RatTest.class, SeedsTest.class, SteakTest.class, TubeTest.class, WoolBallTest.class,
		TestBird.class, TestCat.class, TestDog.class, TestFrog.class, TestHamster.class, TestSnake.class,
		})
public class AllTests {

}
