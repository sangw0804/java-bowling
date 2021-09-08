package bowling.model;

public class Lane {
    public static final int INITIAL_PIN_COUNT = 10;
    public static final int NO_PINS_LEFT = 0;
    public static final int INITIAL_PITCH_COUNT = 2;

    private final PitchStrategy strategy;
    private int leftPinCount = INITIAL_PIN_COUNT;
    private int pitchCount = INITIAL_PITCH_COUNT;

    public Lane() {
        this.strategy = new RandomPitchStrategy();
    }

    public Lane(PitchStrategy strategy) {
        this.strategy = strategy;
    }

    public ShotResult pitch() {
        int pinDownCount = strategy.nextPinDownCount(leftPinCount);
        leftPinCount -= pinDownCount;
        pitchCount--;

        if (leftPinCount == NO_PINS_LEFT || pitchCount == 0) {
            resetPins();
        }

        return ShotResult.from(pinDownCount);
    }

    private void resetPins() {
        leftPinCount = INITIAL_PIN_COUNT;
        pitchCount = INITIAL_PITCH_COUNT;
    }
}
