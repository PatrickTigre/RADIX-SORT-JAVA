import java.util.Arrays;

public class RadixSort {

    // Função para encontrar o maior número no array
    static int getMax(int[] arr, int n) {
        int max = arr[0];
        for (int i = 1; i < n; i++) {  //A função percorre o array e compara cada elemento com o valor máximo
            if (arr[i] > max)          // encontrado até aquele ponto. O maior valor é retornado no final
                max = arr[i];
        }
        return max;
    }

    // Função para fazer a ordenação por dígito usando Counting Sort
    static void countSort(int[] arr, int n, int exp) {
        int[] output = new int[n]; // array de saída
        int[] count = new int[10];
        Arrays.fill(count, 0);

        // Armazena contagens de ocorrências em count[]
        for (int i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        // Muda count[i] para que count[i] contenha a posição real desse dígito em output[]
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Construindo o array de saída
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copia o array de saída para arr[], de modo que arr[] agora esteja ordenado de acordo com o dígito atual
        System.arraycopy(output, 0, arr, 0, n);
    }

    // Função principal que ordena arr[] usando Radix Sort
    static void radixSort(int[] arr, int n) {
        int max = getMax(arr, n); //Usa getMax para encontrar o maior número em arr, 
        						 //o que determina quantos dígitos serão processados.
        
        // Faz a contagem do Counting Sort para cada dígito, em vez de valor do número.
        // Aqui exp é 10^i, onde i é o dígito atual
        for (int exp = 1; max / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }

    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        int n = arr.length;
        
        radixSort(arr, n);
        System.out.println("Array ordenado: " + Arrays.toString(arr));
    }
}
