package PracticalExercise12;

class Dictionary extends PariMap {
    private int count; 

    public Dictionary(int capacity) {
        this.keyArray = new String[capacity];
        this.valueArray = new String[capacity];
        this.count = 0;
    }

    @Override
    public String get(String key) {
        for (int i = 0; i < count; i++) {
            if (keyArray[i].equals(key)) {
                return valueArray[i]; 
            }
        }
        return null; 
    }

    @Override
    public void put(String key, String value) {
        for (int i = 0; i < count; i++) {
            if (keyArray[i].equals(key)) {
                valueArray[i] = value;
                return;
            }
        }

        if (count < keyArray.length) {
            keyArray[count] = key;
            valueArray[count] = value;
            count++;
        }
    }

    @Override
    public String delete(String key) {
        int indexToDelete = -1;
        String valueToDelete = null;

        for (int i = 0; i < count; i++) {
            if (keyArray[i].equals(key)) {
                indexToDelete = i;
                valueToDelete = valueArray[i];
                break;
            }
        }
        
        if (indexToDelete != -1) {
            for (int i = indexToDelete; i < count - 1; i++) {
                keyArray[i] = keyArray[i + 1];
                valueArray[i] = valueArray[i + 1];
            }
            count--;
        }

        return valueToDelete; 
    }

    @Override
    public int length() {
        return this.count;
    }
}


class DictionaryApp {
    public static void main(String[] args) {
        Dictionary dic = new Dictionary(10);
        dic.put("황기태", "자바");
        dic.put("이재문", "파이선");
        dic.put("이재문", "C++");
        System.out.println("이재문의 값은 " + dic.get("이재문"));
        System.out.println("황기태의 값은 " + dic.get("황기태"));
        dic.delete("황기태");
        System.out.println("황기태의 값은 " + dic.get("황기태"));
    }
}