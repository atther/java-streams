package lectures;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.stream.Collectors;
import mockdata.MockData;
import org.junit.Test;

public class Lecture11 {

  @Test
  public void joiningStrings() throws Exception {
    List<String> names = ImmutableList.of("anna", "john", "marcos", "helena", "yasmin");

    String concated = "";
    for (String name : names) {
      concated  = concated + name + ", ";
    }

    System.out.println(concated.substring(0, concated.length() - 2));


  }

  @Test
  public void joiningStringsWithStream() throws Exception {
    List<String> names = ImmutableList.of("anna", "john", "marcos", "helena", "yasmin");

    String concat = names.stream().collect(Collectors.joining(","));
    System.out.println(concat);

  }
}
