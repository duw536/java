package PracticalExercise12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VirtualMachine {

    private ArrayList<String> program; 
    private HashMap<String, Integer> registers;
    private Scanner sc;
    
    public VirtualMachine() {
        program = new ArrayList<>();
        registers = new HashMap<>();
        sc = new Scanner(System.in);
    }

    private void inputProgram() {
        program.clear();
        System.out.println("프로그램을 입력해주세요. go를 입력하면 작동합니다.");
        
        while (true) {
            System.out.print(program.size() + ">> "); 
            String line = sc.nextLine();

            if (line.equals("go")) {
                System.out.println("입력된 프로그램 실행 지시");
                break;
            }
            program.add(line);
        }
    }

    private int getValue(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            return registers.getOrDefault(token, 0);
        }
    }

    private void executeProgram() {
        registers.clear();

        int pc = 0; 

        while (pc < program.size()) {
            String instruction = program.get(pc);
            String[] tokens = instruction.split(" ");
            String op = tokens[0];
            
            int old_pc = pc;

            switch (op) {
                case "mov":
                    registers.put(tokens[1], getValue(tokens[2]));
                    break;
                case "add":
                    registers.put(tokens[1], getValue(tokens[1]) + getValue(tokens[2]));
                    break;
                case "sub":
                    registers.put(tokens[1], getValue(tokens[1]) - getValue(tokens[2]));
                    break;
                case "jn0":
                    if (getValue(tokens[1]) != 0) {
                        pc = Integer.parseInt(tokens[2]);
                    }
                    break;
                case "prt":
                    System.out.println("[prt " + tokens[1] + "]에 의해 출력된 " + 
                                       tokens[1] + "변수값::" + getValue(tokens[1]));
                    break;
                case "exi":
                    return;
                default:
                    System.out.println("알 수 없는 명령어: " + op);
            }
            
            if (pc == old_pc) {
                pc++;
            }
        }
    }

    private void printRegisters() {
        System.out.println("프로그램 종료. 변수들의 최종 값을 출력합니다. ");
        for (Map.Entry<String, Integer> entry : registers.entrySet()) {
            System.out.print(entry.getKey() + ":" + entry.getValue() + " ");
        }
        System.out.println("\n");
    }

    public void run() {
        System.out.println("나의 가상 컴퓨터가 작동합니다.");

        while (true) {
            inputProgram();
            executeProgram();
            printRegisters();
        }
    }

    public static void main(String[] args) {
        VirtualMachine vm = new VirtualMachine();
        vm.run();
    }
}
