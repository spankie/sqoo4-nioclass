package decagon;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App {
	private static Integer counter = 0;

	private static void Lambda(SomeInterface s) {
		System.out.println(s.run("Hello", "World"));
	}

	private static void wasCalled() {
		counter++;
	}

	private static void creatingStreams() {
		// used where you dont want null for a stream value
		Stream<String> streamEmpty = Stream.empty();

		// stream of collection
		Collection<String> collection = Arrays.asList("a", "b", "c");
		Stream<String> streamOfCollection = collection.stream();

		// stream of array
		Stream<String> streamOfArray = Stream.of("a", "b", "c");
		String[] arr = new String[] { "a", "b", "c" };
		Stream<String> streamOfArrayFull = Arrays.stream(arr);
		Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3); // sub stream

		// use builder pattern to create a stream
		Stream<String> streamBuilder = Stream.<String>builder().add("a").add("b").add("c").build();

		// use generate method
		Stream<String> streamGenerated = Stream.generate(() -> "element").limit(10);
		// ["element", "element"]
		// using iterate method
		Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);
		// 40
		// 42
		// 44
		// 46

	}

	public static void otherTypesOfStreams() {
		IntStream intStream = IntStream.range(1, 3); // 1, 2
		LongStream longStream = LongStream.rangeClosed(1, 3); // 1, 2, 3

		Random random = new Random();
		DoubleStream doubleStream = random.doubles(3);

	}

	public static void fileStream() throws Exception {
		Path path = Paths.get("./file.txt");
		// List<String> ll = Files.readAllLines(path);
		// ll.forEach(l -> {
		// System.out.println(l);
		// });
		Stream<String> streamOfStrings = Stream.empty();
		try {
			streamOfStrings = Files.lines(path);
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
		}
		Stream<String> streamWithCharset = Files.lines(path, Charset.forName("UTF-8"));
		streamOfStrings.map(s -> {
			System.out.println(s);
			return s;
		}).count();
	}

	public static void reusingStreams() {
		// Stream<String> stream = Stream.of("abc", "bcd", "cde").filter(element ->
		// element.contains("b"));
		// Optional<String> anyElement = stream.findAny();
		// Optional<String> firstElement = stream.findFirst();

		// properly reusing streams
		Stream<String> ss = Stream.of("a", "b", "c").filter(element -> element.contains("b"));
		List<String> elements = ss.collect(Collectors.toList());
		Optional<String> anyElement = elements.stream().findAny();
		Optional<String> firstElement = elements.stream().findFirst();

		if (anyElement.isPresent()) {
			System.out.println(anyElement.get());
		} else {
			System.out.println("no value containing d");
		}

		if (firstElement.isPresent()) {
			System.out.println(firstElement.get());
		} else {
			System.out.println("no first element");
		}
	}

	/*
	 * best practice for using streams is chaining. the source, intermediate
	 * operation(s) and a terminal operation. types of intermediate operations:
	 * sorted, skip, filter, map etc.
	 */

	public static void streamPipeline() {
		List<String> list = Arrays.asList("abc1", "abc2", "abc3");
		// Stream<String> stream = list.stream().filter(element -> {
		Long streamCount = list.stream().skip(1).filter(element -> {
			counter++;
			return element.contains("2");
		}).count();
		System.out.println(counter); // 2
		System.out.println(streamCount); // 1
		counter = 0;
		Optional<String> firstValue = list.stream().skip(1).filter(element -> {
			counter++;
			System.out.println(element);
			return element.contains("2");
		}).findFirst();
		System.out.println(counter); // 1
		System.out.println(firstValue); // 1
	}

	public static void lazyInvocation() {
		List<String> list = Arrays.asList("abc1", null, "abc3");
		Optional<String> stream2 = list.stream().filter(element -> {
			System.out.println("filter() was called: " + element);
			if (element == null) {
				element = "abc2";
			}
			return element.contains("2");
		}).map(element -> {
			System.out.println("map() was called");
			// if (element == null) {
			// element = "abc1";
			// }
			return element.toUpperCase();
		}).findFirst();
		System.out.println(stream2.get());
	}

	public static void orderOfExecution() {
		List<String> list = Arrays.asList("abc1", "abc2", "abc3");
		long size = list.stream().skip(2).map(element -> {
			wasCalled();
			return element.substring(0, 3);
		}).count();
		System.out.printf("size of count map().skip() : %d\ncounter: %d\n", size, counter);
	}

	public static void usingReduce() {
		OptionalInt reduced = IntStream.range(1, 4).reduce((a, b) -> {
			return a + b;
		});
		int reducedTwoParams = IntStream.range(1, 4).reduce(10, (a, b) -> a + b);

		int reducedParams = Stream.of(1, 2, 3).reduce(10, (a, b) -> {
			return a + b;
		}, (a, b) -> {
			System.out.println("combiner was called");
			return a + b;
		});
		System.out.printf("reduced: %d\n", reduced.getAsInt());
		System.out.printf("reducedTwoParams: %d\n", reducedTwoParams);
		System.out.printf("reducedParams: %d\n", reducedParams);
	}

	public static void usingCollect() {

		List<Product> productList = Arrays.asList(null, new Product(14, "orange"),
				new Product(13, "lemon"), new Product(23, "bread"), new Product(13, "sugar"));

		List<String> collectorCollection = productList.stream().map(Product::getName).collect(Collectors.toList());
		System.out.printf("collectorCollection: ");
		System.out.println(collectorCollection);

		String listToString = productList.stream().map(Product::getName).collect(Collectors.joining(", "));
		System.out.println(listToString);
	}

	public static void main(String[] args) {
		// ArrayList<Integer> numbers = new ArrayList<Integer>();
		// numbers.add(5);
		// numbers.add(9);
		// numbers.add(8);
		// numbers.add(1);
		// numbers.forEach(n -> n += 1);
		// System.out.println(n);

		// Use Java's Consumer interface to store a lambda expression in a variable
		// ArrayList<Integer> numbers = new ArrayList<Integer>();
		// numbers.add(5);
		// numbers.add(9);
		// numbers.add(8);
		// numbers.add(1);
		// Consumer<Integer> method = (n) -> { System.out.println(n); };
		// numbers.forEach( method );

		SomeInterface method = s -> {
		return s + " " + ss;
		};
		Lambda(method);

		// reusingStreams();
		// streamPipeline();
		// lazyInvocation();

		// try {
		// 	fileStream();
		// } catch (Exception e) {
		// 	// TODO Auto-generated catch block
		// 	e.printStackTrace();
		// }

		// orderOfExecution();
		// usingReduce();
		// usingCollect();

		// creating streams
		// creatingStreams();
		// reusingStreams();
	}
}
