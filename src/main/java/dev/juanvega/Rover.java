package dev.juanvega;

public class Rover {

    public Orientation orientation;
    public Point point;

    public Rover(Point point, Orientation orientation) {
        this.orientation = orientation;
        this.point = point;
    }

    public void move(String commands) {
        var split = commands.split("");
        for (String s: split) {
            Command command = Command.from(s);

            switch (command) {
                case LEFT -> orientation = switch (orientation) {
                    case N -> Orientation.W;
                    case S -> Orientation.E;
                    case E -> Orientation.N;
                    case W -> Orientation.S;
                };
                case RIGHT -> orientation = switch (orientation) {
                    case N -> Orientation.E;
                    case S -> Orientation.W;
                    case E -> Orientation.S;
                    case W -> Orientation.N;
                };
                case FORWARD -> point = switch (orientation) {
                    case N -> new Point(point.x(), point.y() + 1);
                    case S -> new Point(point.x(), point.y() - 1);
                    case E -> new Point(point.x() + 1, point.y());
                    case W -> new Point(point.x() - 1, point.y());
                };
                case BACKWARD -> point = switch (orientation) {
                    case N -> new Point(point.x(), point.y() - 1);
                    case S -> new Point(point.x(), point.y() + 1);
                    case E -> new Point(point.x() - 1, point.y());
                    case W -> new Point(point.x() + 1, point.y());
                };
            }
        }
    }

    public enum Orientation {
        N, S, E, W
    }

    public enum Command {
        LEFT, RIGHT, FORWARD, BACKWARD;

        static Command from(String s) {
            return switch (s) {
                case "L" -> LEFT;
                case "R" -> RIGHT;
                case "F" -> FORWARD;
                case "B" -> BACKWARD;
                default -> null;
            };
        }
    }

    public record Point(int x, int y) { }
}
