import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RobotMotionTest {

    @Test
    void testInitialPosition() {
        RobotMotion robot = new RobotMotion(10);
        assertEquals(0, robot.getX());
        assertEquals(0, robot.getY());
        assertFalse(robot.isPenDown());
        assertEquals("NORTH", robot.getDirection());
    }

    @Test
    void testMoveForward() {
        RobotMotion robot = new RobotMotion(10);
        robot.move(3);
        assertEquals(3, robot.getY());
    }

    @Test
    void testTurnRight() {
        RobotMotion robot = new RobotMotion(10);
        robot.turnRight();
        assertEquals("EAST", robot.getDirection());
    }

    @Test
    void testTurnLeft() {
        RobotMotion robot = new RobotMotion(10);
        robot.turnLeft();
        assertEquals("WEST", robot.getDirection());
    }

    @Test
    void testPenDown() {
        RobotMotion robot = new RobotMotion(10);
        robot.penDown();
        assertTrue(robot.isPenDown());
    }

    @Test
    void testPenUp() {
        RobotMotion robot = new RobotMotion(10);
        robot.penDown();
        robot.penUp();
        assertFalse(robot.isPenDown());
    }


    @Test
    void testInvalidMoveDoesNotCrash() {
        RobotMotion robot = new RobotMotion(10);
        robot.move(3);
        assertEquals(3, robot.getY());
        assertEquals("NORTH", robot.getDirection());
    }


    @Test
    void testMoveOutsideGridNorth() {
        RobotMotion robot = new RobotMotion(5);
        robot.penDown();
        robot.move(10);
        assertEquals(4, robot.getY());
    }


    @Test
    void testMoveNegativeSteps() {
        RobotMotion robot = new RobotMotion(10);
        robot.penDown();
        robot.move(-3);
        assertEquals(0, robot.getX());
        assertEquals(0, robot.getY());
    }


    @Test
    void testPrintFloorOutput() {
        RobotMotion robot = new RobotMotion(5);
        robot.penDown();
        robot.move(2);
        String output = robot.getFloorAsString();
        assertTrue(output.contains("*"));
    }
}

