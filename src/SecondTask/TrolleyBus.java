package SecondTask;

class TrolleyBus {
    private TrolleyBusState tbState;
    private Gas gas;
    private Brake brake;
    private Door door;
    private Light light;

    TrolleyBus() {
        tbState = TrolleyBusState.STANDING;
        gas = new Gas(ButtonState.INITIAL);
        brake = new Brake(ButtonState.INITIAL);
        door = new Door(DoorState.CLOSED);
        light = new Light(LightState.OFF);
    }

    TrolleyBusState getState() {
        return tbState;
    }

    Gas getGas() {
        return gas;
    }

    Brake getBrake() {
        return brake;
    }

    Door getDoor() {
        return door;
    }

    Light getLight() {
        return light;
    }

    class Gas implements Replacement {
        private ButtonState state;

        Gas(ButtonState state) {
            this.state = state;
        }

        ButtonState getState() {
            return state;
        }

        @Override
        public  void replaceState() {
            if (state == ButtonState.INITIAL && door.state == DoorState.CLOSED) {
                state = ButtonState.PRESSED;
                tbState = TrolleyBusState.MOVING;
            } else {
                state = ButtonState.INITIAL;
            }
        }
    }

    class Brake implements Replacement {
        private ButtonState state;

        Brake(ButtonState state) {
            this.state = state;
        }

        ButtonState getState() {
            return state;
        }

        @Override
        public  void replaceState() {
            if (state == ButtonState.INITIAL) {
                state = ButtonState.PRESSED;
                tbState = TrolleyBusState.STANDING;
            } else {
                state = ButtonState.INITIAL;
            }
        }
    }

    class Door implements Replacement {
        private DoorState state;

        Door(DoorState state) {
            this.state = state;
        }

        DoorState getState() {
            return state;
        }

        @Override
        public  void replaceState() {
            if (state == DoorState.CLOSED
                    && tbState == TrolleyBusState.STANDING
                    && light.state == LightState.ON) {
                state = DoorState.OPENED;
            } else if (light.state == LightState.OFF) {
                state = DoorState.CLOSED;
            }
        }
    }

    class Light implements  Replacement {
        private LightState state;

        Light(LightState state) {
            this.state = state;
        }

        LightState getState() {
            return state;
        }

        @Override
        public  void replaceState() {
            if (state == LightState.OFF) {
                state = LightState.ON;
            } else {
                state = LightState.OFF;
            }
        }
    }
}