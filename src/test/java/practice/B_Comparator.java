package practice;



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import beans.Person;
import beans.PersonForComparator;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntBinaryOperator;

import mockdata.MockData;
import org.junit.jupiter.api.Test;

/**
 * Exercises to create comparators using lambda expressions
 * and using the Comparator combinators. Some of the exercises
 * use a Person class, which is a simple POJO containing a last
 * name, first name, and age, with the obvious constructors and
 * getters.
 */
public class B_Comparator {

    final PersonForComparator michael = new PersonForComparator("Michael", "Jackson", 51);
    final PersonForComparator rod = new PersonForComparator("Rod", "Stewart", 71);
    final PersonForComparator paul = new PersonForComparator("Paul", "McCartney", 74);
    final PersonForComparator mick = new PersonForComparator("Mick", "Jagger", 73);
    final PersonForComparator jermaine = new PersonForComparator("Jermaine", "Jackson", 61);


    /**
     * Write a Comparator that compare instances of String using their length.
     * For instance FOUR (4 letters) is greater than TWO (three letters)
     */
    @Test
    public void comparator01() {
        //TODO//Comparator<String> compareByLength = null;
        //BEGINREMOVE
        Comparator<String> compareByLength = Comparator.comparing(String::length);
        //ENDREMOVE

        assertTrue(compareByLength.compare("FOUR", "TWO") > 0);
        assertTrue(compareByLength.compare("ONE", "SEVEN") < 0);
        assertTrue(compareByLength.compare("ONE", "TWO") == 0);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Check the static factory methods of the Comparator interface. Remember
    // how you implemented functions in the previous exercises. Write it using
    // a method reference.
    // </editor-fold>

    /**
     * Write a Comparator that compare instances of String using their length.
     * If the lengths are the same, then use the alphabetical order.
     */
    @Test
    public void comparator02() {
        //TODO//Comparator<String> compareByLengthThenAlphabetical = null;
        //BEGINREMOVE
        Comparator<String> compareByLengthThenAlphabetical =
                Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder());
        //ENDREMOVE

        assertTrue(compareByLengthThenAlphabetical.compare("FOUR", "TWO") > 0);
        assertTrue(compareByLengthThenAlphabetical.compare("ONE", "SEVEN") < 0);
        assertTrue(compareByLengthThenAlphabetical.compare("ONE", "TWO") < 0);
        assertTrue(compareByLengthThenAlphabetical.compare("FOUR", "FIVE") > 0);
        assertTrue(compareByLengthThenAlphabetical.compare("EIGHT", "EIGHT") == 0);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Use the previous comparator and check the default methods of the
    // Comparator interface.
    // Check also the factory methods of the Comparator interface, and remember
    // that String is comparable.
    // </editor-fold>

    /**
     * Write a Comparator that compares instances of Person using their lastName.
     */
    @Test
    public void comparator03() throws IOException {
        //TODO//Comparator<Person> comparebyLastName = null;
        //BEGINREMOVE
        Comparator<PersonForComparator> comparebyLastName = Comparator.comparing(PersonForComparator::lastName);
        //ENDREMOVE

        assertTrue(comparebyLastName.compare(michael, rod) < 0);
        assertTrue(comparebyLastName.compare(paul, paul) == 0);
        assertTrue(comparebyLastName.compare(michael, jermaine) == 0);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Check the static factory methods of the Comparator interface. Remember
    // how you implemented functions in the previous exercises. Write it using
    // a method reference.
    // </editor-fold>

    /**
     * Write a Comparator that compares instances of Person using their
     * lastName, and if their last name is the same, uses their first name.
     */
    @Test
    public void comparator04() {
        //TODO//Comparator<Person> comparebyLastNameThenFirstName = null;
        //BEGINREMOVE
        Comparator<PersonForComparator> comparebyLastNameThenFirstName =
                Comparator.comparing(PersonForComparator::lastName)
                        .thenComparing(PersonForComparator::name);
        //ENDREMOVE

        assertTrue(comparebyLastNameThenFirstName.compare(michael, rod) < 0);
        assertTrue(comparebyLastNameThenFirstName.compare(paul, paul) == 0);
        assertTrue(comparebyLastNameThenFirstName.compare(michael, jermaine) > 0);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Use the previous comparator and check the default methods of the Comparator interface.
    // </editor-fold>

    /**
     * Write a Comparator that compares the people in the order reversed from
     * the one you wrote in the comparator04() exercise. That is, the person
     * with the greater last name should be ordered first. If two persons have
     * the same last name, the one with the greater first name should be
     * ordered first.
     */
    @Test
    public void comparator05() {
        //TODO//Comparator<Person> comparebyLastNameThenFirstNameReversed = null;
        //BEGINREMOVE
        Comparator<PersonForComparator> comparebyLastNameThenFirstNameReversed =
                Comparator.comparing(PersonForComparator::lastName)
                        .thenComparing(PersonForComparator::name)
                        .reversed();
        //ENDREMOVE

        assertFalse(comparebyLastNameThenFirstNameReversed.compare(michael, rod) < 0);
        assertTrue(comparebyLastNameThenFirstNameReversed.compare(paul, paul) == 0);
        assertFalse(comparebyLastNameThenFirstNameReversed.compare(michael, jermaine) > 0);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Use the previous comparator and check the default methods of the Comparator interface.
    // </editor-fold>

    /**
     * Write a Comparator that compares the people in the same order than the
     * one you wrote in comparator04(), but that supports null values. The null
     * values should be considered greater than any non-null values.
     */
    @Test
    public void comparator06() {
        //TODO//Comparator<Person> comparebyLastNameThenFirstNameWithNull = null;
        //BEGINREMOVE
        Comparator<PersonForComparator> comparebyLastNameThenFirstNameWithNull =
                Comparator.nullsLast(
                        Comparator.comparing(PersonForComparator::lastName)
                                .thenComparing(PersonForComparator::name));
        //ENDREMOVE

        assertTrue(comparebyLastNameThenFirstNameWithNull.compare(michael, rod) < 0);
        assertTrue(comparebyLastNameThenFirstNameWithNull.compare(paul, paul) == 0);
        assertTrue(comparebyLastNameThenFirstNameWithNull.compare(michael, jermaine) > 0);
        assertTrue(comparebyLastNameThenFirstNameWithNull.compare(mick, null) < 0);
        assertTrue(comparebyLastNameThenFirstNameWithNull.compare(null, mick) > 0);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Use the previous comparator and check the static methods of the Comparator interface.
    // </editor-fold>

    /**
     * Write a Comparator that compares two people by age.
     * Try to write the comparator so as to avoid boxing of primitives.
     */
    @Test
    public void comparator07() {
        //TODO//Comparator<Person> comparebyAge = null;
        //BEGINREMOVE
        Comparator<PersonForComparator> comparebyAge = Comparator.comparingInt(PersonForComparator::age);
        //ENDREMOVE

        assertTrue(comparebyAge.compare(michael, rod) < 0);
        assertTrue(comparebyAge.compare(paul, paul) == 0);
        assertTrue(comparebyAge.compare(mick, jermaine) > 0);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Look for static methods on the Comparator interface that
    // have primitive specializations.
    // </editor-fold>

    /**
     * Write a lambda expression that compares two int values and returns an
     * int result that is less than, equal to, or greater than zero, like
     * a comparator. Watch out for overflow. The Comparator interface takes
     * two objects, but in this case we are comparing int primitives, so the
     * functional interface we use is IntBinaryOperator.
     */
    @Test
    public void comparator08() {
        //TODO//IntBinaryOperator intCompare = null;
        //BEGINREMOVE
        IntBinaryOperator intCompare = (a, b) -> (a < b) ? -1 : (a > b) ? 1 : 0;
        // Avoid the following as it is susceptible to overflow:
        // IntBinaryOperator intCompare = (a, b) -> a - b;
        //ENDREMOVE

        assertTrue(intCompare.applyAsInt(0, 1) < 0);
        assertTrue(intCompare.applyAsInt(1, 1) == 0);
        assertTrue(intCompare.applyAsInt(2, 1) > 0);
        assertTrue(intCompare.applyAsInt(Integer.MIN_VALUE, Integer.MAX_VALUE) < 0);
        assertTrue(intCompare.applyAsInt(Integer.MAX_VALUE, Integer.MIN_VALUE) > 0);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Use a ternary operator (cond ? result1 : result2) instead of subtraction.
    // </editor-fold>

    /**
     * Write a method reference that compares two int values and returns an
     * int result that is less than, equal to, or greater than zero, like
     * a comparator.
     */
    @Test
    public void comparator09() {
        //TODO//IntBinaryOperator intCompare = null;
        //BEGINREMOVE
        IntBinaryOperator intCompare = Integer::compare;
        //ENDREMOVE

        assertTrue(intCompare.applyAsInt(0, 1) < 0);
        assertTrue(intCompare.applyAsInt(1, 1) == 0);
        assertTrue(intCompare.applyAsInt(2, 1) > 0);
        assertTrue(intCompare.applyAsInt(Integer.MIN_VALUE, Integer.MAX_VALUE) < 0);
        assertTrue(intCompare.applyAsInt(Integer.MAX_VALUE, Integer.MIN_VALUE) > 0);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Use a method reference to a static method on the Integer class.
    // </editor-fold>

    interface DoubleToIntBiFunction {
        int applyAsInt(double a, double b);
    }

    /**
     * Write a method reference that compares two double values and returns an
     * int result that is less than, equal to, or greater than zero, like
     * a comparator. There functional interface that takes two doubles and returns
     * an int, so we define one here. Comparing double values introduces
     * special cases such NaN. Consider all NaN values to be equal to each other
     * and greater than any non-NaN value.
     */
    @Test
    public void comparator10() {
        //TODO//DoubleToIntBiFunction doubleCompare = null;
        //BEGINREMOVE
        DoubleToIntBiFunction doubleCompare = Double::compare;
        //ENDREMOVE

        assertTrue(doubleCompare.applyAsInt(0.0, 1.0) < 0);
        assertTrue(doubleCompare.applyAsInt(1.0, 1.0) == 0);
        assertTrue(doubleCompare.applyAsInt(2.0, 1.0) > 0);
        assertTrue(doubleCompare.applyAsInt(Double.NaN, Double.NaN) == 0);
        assertTrue(doubleCompare.applyAsInt(Double.NaN, 0.0) > 0);
        assertTrue(doubleCompare.applyAsInt(0.0, Double.NaN) < 0);
    }
    // Hint:
    // <editor-fold defaultstate="collapsed">
    // Use a method reference to a static method on the Double class.
    // </editor-fold>
}