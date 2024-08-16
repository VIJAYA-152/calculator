import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class calc 
{
    private JFrame frame;
    private JTextField textField;
    private List<Double> numbers = new ArrayList<>();
    private List<String> operators = new ArrayList<>();

    public calc() 
    {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        textField = new JTextField();
        frame.add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        String[] buttons = 
        {
            "1", "2", "3", "+",
            "4", "5", "6", "-",
            "7", "8", "9", "/",
            "0", "C", "=", "*"
        };

        for (String i : buttons) 
        {
            JButton button = new JButton(i);
            panel.add(button);
            button.addActionListener(e -> buttonClicked(button.getText()));
        }

        frame.add(panel);
        frame.setVisible(true);
    }

    private void buttonClicked(String c)
     {
        switch (c) 
        {
            case "C":
                textField.setText("");
                numbers.clear();
                operators.clear();
                break;
            case "=":
                if (!textField.getText().isEmpty()) 
                {
                    numbers.add(Double.parseDouble(textField.getText()));
                }
                calculateResult();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                if (!textField.getText().isEmpty()) 
                {
                    numbers.add(Double.parseDouble(textField.getText()));
                    operators.add(c);
                    textField.setText("");
                }
                break;
            default:
                textField.setText(textField.getText() + c);
                break;
        }
    }

    private void calculateResult() 
    {
        if (numbers.isEmpty() || operators.isEmpty())
         {
            return;
        }

        double result = numbers.get(0);

        for (int i = 0; i < operators.size(); i++) 
        {
            double nextNum= numbers.get(i + 1);
            switch (operators.get(i)) 
            {
                case "+":
                    result += nextNum;
                    break;
                case "-":
                    result -= nextNum;
                    break;
                case "*":
                    result *= nextNum;
                    break;
                case "/":
                    result /= nextNum;
                    break;
            }
        }

        textField.setText(String.valueOf(result));
        numbers.clear();
        operators.clear();
        numbers.add(result);
    }

    public static void main(String[] args) 
    {
        new calc();
    }
}
