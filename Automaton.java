public class Automaton {
    int ruleNumber;
    int[] binaryRule;
    int[] cellArray;
    int generation;

    Automaton(int cellArrayLength, int ruleNumber){
        cellArray = new int[cellArrayLength];
        cellArray[cellArrayLength/2] = 1;
        binaryRule = new int[8];
        generation = 1;
        this.ruleNumber = ruleNumber;
        ruleConverter(ruleNumber);
    }

    public boolean ruleConverter(int ruleNumber) {
        int[] decimalBinaryRule = new int[8];
        int runningRuleNumber= ruleNumber;
        if((0<=ruleNumber)&&(ruleNumber<=255)){
            for(int i=0; i<decimalBinaryRule.length; i++){
                int twoPower = (int)Math.pow(2, 7-i);
                if(runningRuleNumber==0){
                    break;
                }
                if((twoPower)<=runningRuleNumber){
                    decimalBinaryRule[i] = 1;
                    runningRuleNumber-=twoPower;
                }
            }
            for(int i=0; i<binaryRule.length; i++){
                binaryRule[i] = decimalBinaryRule[i];
                System.out.print(binaryRule[i]);
            }
            System.out.println();



            return true;
        }
        return false;

    }

    public int binaryToDecimal(int char1, int char2, int char3){
        int finalValue = 0;
        if(char1 == 1){
            finalValue+=4;
        }
        if(char2 == 1){
            finalValue+=2;
        }
        if (char3 == 1){
            finalValue+=1;
        }
        return finalValue;

    }

    public void advanceGenereation(){
        int[] adjustedCellArray = new int[cellArray.length];

        generation += 1;
        for(int i=0; i<cellArray.length; i++){
            if(i==0){
                int decimalRep = binaryToDecimal(cellArray[cellArray.length-1],cellArray[i],cellArray[i+1]);
                adjustedCellArray[i] = binaryRule[binaryRule.length-decimalRep-1];
            }
            else if(i==cellArray.length-1){
                int decimalRep = binaryToDecimal(cellArray[i-1], cellArray[i],cellArray[0]);
                adjustedCellArray[i] = binaryRule[binaryRule.length-decimalRep-1];
            }
            else{
                int decimalRep = binaryToDecimal(cellArray[i-1],cellArray[i],cellArray[i+1]);
                adjustedCellArray[i] = binaryRule[binaryRule.length-decimalRep-1];
            }
        }
        for(int i=0; i<cellArray.length; i++){
            cellArray[i] = adjustedCellArray[i];
        }
    }
    public void printGeneration(){
        for(int i=0; i<cellArray.length; i++){
            System.out.print(cellArray[i]);
        }
    }

}
