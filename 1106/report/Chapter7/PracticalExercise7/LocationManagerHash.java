package PracticalExercise7;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Location {
    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
}

public class LocationManagerHash {

    public static void main(String[] args) {
        HashMap<String, Location> cityMap = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("4개의 도시를 입력받아 해시맵에 저장합니다.");
        System.out.println("도시, 위도, 경도를 입력하세요 (예: 서울, 37, 126)");
        
        for (int i = 0; i < 4; i++) {
            System.out.print(">> ");
            String line = sc.nextLine();
            String[] tokens = line.split(", ");

            String name = tokens[0].trim();
            double lat = Double.parseDouble(tokens[1].trim());
            double lon = Double.parseDouble(tokens[2].trim());

            cityMap.put(name, new Location(lat, lon));
        }
        System.out.println("--------------------");

        for (Map.Entry<String, Location> entry : cityMap.entrySet()) {
            String name = entry.getKey();
            Location loc = entry.getValue();
            System.out.println(name + "\t" + loc.getLatitude() + "\t" + loc.getLongitude());
        }
        System.out.println("--------------------");

        while (true) {
            System.out.print("도시 이름 >> ");
            String searchName = sc.nextLine();

            if (searchName.equals("그만")) {
                break;
            }

            Location loc = cityMap.get(searchName);
            
            if (loc == null) {
                System.out.println(searchName + "는 없습니다.");
            } else {
                System.out.println(searchName + "\t" + loc.getLatitude() + "\t" + loc.getLongitude());
            }
        }
        
        sc.close();
    }
}
