import java.util.ArrayList;
import javax.swing.*;


abstract class Pet {
    String name;
    int age;
    String type;

    abstract void displayinfo();
}


class AdoptablePet extends Pet {
    String breed;
    boolean isAdopted = false;

    @Override
    void displayinfo() {
        System.out.println("The name of pet is " + name);
        System.out.println("The age of pet is " + age);
        System.out.println("The type of pet is " + type);
        System.out.println("The breed of pet is " + breed);
        System.out.println("Is adopted: " + (isAdopted ? "Yes" : "No"));
    }

    @Override
    public String toString() {
        return "Name: " + name +
               ", Age: " + age +
               ", Type: " + type +
               ", Breed: " + breed +
               ", Adopted: " + (isAdopted ? "Yes" : "No");
    }
}


public class PetManager {

    static ArrayList<AdoptablePet> pets = new ArrayList<>();

    public static void main(String[] args) {
      
        JFrame frame = new JFrame("Pet Manager");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JButton addBtn = new JButton("Add Pet");
        JButton removeBtn = new JButton("Remove Pet");
        JButton adoptBtn = new JButton("Mark as Adopted");
        JButton viewBtn = new JButton("View All Pets");
        addBtn.setBounds(30, 20, 150, 30);
        removeBtn.setBounds(200, 20, 150, 30);
        adoptBtn.setBounds(370, 20, 150, 30);
        viewBtn.setBounds(30, 60, 490, 30);

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(30, 110, 490, 300);

        frame.add(addBtn);
        frame.add(removeBtn);
        frame.add(adoptBtn);
        frame.add(viewBtn);
        frame.add(scrollPane);

        addBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter pet name:");
            String ageStr = JOptionPane.showInputDialog("Enter pet age:");
            String type = JOptionPane.showInputDialog("Enter pet type:");
            String breed = JOptionPane.showInputDialog("Enter pet breed:");
            if (name != null && ageStr != null && type != null && breed != null) {
                try {
                    int age = Integer.parseInt(ageStr);
                    AdoptablePet newPet = new AdoptablePet();
                    newPet.name = name;
                    newPet.age = age;
                    newPet.type = type;
                    newPet.breed = breed;
                    pets.add(newPet);
                    outputArea.setText("Pet added: " + name);
                } catch (NumberFormatException ex) {
                    outputArea.setText("Invalid age entered.");
                }
            }
        });

        removeBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter name of pet to remove:");
            boolean removed = pets.removeIf(p -> p.name != null && p.name.equalsIgnoreCase(name));
            outputArea.setText(removed ? "Pet removed." : "Pet not found.");
        });

        adoptBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter name of pet to mark as adopted:");
            boolean found = false;
            for (AdoptablePet p : pets) {
                if (p.name != null && p.name.equalsIgnoreCase(name)) {
                    p.isAdopted = true;
                    found = true;
                    break;
                }
            }
            outputArea.setText(found ? "Pet marked as adopted." : "Pet not found.");
        });

        viewBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (AdoptablePet p : pets) {
                sb.append(p.toString()).append("\n");
            }
            outputArea.setText(sb.length() > 0 ? sb.toString() : "No pets available.");
        });

        frame.setVisible(true);
    }
}
