package entities;

public enum ActivityType {
    GENERIC,
    THEATER,
    CINEMA;

//----------------------------------------------------------------------------------------------------------------------

    public int getDiscountPercentage(User user) {
        int age = user.getAge();

        return switch (this) {
            case THEATER -> (age <= 25) ? 50 : ((age >= 65) ? 70 : 0);
            case CINEMA -> (age <= 21) ? 50 : 0;
            default -> 0;
        };
    }

//----------------------------------------------------------------------------------------------------------------------

    public static ActivityType getType(String string) {
        return switch (string) {
            case "theatre" -> THEATER;
            case "cinema" -> CINEMA;
            default -> GENERIC;
        };
    }

//----------------------------------------------------------------------------------------------------------------------
}
