import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));
        }

        //Количество несовершеннолетних
        long count = persons.stream()
                .filter(l -> l.getAge() < 18)
                .count();

        //Фамилии призывников
        List<String> familiesConscripts = persons.stream()
                .filter(l -> (l.getAge() >= 18 && l.getAge() <= 27 && l.getSex() == Sex.MAN))
                .map(Person::getFamily).collect(Collectors.toList());

        //Спиок работоспособных людей
        List<Person> workablePersons = persons.stream()
                .filter(l -> (l.getAge() >= 18 && l.getEducation() == Education.HIGHER))
                .filter(l -> (l.getAge() <= 65 && l.getSex() == Sex.MAN) || (l.getAge() <= 60 && l.getSex() == Sex.WOMAN))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
}
