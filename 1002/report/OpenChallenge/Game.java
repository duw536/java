package OpenChallenge;

import java.util.Scanner;

public class Game {
    private Player human;
    private Player computer;
    private Player owner;

    public Game() {
        Scanner scn = new Scanner(System.in);

        System.out.print("선수 이름을 입력하세요>>");
        String humanName = scn.next();
        
        System.out.print("컴퓨터 이름을 입력하세요>>");
        String computerName = scn.next();

        human = new Human(humanName);
        computer = new Computer(computerName);
        owner = human;
        
    }

    public void run() {
        System.out.println("2명의 선수를 생성 완료하였습니다.");

        while(true) {
        	String humanBet;
        	
        	while (true) {
                humanBet = human.next();
                
                if (humanBet.equals("묵") || humanBet.equals("찌") || humanBet.equals("빠")) {
                    break; 
                } else {
                    System.out.println("묵 찌 빠 중에서 다시 입력하세요.");
                }
            }
        	
            String computerBet = computer.next(); 
            
            System.out.println(human.getName() + " : " + humanBet + ", " + computer.getName() + " : " + computerBet);
            
            if (humanBet.equals(computerBet)) {
                System.out.println(owner.getName() + "이 이겼습니다.");
                break; 
            }
            
            Player previousOwner = owner;
            
            if (isHumanWinner(humanBet, computerBet)) {
                owner = human; 
            } else {
                owner = computer;
            }

            if (owner != previousOwner) {
                System.out.println("오너가 " + owner.getName() + "로 변경되었습니다.");
            }
            System.out.println();
        }
    }
    
    private boolean isHumanWinner(String human, String computer) {
        return (human.equals("묵") && computer.equals("찌")) ||
               (human.equals("찌") && computer.equals("빠")) ||
               (human.equals("빠") && computer.equals("묵"));
    }
}