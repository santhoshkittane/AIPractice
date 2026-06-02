import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Premitive_Types {
	
	public static void main(String[] args) {
//		Stream<Integer> intStream = Stream.of(...values:1,2,3,4,5,6,7,8,9,10);
//		intStream.forEach(Integer ->{
//			System.out.println(Integer);
		IntStream intStream = IntStream.of(1,2,3,4,5,6,7,8,9,10);
		
		//System.out.println("Overall Items are"+intStream.count());
		//System.out.println("Average is "+intStream.average());
		//System.out.println("Overall Items are"+intStream.count());
		intStream.filter(i->i%2==0).forEach(System.out::println);
		
		
		};
		
		}


