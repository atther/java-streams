package lectures;


import static org.assertj.core.api.Assertions.assertThat;

import beans.Car;
import beans.Person;
import beans.PersonDTO;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import mockdata.MockData;
import org.junit.Test;

public class Lecture5 {

  @Test
  public void understandingFilter() throws Exception {
    ImmutableList<Car> cars = MockData.getCars();
    Predicate<Car> myCar =n -> n.getPrice()<20000;

    List<Car> goodcar  = cars.stream().filter(myCar).collect(Collectors.toList());
    System.out.println(goodcar);

  }

  @Test
  public void ourFirstMapping() throws Exception {
    // transform from one data type to another
    List<Person> people = MockData.getPeople();
    List<PersonDTO> dtos = new ArrayList<>();

    Function<Person, PersonDTO> getPersonPersonDTOFunction =
       person -> new PersonDTO(person.getId(), person.getFirstName(), person.getAge());

//    dtos = people.stream()
//            .map(getPersonPersonDTOFunction)
//            .collect(Collectors.toList());

    dtos = people.stream()
            .map(PersonDTO::map)
            .collect(Collectors.toList());

    assertThat(dtos.size()).isEqualTo(1000);

  }

  @Test
  public void averageCarPrice() throws Exception {
    // calculate average of car prices
    ImmutableList<Car> cars = MockData.getCars();;

    double avg   = cars.stream().mapToDouble(Car::getPrice).average().orElse(0);
    System.out.println(avg);

  }

  @Test
  public void test() throws Exception {
    MockData.getCars().forEach(System.out::println);

  }
}



