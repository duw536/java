package PracticalExercise11;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class StepCounterManager {

    private HashMap<String, ArrayList<Integer>> stepMap;
    private Scanner sc;

    public StepCounterManager() {
        stepMap = new HashMap<>();
        sc = new Scanner(System.in);
    }

    public void run() {
        inputSteps();
        
        findAndPrintMaxStepper();
        
        searchSteps();
        
        sc.close();
    }

    private void inputSteps() {
        
        while (true) {
            System.out.print("이름과 걸음수>>");        	
            String line = sc.nextLine();
            if (line.equals("그만")) {
                break;
            }

            String[] tokens = line.split(" ");
            String name = tokens[0];

            ArrayList<Integer> stepList = stepMap.computeIfAbsent(name, k -> new ArrayList<>());
            for (int i = 1; i < tokens.length; i++) {
                try {
                    stepList.add(Integer.parseInt(tokens[i]));
                } catch (NumberFormatException e) {
                }
            }
        }
    }

    private void findAndPrintMaxStepper() {
        String maxName = "";
        int maxTotal = -1;

        for (Map.Entry<String, ArrayList<Integer>> entry : stepMap.entrySet()) {
            String name = entry.getKey();
            int currentTotal = calculateTotal(entry.getValue());

            if (currentTotal > maxTotal) {
                maxTotal = currentTotal;
                maxName = name;
            }
        }

        if (!maxName.isEmpty()) {
            System.out.println("걸음수가 가장 많은 사람은 " + maxName + " " + maxTotal + "보");
        }
    }

    private void searchSteps() {
        
        while (true) {
            System.out.print("검색할 이름>>");
            String name = sc.nextLine();
            
            if (name.equals("그만")) {
                break;
            }

            ArrayList<Integer> list = stepMap.get(name);

            if (list == null) {
                System.out.println(name + "은 없는 학생입니다.");
            } else {
                for (int steps : list) {
                    System.out.print(steps + " ");
                }

                int average = calculateAverage(list);
                System.out.println("평균 " + average);
            }
        }
    }

    private int calculateTotal(ArrayList<Integer> list) {
        int total = 0;
        for (int steps : list) {
            total += steps;
        }
        return total;
    }

    private int calculateAverage(ArrayList<Integer> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return calculateTotal(list) / list.size();
    }

    public static void main(String[] args) {
        StepCounterManager manager = new StepCounterManager();
        manager.run();
    }
}