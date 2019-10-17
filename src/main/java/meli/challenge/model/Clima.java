package meli.challenge.model;

public enum Clima {

    LLUVIA,
    SEQUIA,
    OPTIMO,
    NORMAL;

    @Override
    public String toString() {
        switch (this) {
            case LLUVIA:
                return "lluvia";
            case OPTIMO:
                return "optimo";
            case SEQUIA:
                return "sequia";
            case NORMAL:
                return "normal";
        }
        return "normal";
    }
}
