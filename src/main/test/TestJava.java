import com.springbootdemo.demo.domain.City;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author sjk
 * @date 2020/9/18 16:13
 */
public class TestJava {
    public  static  void aa(){}

    @Test
    public void test1(){
        ArrayList<City> citys = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            City city = new City();
            city.setId((long)i);
            city.setId((long)i);
            city.setCityName(String.valueOf(i));
            citys.add(city);
        };
        List<City> collect = citys.stream().filter(city -> {
            return city.getId() > 5;
        }).collect(Collectors.toList());

        System.out.println(collect.toString());
    }

}
