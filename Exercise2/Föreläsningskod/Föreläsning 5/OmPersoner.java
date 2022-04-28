import java.util.*;

class OmPersoner{

    public static void main(String[] args){
	ArrayList<Person> folk = new ArrayList<>();
	
	Scanner sc = new Scanner(System.in);

	befolka(folk);

	System.out.print(">> ");
	String com = sc.nextLine();

	while (!com.equals("slut")){
	    if (com.equals("namn"))
		System.out.println("Sortering på namn ej implementerad!");
	    else if (com.equals("vikt"))
		folk.sort(Comparator.comparing(Person::getVikt));
	    else if (com.equals("pnr"))
		System.out.println("Sortering på pnr ej implementerad!");

		for(Person p : folk)
		System.out.println(p);

	    System.out.print(">> ");
	    com = sc.nextLine();

	} //while
    } // main
    
    static Person[] data = {
	new Person(new Persnr(530610, 1309), "Eva", 48),
	new Person(new Persnr(611000, 7543), "Stefan", 108),	
	new Person(new Persnr(731030, 3112), "Martin", 72),
	new Person(new Persnr(111110, 9532), "Ela", 83),
	new Person(new Persnr(160320, 9655), "Maria", 44),
	new Person(new Persnr(430420, 1876), "Ina", 77),
	new Person(new Persnr(761200, 4321), "Patrik", 84),
	new Person(new Persnr(101220, 6201), "Marian", 53),
	new Person(new Persnr(530611, 1309), "Anna", 48),
	new Person(new Persnr(611001, 7543), "Zack", 108),	
	new Person(new Persnr(731031, 3112), "Mathilda", 72),
	new Person(new Persnr(111111, 9532), "Ruben", 83),
	new Person(new Persnr(160321, 9655), "Peter", 44),
	new Person(new Persnr(430421, 1876), "Bodil", 63),
	new Person(new Persnr(761201, 4321), "Åsa", 84)
    };

    static void befolka(ArrayList<Person> vilka){
	for(Person p : data)
	    vilka.add(p);
    }
}
