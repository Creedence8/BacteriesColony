import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Cole S' Offe on 22.01.2017.
 */
public class BacteriesColony {

    public static void main(String[] args) {

        //Тестирование на данных из условия
        int[] test0 = new int[] {5, 3, 4, 6, 1 };
        int[] test1 = new int[] {1, 5, 4, 9 };
        int[] test2 = new int[] {78, 34, 3, 54, 44, 99 };
        int[] test3 = new int[] {32, 68, 50, 89, 34, 56, 47, 30, 82, 7, 21, 16, 82, 24, 91 };
        assert Arrays.equals(performTheExperiment(test0), new int[] {5, 4, 4, 4, 1}) : "Failed test #0";
        assert Arrays.equals(performTheExperiment(test1), new int[] {1, 4, 5, 9 }) : "Failed test #1";
        assert Arrays.equals(performTheExperiment(test2), new int[] {78, 34, 34, 49, 49, 99 }) : "Failed test #2";
        assert Arrays.equals(performTheExperiment(test3), new int[] {32, 59, 59, 59, 47, 47, 47, 47, 47, 18, 18, 19, 53, 53, 91 }) : "Failed test #3";

    }

    public static int[] performTheExperiment(int[] colonies) {

        //Проверка корректности введенных данных
        if (colonies.length<3 || colonies.length>50) {throw new RuntimeException("Incorrect number of elements");}
        for (int foo:colonies) {if (foo<1 || foo>100) {throw new RuntimeException("Incorrect volume of element: "+foo);}}

        //Создание коллекций для хранения индексов значений, которые следует увеличить/уменьшить
        ArrayList<Integer> growing = new ArrayList<>();
        ArrayList<Integer> shrinking = new ArrayList<>();

        //Бесконечный цикл "день-ночь". Прерывается после наступления равновесия в системе.
        for(;;) {
            //Проходим по массиву colonies от второго до предпоследнего элемента
            for (int i = 1; i < colonies.length - 1; i++) {
                //Запоминаем индексы значений, которые следует увеличить
                if (colonies[i - 1] > colonies[i] && colonies[i] < colonies[i + 1]) {
                    growing.add(i);
                    continue;
                }
                //Запоминаем индексы значений, которые следует уменьшить
                if (colonies[i - 1] < colonies[i] && colonies[i] > colonies[i + 1]) {
                    shrinking.add(i);
                }
            }
            //После "дневного" цикла проверяем, есть ли значения для изменения. Если нет, возвращаем массив colonies.
            if (growing.size()+shrinking.size()==0) {
                return colonies;
            }

            //Изменяем значения массива colonies и чистим коллекции
            for (Integer foo : growing) {
                colonies[foo]++;
            }
            growing.clear();

            for (Integer bar : shrinking) {
                colonies[bar]--;
            }
            shrinking.clear();
        }
    }
}