
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Exercise1Test {

	public static final String ORDER_INCORRECT_VALUE_EXCL_VAT = "Värdet exkl. moms stämmer inte.";
	public static final String ORDER_INCORRECT_VALUE_INCL_VAT = "Värdet inkl. moms stämmer inte.";
	public static final String BOUND_BOOK_INCORRECT_VALUE_EXCL_VAT = "Värdet (exkl. moms) på en inbunden bok beräknas inte rätt.";
	public static final String BOUND_BOOK_INCORRECT_VALUE_INCL_VAT = "Värdet (inkl. moms) på en inbunden bok beräknas inte rätt.";
	public static final String UNBOUND_BOOK_INCORRECT_VALUE_EXCL_VAT = "Värdet (exkl. moms) på en icke inbunden bok beräknas inte rätt.";
	public static final String UNBOUND_BOOK_INCORRECT_VALUE_INCL_VAT = "Värdet (inkl. moms) på en icke inbunden bok beräknas inte rätt.";
	public static final String INCORRECT_VAT_VALUE = "Klassen har fått fel momsvärde.";

	@Test
	@org.junit.jupiter.api.Order(0)
	void _version() {
		System.out.println("Test version 1.3 (2021-03-31)");
	}

	@ParameterizedTest
	@org.junit.jupiter.api.Order(10)
	@DisplayName("Book: testar att inbundna böcker får rätt pris.")
	@CsvSource({"100.0,125.0,132.5", "0.0,0.0,0.0"})
	void bookCorrectPriceForBoundBook(double price, double expPrice, double expPricePlusVat) {
		var book = new Book("A Name", "An Author", price, true);
		assertEquals(0.06, book.getVAT(), INCORRECT_VAT_VALUE);
		assertEquals(expPrice, book.getPrice(), BOUND_BOOK_INCORRECT_VALUE_EXCL_VAT);
		assertEquals(expPricePlusVat, book.getPricePlusVAT(), BOUND_BOOK_INCORRECT_VALUE_INCL_VAT);
	}

	@ParameterizedTest
	@org.junit.jupiter.api.Order(11)
	@DisplayName("Book: testar att icke inbundna böcker får rätt pris.")
	@CsvSource({"100.0,100.0,106.0", "0.0,0.0,0.0"})
	void bookCorrectPriceForUnboundBook(double price, double expPrice, double expPricePlusVat) {
		var book = new Book("A Name", "An Author", price, false);
		assertEquals(expPrice, book.getPrice(), UNBOUND_BOOK_INCORRECT_VALUE_EXCL_VAT);
		assertEquals(expPricePlusVat, book.getPricePlusVAT(), UNBOUND_BOOK_INCORRECT_VALUE_INCL_VAT);
	}

	@ParameterizedTest
	@org.junit.jupiter.api.Order(12)
	@DisplayName("Book: testar att toString innehåller nödvändig information.")
	@CsvSource({"For Whom The Bell Tolls, Ernest Hemingway, 125.0, true",
			"William Gibson, Neuromancer, 200.0, false"})
	void bookCorrectToString(String title, String author, double price, boolean bound) {
		var book = new Book(title, author, price, bound);
		var parts = Set.of(title, author, String.valueOf(price * (bound ? 1.25 : 1.0)), String.valueOf(bound));
		var message = String.format("Klassen Book: toString saknar information. Letade efter %s i strängen '%s' men något saknades.", parts, book.toString());
		assertTrue(parts.stream().allMatch(s -> book.toString().contains(s)), message);
	}

	@Test
	@org.junit.jupiter.api.Order(13)
	@DisplayName("Book: testar att klassen har nödvändiga medlemsvariabler.")
	void bookHasCorrectFields() {
		var clazz = Book.class;
		var fields = clazz.getDeclaredFields();
		var hasCorrectFieldNames = Arrays.stream(fields).map(Field::getName).collect(Collectors.toSet()).containsAll(Set.of("author", "bound", "price"));
		assertTrue(fields.length >= 3, "Klassen ska innehålla minst tre medlemsvariabler.");
		assertTrue(hasCorrectFieldNames, "Klassen innehåller inte rätt medlemsvariabler.");
	}

	@Test
	@org.junit.jupiter.api.Order(14)
	@DisplayName("Book: testar att klassen implementerar rätt gränssnitt")
	void bookImplementsCorrectInterface() {
		var clazz = Book.class;
		var interfaces = clazz.getInterfaces();
		var implementsInterface = Vat6.class.isAssignableFrom(clazz);

		assertEquals(1, interfaces.length, "Enligt klassdiagrammet ska klassen bara implementera ett gränssnitt.");
		assertTrue(implementsInterface, "Klassen implementerar inte rätt gränssnitt.");
	}

	@Test
	@org.junit.jupiter.api.Order(15)
	@DisplayName("Book: testar att klassen har nödvändiga metoder.")
	void bookImplementsCorrectMethods() {
		var clazz = Book.class;
		var methods = clazz.getDeclaredMethods();
		var pubMethods = Arrays.stream(methods).filter(method -> Modifier.isPublic(method.getModifiers())).count();

		assertEquals(2, pubMethods, "Enligt klassdiagrammet ska klassen ha exakt två publika metoder utöver konstruktorn.");

		try {
			clazz.getDeclaredMethod("getPrice");
		} catch (NoSuchMethodException e) {
			fail("Kunde inte hitta metoden getPrice.");
		}
	}

	@Test
	@org.junit.jupiter.api.Order(1)
	@DisplayName("Item: testar att klassens konstruktor är korrekt.")
	void itemHasCorrectConstructor() {
		var clazz = Item.class;
		var ctors = clazz.getDeclaredConstructors();
		var ctor = ctors[0];
		var ctorParameters = ctor.getParameterCount();
		var ctorType = ctor.getParameters()[0].getType();

		assertEquals(1, ctors.length, "Enligt klassdiagrammet ska det bara finnas en enda konstruktor.");
		assertEquals(1, ctorParameters, "Den enda konstruktorn ska enligt klassdiagrammet bara ha en enda parameter.");
		assertEquals(String.class, ctorType, "Den enda konstruktorns enda parameter har inte rätt typ.");
	}

	@Test
	@org.junit.jupiter.api.Order(2)
	@DisplayName("Item: testar att klassen har nödvändiga medlemmar.")
	void itemHasCorrectMember() {
		var clazz = Item.class;
		var fields = clazz.getDeclaredFields();
		var field = fields[0];
		var fieldType = field.getType();
		var isFinal = Modifier.isFinal(field.getModifiers());

		assertEquals(1, fields.length, "Enligt klassdiagrammet ska det bara finnas en enda instansvariabel.");
		assertEquals(String.class, fieldType, "Den enda instansvariabeln har inte den förväntade typen.");
		assertTrue(isFinal, "Den enda medlemsvariabeln ska inte kunna ändra värde.");
	}

	@Test
	@org.junit.jupiter.api.Order(3)
	@DisplayName("Item: testar att klassen har nödvändiga metoder")
	void itemHasCorrectMethods() {

		var clazz = Item.class;
		var methods = clazz.getDeclaredMethods();
		var pubMethods = Arrays.stream(methods).filter(method -> Modifier.isPublic(method.getModifiers())).count();

		assertEquals(2, pubMethods, "Enligt klassdiagrammet ska klassen bara ha två publika metoder utöver konstruktorn.");

		Method method = null;
		try {
			method = clazz.getDeclaredMethod("getPrice");
		} catch (NoSuchMethodException e) {
			fail("Kunde inte hitta metoden getPrice.");
		}
		assertTrue(Modifier.isAbstract(method.getModifiers()), "Metoden getPrice ska vara abstrakt.");

		try {
			method = clazz.getDeclaredMethod("getPricePlusVAT");
		} catch (NoSuchMethodException e) {
			fail("Kunde inte hitta metoden getPricePlusVAT.");
		}
		assertTrue(Modifier.isFinal(method.getModifiers()), "Metoden getPricePlusVAT ska inte gå att överskugga.");
	}

	@Test
	@org.junit.jupiter.api.Order(4)
	@DisplayName("Item: testar att klassen implementerar rätt gränssnitt.")
	void itemImplementsVat() {
		var clazz = Item.class;
		var interfaces = clazz.getInterfaces();
		var implementsInterface = Vat.class.isAssignableFrom(clazz);

		assertEquals(1, interfaces.length, "Enligt klassdiagrammet ska klassen bara implementera ett gränssnitt.");
		assertTrue(implementsInterface, "Klassen implementerar inte rätt gränssnitt.");
	}

	@Test
	@org.junit.jupiter.api.Order(5)
	@DisplayName("Item: testar att klassen inte kan instansieras.")
	void itemIsAbstract() {
		assertTrue(ReflectionUtils.isAbstract(Item.class), "Klassen Item ska vara abstrakt och inte kunna instansieras.");
	}

	@ParameterizedTest
	@org.junit.jupiter.api.Order(20)
	@DisplayName("Recording: testar att CompactDisc får rätt pris.")
	@CsvSource({"2021,10,200,200,250", "2021,5,200,100,125", "2021,1,200,20,25", "2021,0,200,10,12.5"})
	void recordingCorrectPriceForCD(int year, int condition, double price, double expected, double plusVat) {
		var message = String.format("Klassen CompactDisc: värdet (exkl. moms) på en CD beräknas inte rätt för slitage %d.", condition);

		var cdCond10 = new CompactDisc("A Name", "An Artist", year, condition, price);

		assertEquals(0.25, cdCond10.getVAT(), "Klassen CompactDisc: " + INCORRECT_VAT_VALUE + ".");
		assertEquals(expected, cdCond10.getPrice(), message);
		assertEquals(plusVat, cdCond10.getPricePlusVAT(), message);
	}

	@ParameterizedTest
	@org.junit.jupiter.api.Order(21)
	@DisplayName("Recording: testar att LongPlay får rätt pris.")
	@CsvSource({"2021,10,200,200,250", "2021,5,200,100,125", "2021,1,200,20,25", "2021,0,200,10,12.5"})
	void recordingCorrectPriceForLP(int year, int condition, double price, double expected, double plusVat) {
		var message = String.format("Klassen LongPlay: värdet (exkl. moms) på en LP beräknas inte rätt för slitage %d.", condition);

		var item = new LongPlay("A Name", "An Artist", year, condition, price);

		assertEquals(expected, item.getPrice(), message);
		assertEquals(plusVat, item.getPricePlusVAT(), message);
	}

	@ParameterizedTest
	@org.junit.jupiter.api.Order(22)
	@DisplayName("Recording: testar att äldre LongPlay får rätt pris.")
	@CsvSource({"2020,10,200,205,256.25", "1950,10,200,555,693.75"})
	void recordingCorrectPriceForLPFrom2020(int year, int condition, double price, double expected, double plusVat) {
		var message = String.format("Klassen LongPlay: värdet (exkl. moms) på en LP från " + year + " beräknas inte rätt för slitage %d.", condition);

		var item = new LongPlay("A Name", "An Artist", year, condition, price);

		assertEquals(expected, item.getPrice(), message);
		assertEquals(plusVat, item.getPricePlusVAT(), message);
	}

	@ParameterizedTest
	@org.junit.jupiter.api.Order(23)
	@DisplayName("Recording: testar att toString innehåller nödvändig information för CD.")
	@CsvSource({"Phoebe Bridgers, Punisher, 2021, 10, 200.0", "Bruce Springsteen, The River, 1980, 10, 300.0"})
	void recordingCorrectToStringCD(String artist, String title, int year, int condition, double price) {
		var item1 = new CompactDisc(title, artist, year, condition, price);
		var parts = Set.of(title, artist, String.valueOf(year), String.valueOf(condition), String.valueOf(price));
		var type = item1.getClass().getSimpleName();

		var strings = String.format("%s (titel), %s (artist), %d (år), %d (slitage), %.3f (pris)", title, artist, year, condition, price);
		var message = String.format("Klassen Recording: toString saknar information.%nLetade efter %s i strängen:%n%n'%s'%n%nmen något saknades för subtypen %s. Jämförelsen gör skillnad på stora och små bokstäver.%n", strings, item1.toString(), type);

		assertTrue(parts.stream().allMatch(s -> item1.toString().contains(s)), message);
	}

	@ParameterizedTest
	@org.junit.jupiter.api.Order(23)
	@DisplayName("Recording: testar att toString innehåller nödvändig information för LP.")
	@CsvSource({"Phoebe Bridgers, Punisher, 2021, 10, 200.0", "Bruce Springsteen, The River, 1980, 10, 300.0"})
	void recordingCorrectToStringLP(String artist, String title, int year, int condition, double price) {
		var item1 = new LongPlay(title, artist, year, condition, price);
		var parts = Set.of(title, artist, String.valueOf(year), String.valueOf(condition), String.valueOf(price));
		var type = item1.getClass().getSimpleName();

		var strings = String.format("%s (titel), %s (artist), %d (år), %d (slitage), %.3f (pris)", title, artist, year, condition, price);
		var message = String.format("Klassen Recording: toString saknar information.%nLetade efter %s i strängen:%n%n'%s'%n%nmen något saknades för subtypen %s. Jämförelsen gör skillnad på stora och små bokstäver.%n", strings, item1.toString(), type);

		assertTrue(parts.stream().allMatch(s -> item1.toString().contains(s)), message);
	}

	@Test
	@org.junit.jupiter.api.Order(24)
	@DisplayName("Recording: testar att värdet inte hamnar under 10.0.")
	void recordingGetsCorrectMinimumValue() {
		Recording item = new LongPlay("test", "test", 2020, 1, 90);
		assertEquals(14.0, item.getPrice(), "Fel värde för Longplay med condition 1.");

		item = new CompactDisc("test", "test", 2020, 0, 5);
		assertEquals(10.0, item.getPrice(), "Fel värde för CompactDisc med condition 0.");
	}

	@Test
	@org.junit.jupiter.api.Order(25)
	@DisplayName("Recording: testar att klassen har nödvändiga medlemsvariabler.")
	void recordingHasCorrectFields() {
		var clazz = Recording.class;
		var fields = clazz.getDeclaredFields();
		var hasCorrectFieldNames = Arrays.stream(fields).map(Field::getName).collect(Collectors.toSet()).containsAll(Set.of("year", "artist", "condition", "price"));
		assertTrue(fields.length >= 4, "Klassen innehåller inte rätt antal medlemsvariabler.");
		assertTrue(hasCorrectFieldNames, "Klassen innehåller inte rätt medlemsvariabler.");
	}

	@Test
	@org.junit.jupiter.api.Order(26)
	@DisplayName("Recording: testar att klassen implementerar rätt gränssnitt.")
	void recordingImplementsCorrectInterface() {
		var clazz = Recording.class;
		var interfaces = clazz.getInterfaces();
		var implementsInterface = Vat25.class.isAssignableFrom(clazz);

		assertEquals(1, interfaces.length, "Enligt klassdiagrammet ska klassen bara implementera ett gränssnitt.");
		assertTrue(implementsInterface, "Klassen implementerar inte rätt gränssnitt.");
	}

	@Test
	@org.junit.jupiter.api.Order(27)
	@DisplayName("Recording: testar att klassen har nödvändiga metoder.")
	void recordingImplementsCorrectMethods() {
		var clazz = Recording.class;
		var methods = clazz.getDeclaredMethods();
		var pubMethods = Arrays.stream(methods).filter(method -> Modifier.isPublic(method.getModifiers())).count();

		assertEquals(1, pubMethods, "Enligt klassdiagrammet ska klassen bara ha en publik metod utöver konstruktorn.");
		assertEquals("toString", methods[0].getName(), "Den enda metoden som ska finnas (utöver konstruktorn) har fel namn.");
	}

	@Test
	@org.junit.jupiter.api.Order(28)
	@DisplayName("Recording: testar att klassen inte kan instansieras.")
	void recordingIsAbstract() {
		assertTrue(ReflectionUtils.isAbstract(Recording.class), "Klassen Recording ska vara abstrakt och inte kunna instansieras.");
	}

	@Test
	@org.junit.jupiter.api.Order(29)
	@DisplayName("Order: testar metoderna getPrice, getPricePlusVAT och getReceipt.")
	void testOrderMethods() {
		Item book1 = new Book("A guide to modern jazz", "Unknown author", 100, false);
		Item book2 = new Book("Beethoven: a biography", "Holmqvist", 400, false);
		Item book2bound = new Book("Beethoven: a biography", "Holmqvist", 400, true);

		Item item1 = new LongPlay("Giant Steps", "John Coltrane", 1959, 10, 100);
		Item cd2 = new CompactDisc("Kind of Blue", "Miles Davis", 1959, 5, 100);
		Item lp1 = new CompactDisc("Punisher", "Phoebe Bridgers", 2020, 10, 200);
		Item lp2 = new LongPlay("What Kinda Music", "Tom Misch", 2020, 10, 150);
		Item lp3 = new LongPlay("Little Oblivions", "Julien Baker", 2021, 10, 120);

		/*
		Receipt for order #1
		-----------
		Book { name='A guide to modern jazz', author='Unknown author', bound=false, price=100.0, price+vat=106.0 }
		Book { name='Beethoven: a biography', author='Holmqvist', bound=true, price=500.0, price+vat=530.0 }
		Total excl. VAT: 600.0
		Total incl. VAT: 636.0
		*/

		var message = "Metoden getReceipt saknar information.%nLetade efter följande ord %s i strängen:%n%n%s%nmen något saknades.%n";

		var order = new Order(book1, book2bound);
		var receipt = order.getReceipt();

		assertEquals(600.0, order.getTotalValue(), ORDER_INCORRECT_VALUE_EXCL_VAT);
		assertEquals(636.0, order.getTotalValuePlusVAT(), ORDER_INCORRECT_VALUE_INCL_VAT);

		var part = Set.of("Book", "A guide to modern jazz", "Unknown author", "false", "100.0", "106.0");
		assertTrue(part.stream().allMatch(receipt::contains), String.format(message, part, receipt));

		part = Set.of("Book", "Beethoven: a biography", "Holmqvist", "true", "500.0", "530.0");
		assertTrue(part.stream().allMatch(receipt::contains), String.format(message, part, receipt));


		/*
		Receipt for order #2
		-----------
		Book { name='Beethoven: a biography', author='Holmqvist', bound=false, price=400.0, price+vat=424.0 }
		Long Play Record { name=Giant Steps, artist='John Coltrane', year=1959, type=LP, condition=10, original price=100.0, price=410.0, price+vat=512.5 }
		Compact Disc { name=Kind of Blue, artist='Miles Davis', year=1959, type=CD, condition=5, original price=100.0, price=50.0, price+vat=62.5 }
		Total excl. VAT: 860.0
		Total incl. VAT: 999.0
		*/
		order = new Order(book2, item1, cd2);
		receipt = order.getReceipt();

		assertEquals(860.0, order.getTotalValue(), ORDER_INCORRECT_VALUE_EXCL_VAT);
		assertEquals(999.0, order.getTotalValuePlusVAT(), ORDER_INCORRECT_VALUE_INCL_VAT);

		part = Set.of("Book", "Beethoven: a biography", "Holmqvist", "false", "400.0", "424.0");
		assertTrue(part.stream().allMatch(receipt::contains), String.format(message, part, receipt));

		part = Set.of("Long Play Record", "Giant Steps", "John Coltrane", "1959", "10", "100.0", "410.0", "512.5");
		assertTrue(part.stream().allMatch(receipt::contains), String.format(message, part, receipt));

		part = Set.of("Compact Disc", "Kind of Blue", "Miles Davis", "1959", "5", "100.0", "50.0", "62.5");
		assertTrue(part.stream().allMatch(receipt::contains), String.format(message, part, receipt));

		/*
		Receipt for order #3
		-----------
		Compact Disc { name=Punisher, artist='Phoebe Bridgers', year=2020, type=CD, condition=10, original price=200.0, price=200.0, price+vat=250.0 }
		Long Play Record { name=What Kinda Music, artist='Tom Misch', year=2020, type=LP, condition=10, original price=150.0, price=155.0, price+vat=193.75 }
		Long Play Record { name=Little Oblivions, artist='Julien Baker', year=2021, type=LP, condition=10, original price=120.0, price=120.0, price+vat=150.0 }
		Total excl. VAT: 475.0
		Total incl. VAT: 593.75
		 */
		order = new Order(lp1, lp2, lp3);
		receipt = order.getReceipt();

		assertEquals(475.0, order.getTotalValue(), ORDER_INCORRECT_VALUE_EXCL_VAT);
		assertEquals(593.75, order.getTotalValuePlusVAT(), ORDER_INCORRECT_VALUE_INCL_VAT);

		part = Set.of("Compact Disc", "Punisher", "Phoebe Bridgers", "2020", "10", "200.0", "250.0");
		assertTrue(part.stream().allMatch(receipt::contains), String.format(message, part, receipt));

		part = Set.of("Long Play Record", "What Kinda Music", "Tom Misch", "2020", "10", "150.0", "155.0", "193.75");
		assertTrue(part.stream().allMatch(receipt::contains), String.format(message, part, receipt));

		part = Set.of("Long Play Record", "Little Oblivions", "Julien Baker", "2021", "10", "120.0", "150.0");
		assertTrue(part.stream().allMatch(receipt::contains), String.format(message, part, receipt));
	}

	@Test
	@org.junit.jupiter.api.Order(1)
	@DisplayName("Kontrollerar att gränssnitten Vat, Vat6 och Vat25 är korrekta.")
	void vatInterfaceTests() {
		assertTrue(ReflectionUtils.isAbstract(Vat.class));
		assertTrue(ReflectionUtils.isAbstract(Vat6.class));
		assertTrue(ReflectionUtils.isAbstract(Vat25.class));

		assertEquals(1, Vat.class.getDeclaredMethods().length, "Gränssnittet Vat ska bara ha en enda metod.");

		class TEST_VAT6 implements Vat6 {
		}
		assertEquals(0.06, new TEST_VAT6().getVAT(), "Metoden getVAT ger inte rätt värde för gränssnittet Vat6.");

		class TEST_VAT25 implements Vat25 {
		}
		assertEquals(0.25, new TEST_VAT25().getVAT(), "Metoden getVAT ger inte rätt värde för gränssnittet Vat25.");
	}
}