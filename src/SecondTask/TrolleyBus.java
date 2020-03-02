package SecondTask;

public class TrolleyBus implements Action {
    TrolleyBusState state;
    Gas gas;
    Brake brake;
    Door door;
    Light light;
    TrolleyBus() {
        state = TrolleyBusState.STANDING;
        gas = new Gas(ButtonState.INITIAL);
        brake = new Brake(ButtonState.INITIAL);
        door = new Door(DoorState.CLOSED);
        light = new Light(LightState.OFF);
    }

    public void Act() {
        if (this.door.state == DoorState.CLOSED && this.gas.state == ButtonState.PRESSED) {
            this.state = TrolleyBusState.MOVING;
        }
        if (this.brake.state == ButtonState.PRESSED) {
            this.state = TrolleyBusState.STANDING;
        }
        if (this.state == TrolleyBusState.STANDING) {
            if (this.light.state == LightState.ON) {
                this.door.state = DoorState.OPENED;
            } else {
                this.door.state = DoorState.CLOSED;
            }
        }
    }

    private class Gas {
        private ButtonState state;
        Gas(ButtonState state){
            this.state = state;
        }
    }

    private class Brake {
        private ButtonState state;
        Brake(ButtonState state) {
            this.state = state;
        }
    }

    private class Door {
        private DoorState state;
        Door(DoorState state) {
            this.state = state;
        }
    }

    private class Light {
        private LightState state;
        Light(LightState state){
            this.state = state;
        }
    }
}