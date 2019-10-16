import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
public class main {
    public static void main(String [] args){
        Scanner myScan = new Scanner(System.in);
        Board gameBoard = new Board();
        JFrame frame = new JFrame();
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.white);
        frame.add(gameBoard);
        frame.setVisible(true);
        while (true) {
            //String nextGeneration = myScan.nextLine();
            //if (!(nextGeneration.isEmpty())){
            //    break;
            //}
            try {
                Thread.sleep(150);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            frame.repaint();
            gameBoard.graphicalAutomatonLogic.printGeneration();
            System.out.println();

//            testAutomaton.advanceGenereation();
//            testAutomaton.printGeneration();
//
        }
    }
}
