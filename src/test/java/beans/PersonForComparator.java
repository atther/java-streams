package beans;


/**
 * Exercises to create comparators using lambda expressions and using the Comparator combinators.
 * Some of the exercises use a Person class, which is a simple POJO containing a last name, first
 * name, and age, with the obvious constructors and getters.
 */
public record PersonForComparator(String name, String lastName, int age) {}