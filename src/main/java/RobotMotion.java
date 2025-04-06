import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class RobotMotion {
    private int x, y;
    private boolean penDown;
    private String direction;
    private int[][] floor;

    public RobotMotion(int size) {
        this.floor = new int[size][size];
        this.x = 0;
        this.y = 0;
        this.penDown = false;
        this.direction = "NORTH";
    }

    public void penUp() {
        penDown = false;
    }

    public void penDown() {
        penDown = true;
    }

    public void turnRight() {
        switch (direction) {
            case "NORTH": direction = "EAST"; break;
            case "EAST": direction = "SOUTH"; break;
            case "SOUTH": direction = "WEST"; break;
            case "WEST": direction = "NORTH"; break;
        }
    }

    public void turnLeft() {
        switch (direction) {
            case "NORTH": direction = "WEST"; break;
            case "WEST": direction = "SOUTH"; break;
            case "SOUTH": direction = "EAST"; break;
            case "EAST": direction = "NORTH"; break;
        }
    }

    public void move(int steps) {
        if (steps < 0) return;

        for (int i = 0; i < steps; i++) {
            if (penDown) floor[y][x] = 1;
            switch (direction) {
                case "NORTH": if (y < floor.length - 1) y++; break;
                case "SOUTH": if (y > 0) y--; break;
                case "EAST": if (x < floor.length - 1) x++; break;
                case "WEST": if (x > 0) x--; break;
            }
        }
        if (penDown) floor[y][x] = 1;
    }

    public void printPosition() {
        System.out.println("Position: " + x + ", " + y + " - Pen: " + (penDown ? "down" : "up") + " - Facing: " + direction);
    }

    public void printFloor() {
        for (int i = floor.length - 1; i >= 0; i--) {
            System.out.print(i + " ");
            for (int j = 0; j < floor[i].length; j++) {
                System.out.print(floor[i][j] == 1 ? "* " : "  ");
            }
            System.out.println();
        }
        System.out.print("  ");
        for (int j = 0; j < floor.length; j++) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    // For testing
    public String getFloorAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = floor.length - 1; i >= 0; i--) {
            sb.append(i).append(" ");
            for (int j = 0; j < floor[i].length; j++) {
                sb.append(floor[i][j] == 1 ? "* " : "  ");
            }
            sb.append("\n");
        }
        sb.append("  ");
        for (int j = 0; j < floor.length; j++) {
            sb.append(j).append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isPenDown() {
        return penDown;
    }

    public String getDirection() {
        return direction;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RobotMotion robot = null;
        List<String> history = new ArrayList<>();

        while (true) {
            System.out.println("Enter command:");
            String command = scanner.nextLine().toUpperCase();


            if (!command.equals("H")) {
                history.add(command);
            }

            if (command.startsWith("I ")) {
                try {
                    int size = Integer.parseInt(command.split(" ")[1]);
                    robot = new RobotMotion(size);
                    System.out.println("Initialized " + size + "x" + size + " grid.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid grid size.");
                }
            } else if (robot == null) {
                System.out.println("Error: Initialize grid first using 'I size'.");
            } else {
                if (command.equals("Q")) {
                    break;
                } else if (command.equals("U")) {
                    robot.penUp();
                } else if (command.equals("D")) {
                    robot.penDown();
                } else if (command.equals("R")) {
                    robot.turnRight();
                } else if (command.equals("L")) {
                    robot.turnLeft();
                } else if (command.startsWith("M ")) {
                    try {
                        int steps = Integer.parseInt(command.split(" ")[1]);
                        robot.move(steps);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid move value.");
                    }
                } else if (command.equals("C")) {
                    robot.printPosition();
                } else if (command.equals("P")) {
                    robot.printFloor();
                } else if (command.equals("H")) {
                    System.out.println("Command History:");
                    for (String pastCommand : history) {
                        System.out.println("> " + pastCommand);
                    }
                } else {
                    System.out.println("Invalid command.");
                }
            }
        }

        scanner.close();
    }
}
