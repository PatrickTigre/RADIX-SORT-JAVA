import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RadixSortGUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField inputField;
    private JTextArea outputArea;

    public RadixSortGUI() {
        setTitle("Radix Sort");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criação dos componentes
        inputField = new JTextField(25);
        JButton sortButton = new JButton("ORGANIZAR");
        outputArea = new JTextArea(30, 50);
        outputArea.setEditable(false);

        // Ajuste do tamanho da fonte
        Font labelFont = new Font("Arial", Font.PLAIN, 25); // Fonte para o JLabel
        Font textFieldFont = new Font("Arial", Font.PLAIN, 25); // Fonte para o JTextField
        Font buttonFont = new Font("Arial", Font.PLAIN, 30); // Fonte para o JButton
        Font outputAreaFont = new Font("Arial", Font.PLAIN, 30); // Fonte para o JTextArea

        // Configurações dos componentes com a fonte ajustada
        JLabel inputLabel = new JLabel("DIGITE OS NUMEROS INTEIROS (SEPARADOS POR VIRGULA):");
        inputLabel.setFont(labelFont);
        inputField.setFont(textFieldFont);
        sortButton.setFont(buttonFont);
        outputArea.setFont(outputAreaFont);

        // Configuração do layout
        JPanel inputPanel = new JPanel(); // Painel para os componentes de entrada
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.add(sortButton);

        // Adiciona os componentes ao JFrame
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Ação do botão de ordenação
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputField.getText();
                String[] numberStrings = inputText.split(",");
                int[] numbers = new int[numberStrings.length];

                try {
                    for (int i = 0; i < numberStrings.length; i++) {
                        numbers[i] = Integer.parseInt(numberStrings[i].trim());
                    }
                    radixSort(numbers, numbers.length);
                    outputArea.setText("NUMEROS ORGANIZADOS: " + arrayToString(numbers));
                } catch (NumberFormatException ex) {
                    outputArea.setText("Insira números inteiros válidos.");
                }
            }
        });
    }

    // Função para converter um array para uma string
    private String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num).append(" ");
        }
        return sb.toString().trim();
    }

    // Função para encontrar o maior número no array
    static int getMax(int[] arr, int n) {
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max)
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

        // Constrói o array de saída
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copia o array de saída para arr[], de modo que arr[] agora esteja 
       //ordenado de acordo com o dígito atual
        System.arraycopy(output, 0, arr, 0, n);
    }

    // Função principal que ordena arr[] usando Radix Sort
    static void radixSort(int[] arr, int n) {
        int max = getMax(arr, n);

        // Faz a contagem do Counting Sort para cada dígito, em vez de valor do número.
        // Aqui exp é 10^i, onde i é o dígito atual
        for (int exp = 1; max / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RadixSortGUI().setVisible(true);
            }
        });
    }
}
