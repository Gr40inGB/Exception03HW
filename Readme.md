
### Алгоритм
* сравнивается количество аргументов полученных при вводе. Если не совпадает с заданным - то выкидываем исключение HumanParsingException с заданным тесктом об ошибке
* если в процессе парсинга попалась строка, которая не подходит по критериям - выбрасываем исключение с текстом о проблемном элементе текста
* отдельно обрабатываем дату 

#### Допущения
* решил записывать в JSON вместо строк в треугольных скобках
* не доделал показ всех записей и слежение за уникальностью.
* встроенный парсинг LocalDate.parse - автоматом корректирует дату например если 31-го число в месяце отсутствует - подставляет 30. Не стал исправлять

```java
public class HumanService {
    public static final int ARGUMENTS_COUNT = 6;

    public static Human parseToHuman(String stringData) throws HumanParsingException {
        List<String> dataList = new ArrayList<>(Arrays.asList(stringData.split("\s+")));
        int dataLength = dataList.size();
        if (dataLength != ARGUMENTS_COUNT)
            throw new HumanParsingException("There are " +
                    (dataLength < ARGUMENTS_COUNT ? "not enough" : "too much") +
                    " arguments in your data! Need " +
                    ARGUMENTS_COUNT + ". You entered " + dataLength + "!");

        Human tempHumanLink = new Human();

        for (String s : dataList) {
            if (s.matches("[a-zA-Zа-яА-Я]+") && s.length() > 1 && tempHumanLink.getLastName().isEmpty())
                tempHumanLink.setLastName(s);
            else if (s.matches("[a-zA-Zа-яА-Я]+") && s.length() > 1 && tempHumanLink.getFirstName().isEmpty())
                tempHumanLink.setFirstName(s);
            else if (s.matches("[a-zA-Zа-яА-Я]+") && s.length() > 1 && tempHumanLink.getPatronymic().isEmpty())
                tempHumanLink.setPatronymic(s);
            else if
            (s.matches("[mMfF]")) tempHumanLink.setSex(s.equalsIgnoreCase("f") ? Sex.Female : Sex.Male);
            else if
            (s.matches("(0[1-9]|[12][0-9]|3[01])[-\\/.](0[1-9]|1[0-2])[-\\/.](19[0-9][0-9]|20[0-9][0-9])")) {
                tempHumanLink.setBirthDate(getValidLocalData(s));
            } else if (s.matches("(\\+?)([8|7|9])(-?)\\d{3}(-?)" +
                    "\\d{1}(-?)\\d{1}(-?)\\d{1}(-?)\\d{1}(-?)\\d{1}(-?)\\d{1}(-?)\\d{1}")) {
                tempHumanLink.setPhoneNumber(getValidPhone(s));
            } else throw new HumanParsingException("Can't parse this data: " + s);
        }
        return tempHumanLink;
    }

    public static boolean allHumanValuesGood(Human human) {
        return !human.getLastName().isEmpty() &&
                !human.getFirstName().isEmpty() &&
                !human.getPatronymic().isEmpty() &&
                human.getBirthDate() != null &&
                human.getPhoneNumber() != null &&
                human.getSex() != null;
    }

    public static String detailingHumanDataErrors(Human human) {
        StringBuilder stringBuilder = new StringBuilder("In input data not found: ");
        if (human.getLastName().isEmpty()) stringBuilder.append("Lastname ");
        if (human.getFirstName().isEmpty()) stringBuilder.append("FirstName ");
        if (human.getPatronymic().isEmpty()) stringBuilder.append("Patronymic ");
        if (human.getBirthDate() == null) stringBuilder.append("BirthDate ");
        if (human.getPhoneNumber() == null) stringBuilder.append("PhoneNumber ");
        if (human.getSex() == null) stringBuilder.append("Sex ");
        return stringBuilder.toString();
    }

    static LocalDate getValidLocalData(String stringData) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.parse(stringData.replaceAll("[-\\/.]", "."), formatter);
        if (localDate.isAfter(LocalDate.now()))
            throw new HumanParsingException("Found Data " + stringData + " is not correct!");
        return localDate;
    }

    static Long getValidPhone(String stringData) {
        String digitString = stringData.replaceAll("[-\\/.]", "");
        return Long.parseLong(digitString);
    }

    public HumanService() {}
}


```

Запись файла через Files.write - эта операция сама закрывает поток - поэтоу вручну закрывать не нужно
```java
    public void writeNewHuman(Human human) {
        this.updateLastNameSet();
        if (!this.allLastNames.contains(human.getLastName())) {
            System.out.println(createLastNameFile(human.getLastName()));
        }
        Gson humanGson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).setPrettyPrinting().create();
        String humanJSON = humanGson.toJson(human);

        try {
            Files.write(Path.of(DIRECTORY_NAME, human.getLastName()), humanJSON.getBytes(), StandardOpenOption.APPEND);
            System.out.println("Human " + human + " was written in \"database\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```