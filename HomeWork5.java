import java.security.KeyStore.Entry;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Map.*;



public class HomeWork5 {

    public static void main(String[] args) {
        /*Реализуйте структуру телефонной книги с помощью HashMap, учитывая, что 1 человек может 
        иметь несколько телефонов. */
        //PhoneDict();

        /* Пусть дан список сотрудников: Иван Иванов и т.д.
        Написать программу, которая найдет и выведет повторяющиеся имена с
        количеством повторений. Отсортировать по убыванию популярности. */
        //RepeatNames();
        /*2) Реализовать алгоритм пирамидальной сортировки (HeapSort). */
        //HeapSort();
        /*Доп задание. На шахматной доске расставить 8 ферзей так, чтобы они не били друг друга.*/
        Chess();

    }


    private static void PhoneDict() {
        String encoding = System.getProperty("console.encoding", "cp866");
        Scanner iScanner = new Scanner(System.in, encoding);
        HashMap<String, ArrayList> phones = new HashMap <String, ArrayList>(); //создаю HashMap для хранения тел справочника
        System.out.println("Введите фамилию и телефонный номер: "); //Ввод строки Фимилия Телефон через пробел
        String entry = iScanner.nextLine(); //ввод данных из консоли
        while (entry != ""){ // ввод будет до тех пор, пока не встретится пустая строка
            String [] data = entry.split(" "); // создаю массив строк из введенной строки
            if (!phones.containsKey(data[0])){ // проверяю, если в справочнике нет такой фамилии, то
                ArrayList <String> phone = new ArrayList<String>(); //создаю переменную - список телефонов
                phone.add(data[1]); // добавляю в список телефон
                phones.put(data[0],phone); //добавляю в справочник - Имя и телефон
            }
            else { //если фамилия есть в справочнике
                phones.get(data[0]).add(data[1]); //добавляю новый номер телефона к существующей записи
            }
            System.out.println("Введите фамилию и телефонный номер: "); 
            entry = iScanner.nextLine(); 
            }
            iScanner.close();
            System.out.println(phones);
        }
/* Пусть дан список сотрудников: Иван Иванов и т.д.
Написать программу, которая найдет и выведет повторяющиеся имена с
количеством повторений. Отсортировать по убыванию популярности. */
    private static void RepeatNames() {
        ArrayList <String> list = new ArrayList<String>();
        HashMap<String, Integer> employees = new HashMap<String, Integer>();
        list.add("Иван Иванов");
        list.add("Светлана Петрова");
        list.add("Кристина Белова");
        list.add("Анна Мусина");
        list.add("Анна Крутова");
        list.add("Иван Юрин");
        list.add("Петр Лыков");
        list.add("Павел Чернов");
        list.add("Петр Чернышов");
        list.add("Мария Федорова");
        list.add("Марина Светлова");
        list.add("Мария Савина");
        list.add("Мария Рыкова");
        list.add("Марина Лугова");
        list.add("Анна Владимирова");
        list.add("Иван Мечников");
        list.add("Петр Петин");
        list.add("Иван Ежов");
        for (int i = 0; i < list.size(); i++) {
            String [] entry = list.get(i).split(" ");
            if (!employees.containsKey(entry[0])) employees.put(entry[0], 1);
            else {
                employees.put(entry[0], employees.get(entry[0])+1);
            } 
        }
        List list1 = employees.entrySet()
            .stream()
            .filter(a -> a.getValue() > 1)
            .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
            .toList();
        for (Object st : list1) {
            System.out.println(st.toString());
        }
    }

    /* 2) Реализовать алгоритм пирамидальной сортировки (HeapSort). */
    private static void HeapSort() {
        int [] arr = matrix(); // заполняю массив из рандомных элементов
        printArray(arr); 
        System.out.println();
        int len = arr.length; // переменная - длина массива
        int max_n = 0; // инициирую максимальный элемент
        int left = max_n * 2 + 1; // индекс левого элемента
        int right = max_n * 2 + 2; // индекс правого элемента
        for (int i = len / 2 - 1; i >= 0; i--) HSRecursion(arr, len, i); //построение дерева
       
        for (int i = len-1; i>=0; i--)
        {
            int temp = arr[0]; // перемещаем текущий корневой элемент в конец
            arr[0] = arr[i];
            arr[i] = temp;
            HSRecursion(arr, i, 0); // вызываем процедуру на уменьшенном дереве
        }
        printArray(arr);
    }

    private static void HSRecursion(int[] arr, int n, int i) {
        int max_n = i; // инициализация корневого элемента - индекс
        int left = max_n * 2 + 1; // индекс левого элемента
        int right = max_n * 2 + 2; // индекс правого элемента
        if (left < n && arr[i] < arr[left]) max_n = left; //если левый элемент больше корневого, то максимальный элемент становится левый
        if (right < n && arr[max_n] < arr[right]) max_n = right; //если правый элемент больше корневого, то максимальный становится правый
        if (max_n != i) { // если  максимальный не равен корневому, то делаем замену корневого
            int temp = arr[i];
            arr[i] = arr[max_n];
            arr[max_n] = temp;
            HSRecursion(arr, n, max_n); // рукурсивно проверяем поддерево
        }
    }

    public static int[] matrix() {
        Random rand = new Random();
        int[]mat = new int[20];
        for (int i = 0; i < mat.length; i++) {
            mat[i] = rand.nextInt(1,20);
        }
        return mat;
    }
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+", ");
        } 
    }
// 3). * Доп задание. На шахматной доске расставить 8 ферзей так, чтобы они не били друг друга.
    private static void Chess() {
        int [] position = new int[8]; //массив, где пытаюсь собрать решение, значение эелемента массива - строка, индекс элемента - столбец
        int [] horizont = new int[8]; //массив: i элемент = 1, если ферзь стоит в i строке
        int [] mainDiagonal = new int[15]; // диагональ слева-направо, снизу вверх: i элемент = 1, если на этой диагонали стоит ферзь
        int [] secondDiagonal = new int[15]; // диагональ слева-направо, сверху вниз: i элемент = 1, если на этой диагонали стоит ферзь

        ArrayList<int[]> solution = new ArrayList<int[]>(); // список, куда складываю все решения

        FindSolution(8, horizont,mainDiagonal,secondDiagonal,position,0,solution); //рекурсивная функция по поиску решений
        for (int[] sol : solution) { // вывод всех решений, как перечень всех массивов solution
            printArray(sol);  
            System.out.println();
        }
        }

    private static void FindSolution(int n, int[]horizont, int[]mainDiagonal,int[]secondDiagonal,int[]position,int j,ArrayList<int[]> solution) {
        for (int i = 0; i < n; i++) { // прохожусь по строкам
            if (horizont[i] + mainDiagonal[i + j] +secondDiagonal[n - 1 - i + j] == 0){ // соот-ий элемент по горизонтали и в диагоналях ==0, то можно поставить ферзя
                horizont[i] = 1; // след-ие строки - ставлю ферзя
                mainDiagonal[i + j] = 1;
                secondDiagonal[n - 1 - i + j] = 1;
                position[j] = i;
                if (j < n-1) FindSolution(n,horizont,mainDiagonal,secondDiagonal,position,j+1,solution); // если j < n-1, иду на поиск нового ферзя, вызов функции
                else{
                    int[] result = new int [8]; // если j = номеру последнего столбца - значит удалось поставить все ферзи, решение найдено
                    result = position.clone();
                    solution.add(result);
                }
            horizont[i] = 0; // откатываю постановку ферзя, обнуляя горизонталь и диагонали
            mainDiagonal[i + j] = 0;
            secondDiagonal[n - 1 - i + j] = 0;
            }
        } 
    }    
}