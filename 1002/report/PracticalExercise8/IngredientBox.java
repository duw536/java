package PracticalExercise8;

import java.util.Scanner;

class IngredientBox extends Box {
    private String ingredientName;

    public IngredientBox(String name, int size) {
        super(size);
        this.ingredientName = name;
    }

    @Override
    public boolean consume() {
        if (!isEmpty()) { 
            this.size--;  
            return true;
        }
        return false;
    }

    @Override
    public void print() {
        System.out.print(this.ingredientName + ">> ");
        for (int i = 0; i < this.size; i++) {
            System.out.print("*");
        }
        System.out.println(this.size);
    }
}

class CoffeeVendingMachine {
    public static void main(String[] args) {
        IngredientBox coffee = new IngredientBox("커피", 5);
        IngredientBox cream = new IngredientBox("프림", 5);
        IngredientBox sugar = new IngredientBox("설탕", 5);
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("*****청춘 커피 자판기 입니다.*****");

        while (true) {
            coffee.print();
            cream.print();
            sugar.print();
            
            System.out.print("1:다방커피, 2:설탕커피, 3:블랙커피, 4:종료 >>");
            int choice = scanner.nextInt();

            boolean result;
            switch (choice) {
                case 1:
                    if (!coffee.isEmpty() && !cream.isEmpty() && !sugar.isEmpty()) {
                    	coffee.consume();
                    	cream.consume();
                        sugar.consume();
                    } else {
                        System.out.println("원료가 부족합니다.");
                    }
                    break;

                case 2:
                    if (!coffee.isEmpty() && !sugar.isEmpty()) {
                        coffee.consume();
                        sugar.consume();
                    } else {
                    	System.out.println("원료가 부족합니다.");
                    }
                    break;
                
                case 3:
                    if (!coffee.isEmpty()) {
                        coffee.consume();
                    } else {
                    	System.out.println("원료가 부족합니다.");
                    }
                    break;

                case 4:
                    System.out.println("청춘 커피 자판기 프로그램을 종료합니다.");
                    scanner.close();
                    return;

            }
        }
    }
}