package entity;

public class Key {

    private final char value;
    private Boolean isTaken;

    public Key(char value, boolean isTaken) {
        this.value = value;
        this.isTaken = isTaken;
    }

    public char getValue() {
        return value;
    }

    public Boolean getIsTaken() {
        return isTaken;
    }

    public void setIsTaken(Boolean taken) {
        isTaken = taken;
    }

    @Override
    public String toString() {
        return "Key{" +
                "value=" + value +
                ", isTaken=" + isTaken +
                '}';
    }
}
