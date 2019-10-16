import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Board extends JComponent {
    final int rowNum = 100;
    final int colNum = 100;
    final int margin = 31;
    final int boardWidth = 800 - (margin*2);
    final int boardHeight = 800 - (margin*2);
    final int rowHeight = boardWidth/(rowNum);
    final int colHeight = boardHeight/(colNum);
    final int strokeWidth = 1;
    int testRow = 1;
    int testCol = 1;
    int generation = 0;
    ArrayList<ActivatedSquare> activatedSquares = new ArrayList<ActivatedSquare>();
    Automaton graphicalAutomatonLogic = new Automaton(colNum,110);

//    Board(){
//
//    }


    public void paintComponent (Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        if(!(generation == 0)){
            graphicalAdvanceGeneration();
        }
        else{
            toActivatedSquares();
        }
        /*Uncomment for grid:*/
        //drawGrid(g2);
        drawActiveSquare(g2);
        generation++;

    }

    public void drawGrid(Graphics2D g2){
        for(int i=0; i<rowNum+1; i++){
            int yVal = (rowHeight*i)+margin;
            Line2D.Double rowLine = new Line2D.Double(margin, yVal, 800-(margin*2.2), yVal);
            g2.setColor(Color.black);
            g2.setStroke(new BasicStroke(strokeWidth));
            g2.draw(rowLine);
        }
        for(int i=0; i<colNum+1; i++){
            int xVal = (colHeight*i)+margin;
            Line2D.Double colLine = new Line2D.Double(xVal, margin, xVal, 800-(margin*2.2));
            g2.setColor(Color.black);

            g2.draw(colLine);
        }
    }
    public void fillSquare(Graphics2D g2, int row, int col){
        int X = (colHeight*(col-1))+margin;
        int Y = (rowHeight*(row-1))+margin;
        Rectangle chosenSquare = new Rectangle(X+strokeWidth, Y+strokeWidth, colHeight-(strokeWidth*2)+1, rowHeight-(strokeWidth*2));
        g2.setColor(Color.blue);
        g2.fill(chosenSquare);
        g2.draw(chosenSquare);
    }
    public void toActivatedSquares(){
        for(int i=0; i<graphicalAutomatonLogic.cellArray.length; i++){
            if(graphicalAutomatonLogic.cellArray[i] == 1){
                ActivatedSquare aS = new ActivatedSquare(i+1, rowNum);
                activatedSquares.add(aS);
            }
        }
    }

    public void drawActiveSquare(Graphics2D g2){
        for (int i=0; i<activatedSquares.size(); i++){
            fillSquare(g2, activatedSquares.get(i).row, activatedSquares.get(i).col);
        }
    }

    public void graphicalAdvanceGeneration(){
        graphicalAutomatonLogic.advanceGenereation();
        for(int i=0; i<activatedSquares.size(); i++){
            activatedSquares.get(i).row -= 1;
            if(activatedSquares.get(i).row == 0){
                activatedSquares.remove(i);
            }
        }
        toActivatedSquares();

    }
}
