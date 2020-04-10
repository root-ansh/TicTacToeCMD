import java.util.*;

public class TicTacToeText{

    private int[] boardModal = null;

    private char compCharactor ='X';
    // user =1 and comp =2 and empty space = 0,  draw game = 3;

    Scanner scn ;


    public TicTacToeText(char userChar, Scanner sc){
        this.boardModal = new int[9];
        this.compCharactor = userChar=='X'?'O':'X';
        this.scn =sc;

    }

    private void printBoard(){
        System.out.println("-------------------------------------------------\t-------------------------------------------------");
        System.out.println("| \t \t | \t \t | \t \t |\t| \t \t | \t \t | \t \t |\t");
        System.out.println("| \t"+getArrayCharactor(0)+"\t | \t"+getArrayCharactor(1)+"\t | \t"+getArrayCharactor(2)+"\t |\t| \t"+(0)+"\t | \t"+(1)+"\t | \t"+(2)+"\t |\t");
        System.out.println("| \t \t | \t \t | \t \t |\t| \t \t | \t \t | \t \t |\t");
        System.out.println("-------------------------------------------------\t-------------------------------------------------");
        System.out.println("| \t \t | \t \t | \t \t |\t| \t \t | \t \t | \t \t |\t");
        System.out.println("| \t"+getArrayCharactor(3)+"\t | \t"+getArrayCharactor(4)+"\t | \t"+getArrayCharactor(5)+"\t |\t| \t"+(3)+"\t | \t"+(4)+"\t | \t"+(5)+"\t |\t");
        System.out.println("| \t \t | \t \t | \t \t |\t| \t \t | \t \t | \t \t |\t");
        System.out.println("-------------------------------------------------\t-------------------------------------------------");
        System.out.println("| \t \t | \t \t | \t \t |\t| \t \t | \t \t | \t \t |\t");
        System.out.println("| \t"+getArrayCharactor(6)+"\t | \t"+getArrayCharactor(7)+"\t | \t"+getArrayCharactor(8)+"\t |\t| \t"+(6)+"\t | \t"+(7)+"\t | \t"+(8)+"\t |\t");
        System.out.println("| \t \t | \t \t | \t \t |\t| \t \t | \t \t | \t \t |\t");
        System.out.println("-------------------------------------------------\t-------------------------------------------------");
        
    }
    

    private char getCompCharacter(){
        return compCharactor;
    }
    private char getUserCharacter(){
        return compCharactor=='X'?'O':'X';
    }

    private String getArrayCharactor(int pos){
        if(boardModal[pos]==0) return "__";
        else if(boardModal[pos]==2) return ""+getCompCharacter();
        else if(boardModal[pos]==1) return ""+ getUserCharacter();
        else return "!";
        
    }


    
    private List<Integer> getUnFilledPositions(){
        List<Integer> unusedPlaces = new ArrayList<>();

        for (int i = 0; i < boardModal.length; i++) {
            if(boardModal[i]==0){
                unusedPlaces.add(i);
            }
        }
        return unusedPlaces;
    }

    private void runComputerTurn() {
        
        List<Integer> unFilledPositionsRemaining = getUnFilledPositions();

        int randomUnFilledPostionIndex = (int) System.currentTimeMillis()%unFilledPositionsRemaining.size();
        int randomUnfilledPositon =(int) unFilledPositionsRemaining.get(randomUnFilledPostionIndex);

        boardModal[randomUnfilledPositon]= 2;

        System.out.println("Computer Placed his "+compCharactor+"at "+randomUnfilledPositon+" position");
        
    }

    private void runUserTurn() {
        
        List<Integer> unFilledPositionsRemaining = getUnFilledPositions();
        System.out.print("\n select a position to place your to --"+unFilledPositionsRemaining+":");


        int userSelectedUnfilledPositon =scn.nextInt();
        boardModal[userSelectedUnfilledPositon]= 1;

        System.out.println("User Placed his "+getUserCharacter()+"at "+userSelectedUnfilledPositon+" position");
        
    }

    private int getWinner(){
        if(getUnFilledPositions().size()==0){
            return 3;
        }

        if(boardModal[0]==boardModal[1] && boardModal[1]==boardModal[2])      return boardModal[2];
        else if(boardModal[3]==boardModal[4] && boardModal[4]==boardModal[5]) return boardModal[5];
        else if(boardModal[6]==boardModal[7] && boardModal[7]==boardModal[8]) return boardModal[8];
        
        else if(boardModal[0]==boardModal[3] && boardModal[3]==boardModal[6]) return boardModal[6];
        else if(boardModal[1]==boardModal[4] && boardModal[4]==boardModal[7]) return boardModal[7];
        else if(boardModal[2]==boardModal[5] && boardModal[5]==boardModal[8]) return boardModal[8];
        
        else if(boardModal[0]==boardModal[4] && boardModal[4]==boardModal[8]) return boardModal[8];
        else if(boardModal[2]==boardModal[4] && boardModal[4]==boardModal[6]) return boardModal[6];
        
        else return 0;
    }

    private void declareWinner(){
        int n = getWinner();
        if(n==1){
            System.out.println("USER WON THE GAME");
        }
        else if(n==2){
            System.out.println("COMPUTER WON THE GAME");
        }
        else if(n==3){
            System.out.println("ITS A DRAW!!");
        }
        else{
            System.out.println("ehy this code ran? the game is still going on!");
        }
    }

    public void run(){
        System.out.println(" YOU GET TO GO FIRST");
        while (getWinner()<=0){
            printBoard();
            
            runUserTurn();
            printBoard();

            if(getWinner()>0){
                declareWinner();
                return;
            }

            runComputerTurn();
            printBoard();

            if(getWinner()>0){
                declareWinner();
                return;
            }
            System.out.println("--------------------------------------------------------");
            
        }

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose X or O:");
        char userChar = sc.next().charAt(0);

        new TicTacToeText(userChar,sc).run();

    }

}